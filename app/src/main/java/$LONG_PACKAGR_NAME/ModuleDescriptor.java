package $LONG_PACKAGR_NAME;

public final abstract class ModuleDescriptor {
    
	private ModuleDescriptor() {}
	
    public final static String PACKAGE_NAME = "com.xiaohan.seven.cide";
    
	public final static String APP_NAME = "CideCompat";
	
	public final static String getPackageName() {
		return PACKAGE_NAME;
	}
	
	public final static String getAppName() {
		return APP_NAME;
	}
	
}
