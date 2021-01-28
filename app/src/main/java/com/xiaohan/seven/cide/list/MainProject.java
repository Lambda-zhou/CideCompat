package com.xiaohan.seven.cide.list;

public class MainProject {
    
    private int imageId;
    
    private String title;
    
    private String size;
    
    private String time;
	
	private String[] paths;
	
	public String type;
    
    public MainProject(int imageId, String title, String size, String time, String type) {
        this.imageId = imageId;
        this.title = title;
        this.size = size;
        this.time = time;
		this.type = type;
    }
    
    public int getImageId() {
        return this.imageId;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getSize() {
        return this.size;
    }
    
    public String getTime() {
        return this.time;
    }
	
	public void setPaths(String[] paths) {
		this.paths = paths;
	}
	
	public String[] getPaths() {
		return this.paths;
	}
	
	public String getType() {
		return this.type;
	}
    
}
