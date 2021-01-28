package com.xiaohan.seven.cide.data;

public class AndroidData {
    
    private String name;

    private String type;

    private String[] activities;
    
    private String[] paths;

    private String[] size;

    private String[] time;
    
    private String packageName;
	
	private String versionCode;
	
	private String versionName;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setActivities(String[] activities) {
        this.activities = activities;
    }
    
    public void setPaths(String[] paths) {
        this.paths = paths;
    }
    
    public String[] getPaths() {
        return this.paths;
    }

    public String[] getActivities() {
        return this.activities;
    }

    public void setSize(String[] size) {
        this.size = size;
    }
    
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
	
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	
	public String getVersionCode() {
		return this.versionCode;
	}
	
	public String getVersionName() {
		return this.versionName;
	}
	
    public String[] getSize() {
        return this.size;
    }

    public void setTime(String[] time) {
        this.time = time;
    }

    public String[] getTime() {
        return this.time;
	}
    
    public String getPackageName() {
        return this.packageName;
    }
    
}
