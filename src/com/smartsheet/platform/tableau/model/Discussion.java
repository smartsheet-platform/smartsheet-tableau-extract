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

public class Discussion {

    protected String title;
    protected Long id;
    protected Comment comment;
    protected List<Comment> comments;
    protected List<Attachment> commentAttachments;
    protected String accessLevel;
    protected Calendar lastCommentedAt;
    protected User lastCommentedUser;
    Boolean readOnly;
    protected Boolean locked = null;
    protected Boolean lockedForUser = null;
    
    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public List<Attachment> getCommentAttachments() {
		return commentAttachments;
	}
	public void setCommentAttachments(List<Attachment> attachments) {
		this.commentAttachments = attachments;
	}
	public Calendar getLastCommentedAt() {
		return lastCommentedAt;
	}
	public void setLastCommentedAt(Calendar lastCommentedDate) {
		this.lastCommentedAt = lastCommentedDate;
	}
	public User getLastCommentedUser() {
		return lastCommentedUser;
	}
	public void setLastCommentedUser(User lastCommentedUser) {
		this.lastCommentedUser = lastCommentedUser;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public String getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}
	public Boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}
	public Boolean getLocked() {
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	public Boolean getLockedForUser() {
		return lockedForUser;
	}
	public void setLockedForUser(Boolean lockedForUser) {
		this.lockedForUser = lockedForUser;
	}
}
