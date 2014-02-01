package com.example.filedialog;

import android.graphics.drawable.BitmapDrawable;

public class ApplicationClass {

	int AppID;
	
	BitmapDrawable AppIcon;
	
	String AppName;
	
	String packageName;   // in case if application's android package name is known (i.e. com.xxxx.xxxx)
	
	
	public ApplicationClass(int appid, String appname, String packagename){
		
		
		AppID = appid;
		
		AppName = appname;
		
		packageName = packagename;	
		
	}
	
	
	
}
