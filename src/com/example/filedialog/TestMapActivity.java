package com.example.filedialog;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class TestMapActivity extends Activity {
	
    static DisplayMetrics metrics = new DisplayMetrics();
	static Context tmp;
        
    static int numOfAps = 0;       // number of access points
	
	static int numOfRefPoints = 0; // number of radiomap reference points
	
	static List<String> listOfAPs;
	static List<referPoint> referPoints;
	
	static WifiManager wifi;
	BroadcastReceiver receiver;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {               //called only once after app launch
 
    	tmp = getApplicationContext();
    	getWindowManager().getDefaultDisplay().getMetrics(metrics);
   	       	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_map);
        
        Log.d("TestMapActivity", "onCreate" );
     
         
		try{
			
		String tmp;	
					
		listOfAPs = new ArrayList<String>();                        // list of access point BSSIDs
		
		referPoints = new ArrayList<referPoint>();                  // list of radiomap reference points
		
		BufferedReader in1 = new BufferedReader(new FileReader("/sdcard/"+FileDialogFragment.mChosenRMapFile));  // radiomap chosen by dialog 
		BufferedReader in2 = new BufferedReader(new FileReader("/sdcard/"+FileDialogFragment.mChosenAPFile));    // AP list  chosen by dialog
				
		//populate list of BSSIDs
		
		while((tmp = in2.readLine())!=null)
			
		{   listOfAPs.add(tmp);
			numOfAps++;
		}		
		
		//populate list of coordinates 
		
		while((tmp = in1.readLine())!=null)
			
		{   referPoints.add(new referPoint(tmp));
			numOfRefPoints++;		
		}		
	
		
		// Setup WiFi
     	wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        // Get WiFi status
     	WifiInfo info = wifi.getConnectionInfo();	
		
        // Register Broadcast Receiver
        if (receiver == null)
     		receiver = new WiFiScanReceiver(this);

     		registerReceiver(receiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));	
		
				
     	//finally we are supposed to perform scanning at regular intervals (every 1 second)
     	//within an infinite loop
       	//this is done through service	
     		
     	Intent service = new Intent(TestMapActivity.this, WiFiScanService.class);
	    startService(service);
     		
     		
     		
		}//end try
			
		catch(Exception e){}
           
    } // end onCreate
    
    
    @Override
    public void onResume() {                 //this is called every time when we return to map from applist
        super.onResume();                    //and outdoor activities

        Log.d("TestMapActivity", "onResume" );
  
    }
    
       
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_file_dialog, menu);
        return true;
    }
        
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
 
        switch (item.getItemId())
        {
        case R.id.showappsoption:
            // Single menu item is selected do something
            // Ex: launching new activity/screen or show alert message
            Toast.makeText(getApplicationContext(), "Show available apps list", Toast.LENGTH_LONG).show();
            
            Intent intent = new Intent(TestMapActivity.this, ApplicationsListActivity.class);
            startActivity(intent);	
            
            return true;
 
        default:
            return super.onOptionsItemSelected(item);    
            
        }//end switch
    }    
    
}