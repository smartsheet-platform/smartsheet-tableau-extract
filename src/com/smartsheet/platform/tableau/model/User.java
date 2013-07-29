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


public class User {
	
	String email;
	String name;
	Boolean admin;
	Boolean licensedSheetCreator;

	Long id;
	
	String status;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean isAdmin() {
		return admin;
	}
	public void setAdmin(Boolean isAdmin) {
		this.admin = isAdmin;
	}
	public Boolean isLicensedSheetCreator() {
		return licensedSheetCreator;
	}
	public void setLicensedSheetCreator(Boolean isLicensedSheetCreator) {
		this.licensedSheetCreator = isLicensedSheetCreator;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
