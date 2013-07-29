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
package com.smartsheet.restapi.service;

import java.util.List;

import com.smartsheet.platform.tableau.model.Attachment;
import com.smartsheet.platform.tableau.model.Sheet;
import com.smartsheet.platform.tableau.model.User;

/**
 * An abstraction of a service for Smartsheet requests.
 */
public interface SmartsheetService {

    List<User> getUsers() throws Exception;
    
    List<Sheet> getSheets() throws Exception;

    Sheet getSheetDetails(long sheetId) throws Exception;

    Attachment getAttachmentDetails(long attachmentId) throws Exception;

    String getAccessToken();

    void assumeUser(String assumedUserEmail);

    String getAssumedUser();
}
