package com.xiaohan.seven.cide.list;
import java.io.File;

public class AndroidProject {
    
    private String name;

    private int imageId;

    private File path;

    public AndroidProject(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return this.name;
    }
    
    
    public int getImageId() {
        return this.imageId;
    }
    
}
