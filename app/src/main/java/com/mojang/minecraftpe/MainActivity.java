package com.mojang.minecraftpe;
import com.xiaohan.seven.cide.CideRunJavaScriptActivity;

public class MainActivity {
    public static class currentMainActivity{
		
        public static CideRunJavaScriptActivity get(){
            return CideRunJavaScriptActivity._this;
        }
	
	}
}
