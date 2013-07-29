/**
 *    Copyright 2013 Smartsheet.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * 
 */
package com.smartsheet.platform.tableau;

import java.io.File;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smartsheet.platform.tableau.exceptions.NotFoundException;
import com.smartsheet.platform.tableau.model.Cell;
import com.smartsheet.platform.tableau.model.Column;
import com.smartsheet.platform.tableau.model.Row;
import com.smartsheet.platform.tableau.model.Sheet;
import com.smartsheet.restapi.service.RestfulSmartsheetService;
import com.smartsheet.restapi.service.RetryingSmartsheetService;
import com.smartsheet.restapi.service.SmartsheetService;
import com.tableausoftware.TableauException;
import com.tableausoftware.DataExtract.Collation;
import com.tableausoftware.DataExtract.Extract;
import com.tableausoftware.DataExtract.Table;
import com.tableausoftware.DataExtract.TableDefinition;
import com.tableausoftware.DataExtract.Type;

/**
 * This is the Main class that gets called to convert Smartsheets into Tableau DataExtracts (TDEs). 
 * The TDEs will be created in the current working directory.
 * 
 * When running, be sure the "bin" folder from the TDE-API-C-Java-64Bit.zip (or TDE-API-C-Java-32Bit.zip 
 * for 32 bit Windows) file are in the PATH.
 * 
 *  Note the following arguments:
 *  
 *  java -jar smartsheet-tableau.jar [accessToken] [sheetIds]
 *  
 *   [accessToken] : Required. A user generated access token from Smartsheet
 *   [sheetIds]    : Optional. A comma-separated list of sheet Ids. These can be found by right clicking a sheet and viewing "Properties". 
 *   				 If this is not present, all sheets from the user will be converted to TDEs. 
 * 
 * 
 * 
 * 
 * @author kskeem
 *
 */
public class SmartsheetExtractor {

