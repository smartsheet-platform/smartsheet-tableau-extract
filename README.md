
Smartsheet Tableau Extract Utility
===
# NOTE: This repo is only for historic purposes.

If you wish to view Smartsheet data in Tableau, please refer to the ODBC connector: https://www.smartsheet.com/apps/smartsheet-odbc

License and Warranty
--------------------
Copyright 2013 Smartsheet.com

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

The use of Tableau Software executables, libraries, or other files
distributed herein is governed by the included TABLEAU DATA EXTRACT API
BINARY CODE LICENSE AGREEMENT - please refer to the TABLEAU-LICENSE.pdf
file for details.


Overview
--------

This Windows command-line utility extracts data from Smartsheet into a Tableau Data Extract (TDE) format.

You can use this utility to perform a one-off export of your Smartsheet data so you can analyze and review it in Tableau, or set up a recurring job to periodically refresh a Tableau dataset that is available to your entire team. 


Revision History
--------

1.0 - July 29 2013 - Initial build
1.1 - November 27 2013 - Fix exception on null cell value


Building
---
If you are looking for instructions to run the utility, skip this section and go directly to "Usage".

* Make sure you have Java 7 installed.
* Make sure you have [Apache Ant](http://ant.apache.org/) installed. 
* Change directory to the root of the project and run <code>ant</code> to build.

This will generate <code>smartsheet-tableau.jar</code>, which is the only jar file you will need, also available for download [here](https://googledrive.com/host/0BzPTof2spscfOVpkVjU4SXViM3M/smartsheet-tableau-1.2.jar).



Usage
------
* This utility will only run on Windows (XP or later required) because Tableau currently only supports Windows.
* Make sure you have Java 7 installed.
* Download the precompiled <code>smartsheet-tableau.jar</code> Java JAR file [here](https://googledrive.com/host/0BzPTof2spscfOVpkVjU4SXViM3M/smartsheet-tableau-1.2.jar), or build it by following the instructions in "Building".
* Download the Tableau Data Extract API (for Java) from the [Tableau website](http://www.tableausoftware.com/data-extract-api).
* Add the Tableau Data Extract API "bin" directory to your Windows PATH system/environment variable (see http://www.java.com/en/download/help/path.xml for instructions).
* Generate a Smartsheet API access token - see [the Smartsheet API docs](http://smartsheet.com/developers/api-faq) for intructions.

Now you are ready to run the utility.  To run, execute the following command via the Windows command line prompt:

	java -jar smartsheet-tableau.jar [accessToken] [sheetIDs]

* [accessToken] 	Required. A user-generated access token created through the Account > Personal Settings > API Access menu.
* [sheetIDs]    Optional. A comma-separated list of sheet IDs. These can be found in "Properties" menu by right-clicking a sheet in the Home tab - see this [Smartsheet Help Center article](http://help.smartsheet.com/customer/portal/articles/1205389-sheet-properties) for more information.
* Note: when running the export, be sure you are in a directory that you can write to. If you attempt to run in a read-only directory (such as C:\ or C:\Program Files, etc) you will see the following:
   <code>com.tableausoftware.TableauException: Invalid Extract handle</code>.You can solve this one of two ways:
  1. From the start menu, search for “cmd” and right click and run as administrator. Continue as before. or
  2. Move the Jar file to a different directory, such as C:\tableau-extract (you’d have to create this directory of course) and run the tool from there.


<b>IMPORTANT</b>: if you don't provide a list of sheet IDs, <b>ALL</b> of the user's sheets will be exported. 


Usage Scenarios
---
###Personal export
Run this tool on your local computer and get all of your Sheets exported. You can then import the TDEs into Tableau Desktop and work with the data from there.

###Periodic Export to Tableau Server
This tool can be used to periodically export Smartsheet data to the Tableau Server. To do so, you must create a simple script to call this tool first and then call Tableau's TabCmd command line tool to import the data into Tableau Server. You should schedule this script to be called on a periodic basis.

Once the server has imported the data, you can use Tableau Desktop to connect to the TDEs as a Tableau Server data source. Once you create your visualizations and publish them, you can periodically run this script and re-import the data, which will automatically update the visualizations.

Note that this solution may require customization to meet your needs, since this only exports a single user's sheets, and exports all of them. 

[![githalytics.com alpha](https://cruel-carlota.pagodabox.com/6f98423b0c8381a4d6c388aadb694aa7 "githalytics.com")](http://githalytics.com/smartsheet-platform/smartsheet-tableau-extract)
