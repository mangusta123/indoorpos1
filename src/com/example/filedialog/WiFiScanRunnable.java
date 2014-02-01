package com.example.filedialog;

import android.content.Context;
import android.widget.Toast;

public class WiFiScanRunnable implements Runnable {

	int off = 0;
	
	Context ctx;
	
	public WiFiScanRunnable(Context ctx1){
		
		ctx = ctx1;
		
		
	}
	
	
	public void run(){
		
		try{
		
		while(off==0){
		
        //perform scanning here 
		//every 1 second 	
			
		TestMapActivity.wifi.startScan();
		Thread.sleep(1000);
		
		}//end while
		
       }//end try
		catch(Exception e){}
      }
	
	
}