	public static void main(String[] args) {
		if (args.length < 1 || args.length > 2) {
			System.out.println("Please provide an access token and optionally a comma-separated list of sheet IDs.  example: java -jar smartsheet-tableau.jar <accesstoken> 123,456,789");
			System.exit(1);
		}

		String accessToken = args[0];
		
		//Get an instance of the smartsheet client
		SmartsheetService smartsheet = new RetryingSmartsheetService(new RestfulSmartsheetService(accessToken));
		try {
			//Fetch the list of sheets
			List<Sheet> sheets = null;
			if (args.length > 1) {
				String sheetIds = args[1];
				String[] split = sheetIds.split(",");
				sheets = new ArrayList<Sheet>();
				Sheet sheet = null;
				for (String sheetId : split) {
					sheet = new Sheet();
					try {
						sheet.setId(Long.parseLong(sheetId));
						sheet.setName(sheetId);
						sheets.add(sheet);
					} catch (NumberFormatException nfe) {
						System.out.println("Invalid sheet id, not numeric: " + sheetId);
					}
				}
			} else {
				sheets = smartsheet.getSheets();
			}
			TableDefinition tableDef = null;
			Table table = null;
			
			//Loop over the list of sheets
			for (Sheet sheet : sheets) {
				System.out.println("Starting sheet: " + sheet.getName());
				try { 
					sheet = smartsheet.getSheetDetails(sheet.getId());
				} catch (NotFoundException nfe) {
					System.out.println("Unable to find sheet id:" + sheet.getId() + ". Skipping.");
					continue;
				}
				try (Extract extract = new Extract(getExtractName(sheet.getName()))) {
					tableDef = new TableDefinition();
					tableDef.setDefaultCollation(Collation.EN_US);
					
					processColumns(sheet, tableDef);
	
					table = extract.addTable("Extract", tableDef);
					
					Map<Long, Integer> columnNumberMap = new HashMap<Long, Integer>();
					for (int i = 0; i < sheet.getColumns().size(); i++) {
						columnNumberMap.put(sheet.getColumns().get(i).getId(), i);
					}
					
					int columnNumber = 0;
					SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dddd");
					SimpleDateFormat dateTimeSdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
					for (Row row : sheet.getRows()) {
						com.tableausoftware.DataExtract.Row tableauRow = new com.tableausoftware.DataExtract.Row(tableDef);
						for (Cell cell : row.getCells()) {
							columnNumber = columnNumberMap.get(cell.getColumnId());
							switch(tableDef.getColumnType(columnNumber)) {
							case CHAR_STRING :
								if (cell.getValue() != null) {
									//There is a bug in the Tableau DataExtract API that assumes the default charset is UTF-8.
									//We convert it from default to UTF-8 to avoid an error.
									String val = new String(cell.getDisplayValue().getBytes(Charset.defaultCharset()), "UTF-8");
									tableauRow.setCharString(columnNumber, val);
								}
								break;
							case DOUBLE :
								if (cell.getValue() != null) {
									try {
										tableauRow.setDouble(columnNumber, Double.parseDouble(cell.getValue().toString()));
									} catch (NumberFormatException nfe) {
										//Do nothing.
									}
								}
								break;
							case BOOLEAN :
								if (cell.getValue() != null) {
									tableauRow.setBoolean(columnNumber, Boolean.parseBoolean(cell.getValue().toString()));
								}
								break;
							case DATE :
								if (cell.getValue() != null) {
									try{ 
										Date date = dateSdf.parse(cell.getValue().toString());
										Calendar cal = Calendar.getInstance();
										cal.setTime(date);
										tableauRow.setDate(columnNumber, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
									} catch (ParseException pe) {
										//Skip this entry, it doesn't comply.
									}
									
								}
								break;
							case DATETIME :
								if (cell.getValue() != null) {
									try{ 
										Date date = dateTimeSdf.parse(cell.getValue().toString());
										Calendar cal = Calendar.getInstance();
										cal.setTime(date);
										tableauRow.setDateTime( columnNumber,
																			 cal.get(Calendar.YEAR), 
																			 cal.get(Calendar.MONTH) + 1, 
																			 cal.get(Calendar.DAY_OF_MONTH),
																			 cal.get(Calendar.HOUR_OF_DAY),
																			 cal.get(Calendar.MINUTE),
																			 cal.get(Calendar.SECOND),
																			 cal.get(Calendar.MILLISECOND) * 10);
										tableauRow.setNull(columnNumber);
									} catch (ParseException pe) {
										//Skip this entry, it doesn't comply.
									}
									
								}
								break;
							default:
								//do nothing, shouldn't get here.
								break;
							}
						}
//						System.out.println("Adding row:"+ row.getRowNumber());
						table.insert(tableauRow);
					}
				}
			}
			System.exit(0);
		} catch (Exception e) {
			System.err.println("An error has occured while attempting to export a Smartsheet to a TDE.");
			e.printStackTrace();
			System.exit(-1);
		}
	}

	private static String getExtractName(String name) {
		name = name.replaceAll("[\\?\"\\/\\\\\\<\\>\\*\\|:]+", "_");
		if (name.length() > 252) {
			name = name.substring(0, 252);
		}
		File file = new File(name + ".tde");
		int i = 0;
		while (file.exists()) {
			file = new File(name + "(" + ++i + ").tde");
		}
		return file.getName();
	}

	/**
	 * Loops over the columns in the sheet and add the appropriate {@link TableDefinition} columns to
	 * the TDE. For TEXT_NUMBER data types, the first row is used to determine whether be text
	 * or number.
	 * 
	 * @param sheet
	 * @param tableDef
	 * @throws TableauException
	 */
	private static void processColumns(Sheet sheet, TableDefinition tableDef) throws TableauException {
		//Use the first row to determine column types
		Row firstRow = null;
		if (sheet.getRows() != null && sheet.getRows().size() > 0) {
			firstRow = sheet.getRows().get(0);
		}
		if (firstRow == null) {
			firstRow = new Row();
		}
		//Create a map of the cells to their respective columnIds
		Map<Long, Cell> cellMapByColumnId = new HashMap<Long, Cell>();
		for (Cell cell : firstRow.getCells()) {
			cellMapByColumnId.put(cell.getColumnId(), cell);
		}
		
		Type type = Type.CHAR_STRING;
		//Loop over the columns defined in the Sheet and map them to Tableau column types
		for (Column col : sheet.getColumns()) {
			if (col.getType().equals("TEXT_NUMBER") || col.getType().equals("PICKLIST")) {
				Cell firstCell = cellMapByColumnId.get(col.getId());
				if (firstCell == null || !isDouble(firstCell.getValue())) {
					type = Type.CHAR_STRING;
				} else {
					type = Type.DOUBLE;
				}
			} else if (col.getType().equals("DATE") ) {
				type = Type.DATE;
			}else if ( col.getType().equals("DATETIME")) {
				type = Type.DATETIME;
			} else if (col.getType().equals("CHECKBOX")) {
				type = Type.BOOLEAN;
			} else if (col.getType().equals("CONTACT_LIST")) {
				type = Type.CHAR_STRING;
			}
			tableDef.addColumn(col.getTitle(), type);
		}
	}

	private static boolean isDouble(Object value) {
		if (value == null) {
			return false;
		} else {
			try {
				Double.parseDouble(value.toString());
				return true;
			} catch (NumberFormatException nfe) {
				return false;
			}
		}
	}
}
