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
import java.util.Comparator;

public class Attachment {
    protected String name;
	protected String description;
    protected String url;
    protected String attachmentType;
	protected String attachmentSubType;
    
	protected String mimeType;
    protected Long id;
    protected Long urlExpiresInMillis;
    protected Integer sizeInKb;
    protected String parentType;
    protected Long parentId;
    
    Calendar createdAt;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAttachmentType() {
		return attachmentType;
	}
	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public Long getUrlExpiresInMillis() {
		return urlExpiresInMillis;
	}
	public void setUrlExpiresInMillis(Long urlExpiresInMillis) {
		this.urlExpiresInMillis = urlExpiresInMillis;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAttachmentSubType() {
		return attachmentSubType;
	}
	public void setAttachmentSubType(String attachmentSubType) {
		this.attachmentSubType = attachmentSubType;
	}
	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}
	
	public static class AttachmentReverseChronoComparator implements Comparator<Attachment> {

		@Override
		public int compare(Attachment o1, Attachment o2) {
			return o2.getCreatedAt().compareTo(o1.getCreatedAt());
		}
		
	}

	public Integer getSizeInKb() {
		return sizeInKb;
	}
	public void setSizeInKb(Integer sizeInKb) {
		this.sizeInKb = sizeInKb;
	}
	public String getParentType() {
		return parentType;
	}
	public void setParentType(String parentType) {
		this.parentType = parentType;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public static class AttachmentComparator implements Comparator<Attachment> {
		public int compare(Attachment o1, Attachment o2) {
			int compare = o1.getName().compareTo(o2.getName());
			if (compare == 0) {
				compare = o1.getCreatedAt().compareTo(o2.getCreatedAt());
			}
			return compare;
		}
	}
}
