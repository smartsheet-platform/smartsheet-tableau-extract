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
package com.smartsheet.platform.tableau.model;


import java.util.Calendar;

public class Cell {

    protected String type;
    
    protected String symbol;
    protected Object value;
    
    protected String formula;
    protected Link link;
    
    protected boolean strict = true;
    
    protected Long columnId;
    protected Long virtualColumnId;
    protected String displayValue;
    private Integer index;
    Calendar modifiedAt;
    
    private static final Object NULL = new Object();
    
	public Cell() {
	}
	public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

	public boolean isValueString() {
		return value instanceof String;
	}

   	public boolean isValueNULL() {
   		return value == NULL;
   	}
    
	public boolean isValueNumeric() {
		return value instanceof Number;
	}

   	public boolean isValueBoolean() {
   		return value instanceof Boolean;
   	}

	public Long getColumnId() {
		return columnId;
	}

	public void setColumnId(Long columnId) {
		this.columnId = columnId;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		if (value == null) {
			this.value = NULL;
		} else {
			this.value = value;
		}
	}
	
    public void setValueQuiet(Object value) {
		this.value = value;
	}

 
	public boolean isStrict() {
		return strict;
	}

	public void setStrict(boolean strict) {
		this.strict = strict;
	}


	public void setDisplayValue(String dataString) {
		this.displayValue = dataString;
	}
	public String getDisplayValue() {
		return displayValue;
	}
	public Long getVirtualColumnId() {
		return virtualColumnId;
	}
	public void setVirtualColumnId(Long virtualColumnId) {
		this.virtualColumnId = virtualColumnId;
	}
	
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public static class Link {
		Long sheetId;
		Long rowId;
		Long columnId;
		String url;
		String type;
		
		public Long getSheetId() {
			return sheetId;
		}
		public void setSheetId(Long sheetId) {
			this.sheetId = sheetId;
		}
		public Long getRowId() {
			return rowId;
		}
		public void setRowId(Long rowId) {
			this.rowId = rowId;
		}
		public Long getColumnId() {
			return columnId;
		}
		public void setColumnId(Long columnId) {
			this.columnId = columnId;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	}

	public Link getLink() {
		return link;
	}
	public void setLink(Link link) {
		this.link = link;
	}

}
