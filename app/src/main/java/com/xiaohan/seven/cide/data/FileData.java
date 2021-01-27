package com.xiaohan.seven.cide.data;

public class FileData {
    
	private String name;
	
    private String type;
	
	private String[] paths;
	
	private String[] size;
	
	private String[] time;
	
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
	
	public void setPaths(String[] paths) {
		this.paths = paths;
	}

	public String[] getPaths() {
		return this.paths;
	}
	
	public void setSize(String[] size) {
		this.size = size;
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
    
}
