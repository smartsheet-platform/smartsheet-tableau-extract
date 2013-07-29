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

import java.io.IOException;
import java.util.List;

import com.smartsheet.platform.tableau.model.Attachment;
import com.smartsheet.platform.tableau.model.Sheet;
import com.smartsheet.platform.tableau.model.User;
import com.smartsheet.utils.HttpUtils;

/**
 * A RESTful implementation of the {@link Smartsheet} interface, i.e., using
 * the Smartsheet REST API to provide the service functionality. An instance of
 * the service is constructed with an access token argument. The instance can be
 * requested to assume the identity of another user on demand as long as the
 * access token provided was for an administrator of that user's organization.
 */
public class RestfulSmartsheetService implements SmartsheetService {

    private static final String API_BASE_URL = "http://kyans.smartsheet.com:8080/dev2/rest/1.1/";

    private final String accessToken;
    private String assumedUserEmail;

    public RestfulSmartsheetService(String accessToken) {
        this.accessToken = accessToken;
    }

    public List<User> getUsers() throws Exception {
        String json = getJsonPayload(API_BASE_URL + "users");
        return new JsonDeserializer<User>().deserializeArray(json, User.class);
    }
    public List<Sheet> getSheets() throws Exception {
    	String json = getJsonPayload(API_BASE_URL + "sheets");
    	return new JsonDeserializer<Sheet>().deserializeArray(json, Sheet.class);
    }

    public Sheet getSheetDetails(long sheetId) throws Exception {

        String json = getJsonPayload(API_BASE_URL + "sheet/" + sheetId + "?include=attachments,discussions");
        return new JsonDeserializer<Sheet>().deserialize(json, Sheet.class);
    }

    public Attachment getAttachmentDetails(long attachmentId) throws Exception {

        String json = getJsonPayload(API_BASE_URL + "attachment/" + attachmentId);
        return new JsonDeserializer<Attachment>().deserialize(json, Attachment.class);
    }

    public String getAccessToken() {
        return accessToken;
    }

    private String getJsonPayload(String url) throws IOException {
        return HttpUtils.getJsonPayload(url, accessToken, assumedUserEmail);
    }

    public void assumeUser(String assumedUserEmail) {
        this.assumedUserEmail = assumedUserEmail;
    }

    public String getAssumedUser() {
        return assumedUserEmail;
    }
}
