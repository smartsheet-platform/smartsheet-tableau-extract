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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Column {
	
    protected Long id;
    protected Long sheetId;
    
    protected Long virtualId;
    
    protected Integer index;
    protected String title;
    protected String type;
    protected String symbol;
    protected List<String> options = null;

    protected String systemColumnType;
    
    protected Boolean primary = null;
    protected Boolean locked = null;
    protected Boolean lockedForUser = null;
    protected Boolean hidden = null;
    protected Set<String> tags;
    
    public Column() {
	}

	public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex (Integer value) {
        this.index = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public void addOption(String opt) {
    	if (options == null) {
			options = new ArrayList<String>();
		}
    	options.add(opt);
    }
	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public Boolean getPrimary() {
		return primary;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}

	public Long getVirtualId() {
		return virtualId;
	}

	public void setVirtualId(Long virtualId) {
		this.virtualId = virtualId;
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

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Long getSheetId() {
		return sheetId;
	}

	public void setSheetId(Long sheetId) {
		this.sheetId = sheetId;
	}

	public String getSystemColumnType() {
		return systemColumnType;
	}

	public void setSystemColumnType(String systemColumnType) {
		this.systemColumnType = systemColumnType;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public void addTag(String tag) {
		if (tags == null) {
			tags = new HashSet<String>();
		}
		tags.add(tag);
	}
}
