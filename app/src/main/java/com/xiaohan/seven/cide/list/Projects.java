package com.xiaohan.seven.cide.list;
import java.io.File;

public class Projects {
    private String name;
    
    private int imageId;
    
    private File path;
    
    private Object tag;
    
    public Projects(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }
    
    public Projects(String name, int imageId, File path) {
        this.name = name;
        this.imageId = imageId;
        this.path = path;
    }
    
    public Projects(String name, int imageId, File path, Object tag) {
        this.name = name;
        this.imageId = imageId;
        this.path = path;
        this.tag = tag;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getImageId() {
        return this.imageId;
    }
    
    
    public File getFile() {
        return this.path;
    }
    
    public Object getTag() {
        return this.tag;
    }
    
}
