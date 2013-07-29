Smartsheet Tableau Extract Tool
=============

This project is a functioning example of how you can extract data from Smartsheet into a Tableau Data Extract (TDE) format.

**NOTE**: This will extract *ALL* sheets viewable by the user that generates the access token.

Before You Begin:
------
>You will need to download the Tableau DataExtract API from their [website](http://www.tableausoftware.com/data-extract-api).


Requirements:
------
  * Java 7 
  * Tableau Extract API "bin" directory added to the PATH system variable
  
To Run:
---
>     java -jar smartsheet-tableau.jar [accessToken] [sheetIDs]
   *[accessToken]* Required. A user-generated access token created through the Account > Personal Settings > API Access menu.
   *[sheetIDs]*  Optional. A comma-separated list of sheet IDs. These can be found in "Properties" menu found by right-clicking a
   sheet in the Home tab.

To build this project: 
--
To build this project, you must have [Apache Ant](http://ant.apache.org/) installed. cd to the root folder, and run:
    
>     ant 

This will generate smartsheet-tableau.jar, which is the only jar file you will need.

Usage Scenarios:
---
###Personal export
Run this tool on your local computer and get all of your Sheets exported. You can then import the TDEs into Tableau Desktop and work with the data from there.

###Periodic Export to Tableau Server
This tool can be used to periodically export Smartsheet data to the Tableau Server. To do so, you must create a simple script to call this tool first and then call Tableau's TabCmd command line tool to import the data into Tableau Server. You should schedule this script to be called on a periodic basis.

Once the server has imported the data, you can use Tableau Desktop to connect to the TDEs as a Tableau Server data source. Once you create your visualizations and publish them, you can periodically run this script and re-import the data, which will automatically update the visualizations.

Note that this solution may require customization to meet your needs, since this only exports a single user's sheets, and exports all of them. **Add note here about security and data privacy**  
