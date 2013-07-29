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
import java.util.List;

public class Sheet {

	Long id;
	String name;
	
	List<Column> columns;
	List<Row> rows;
	String accessLevel;
	List<Discussion> discussions;
	List<Attachment> attachments;
	Boolean readOnly;
	Boolean ganttEnabled;
	Integer version;
	
	Long fromId;
	
	String permalink;
	Calendar createdAt;
	Calendar modifiedAt;
	
	String owner;
	Long ownerId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long value) {
		this.id = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String value) {
		this.name = value;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> value) {
		this.columns = value;
	}

	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> value) {
		this.rows = value;
	}

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	public List<Discussion> getDiscussions() {
		return discussions;
	}

	public void setDiscussions(List<Discussion> discussions) {
		this.discussions = discussions;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public Boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

	public Long getFromId() {
		return fromId;
	}

	public void setFromId(Long fromTemplateId) {
		this.fromId = fromTemplateId;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	public Calendar getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Calendar modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public Boolean getGanttEnabled() {
		return ganttEnabled;
	}

	public void setGanttEnabled(Boolean ganttEnabled) {
		this.ganttEnabled = ganttEnabled;
	}
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
}
