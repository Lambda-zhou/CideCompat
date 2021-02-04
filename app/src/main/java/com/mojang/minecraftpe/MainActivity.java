package com.mojang.minecraftpe;
import com.xiaohan.seven.cide.CideRunJavaScriptActivity;

public class MainActivity {
    public static class currentMainActivity{
		
		private static CideRunJavaScriptActivity activity = null;
		
        public static CideRunJavaScriptActivity get(){
            return activity;
        }
		
		public static void set(CideRunJavaScriptActivity activi) {
			activity = activi;
		}
	
	}
}
