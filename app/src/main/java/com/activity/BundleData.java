package com.activity;
import java.io.Serializable;

public class BundleData implements Serializable {
    
	private Object[] objects;
	
    public BundleData(Object[] objects) {
		this.objects = objects;
	}
	
	public Object[] getObjects() {
		return this.objects;
	}
    
}
