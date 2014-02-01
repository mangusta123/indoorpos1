package com.example.filedialog;

import java.util.List;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.util.Log;
import android.widget.Toast;

public class WiFiScanReceiver extends BroadcastReceiver {

  TestMapActivity wifiDemo;
  
  static int outdoorMapFlag = 0;
  
  double[] SumsOfDifferencesRSS;
   
  double[] CurPositionRSSList;
  
  static double threshold = -60;   //dBm. this is supposed to depend on particular location 
  
  referPoint refp;
  
  int i,j,k;
  
  public WiFiScanReceiver(TestMapActivity wifiDemo) {
    super();
    this.wifiDemo = wifiDemo;
  }

  @Override
  public void onReceive(Context c, Intent intent) {
    
	  
	SumsOfDifferencesRSS = new double[TestMapActivity.numOfRefPoints];   //initialized to zeros
	
	CurPositionRSSList = new double[TestMapActivity.numOfAps];           //initialized to zeros
	
	
	List<ScanResult> results = wifiDemo.wifi.getScanResults();           //scan results retrieved
    
    Toast.makeText(wifiDemo, "Scanned", Toast.LENGTH_SHORT).show();

	
    if(results.isEmpty())   //nothing to process so return
	   {
        Toast.makeText(wifiDemo, "No APs", Toast.LENGTH_SHORT).show();

    	return;
	   }
	
	
	
    //all necessary comparisons together with possible interpolation techniques are done over here and    	
    //based on results, we update static 'xscrll' and 'yscrll' in AnimatedView class WITH LOCK 
    //scan results give us a list of APs around	
    
	//here we check the maximum RSS among received APs and compare it with some threshold value (in dBm)
	
      
    
    int maxRSS = results.get(0).level;
    
    for(i=1; i<results.size(); i++)
       {
    	
    	if(results.get(i).level > maxRSS)
    	   maxRSS = results.get(i).level;
    	
       }
    
    Toast.makeText(wifiDemo, "Strongest = "+maxRSS, Toast.LENGTH_SHORT).show();

    
	//if it happens to be below this threshold value
	//then we check outdoorMapFlag
	//if it is not set
	//then we call OutdoorMapActivity and set outdoorMapFlag
	//otherwise we do nothing 
	//here receiver returns
	
    if(maxRSS <= threshold)
      {
    	if(outdoorMapFlag==0)
    	  {
    		Intent intent1 = new Intent(wifiDemo, OutdoorMapActivity.class);
            wifiDemo.startActivity(intent1);
    		
    		outdoorMapFlag=1;
    	  }
    	
    	return;
      }
        
    
	//if maximum RSS is above threshold
	//then we check outdoorMapFlag
	//if it is set 
	//then we call TestMapActivity and clear outdoorMapFlag
	//otherwise we do nothing
	
    if(outdoorMapFlag==1)
      {
    	Intent intent1 = new Intent(wifiDemo, TestMapActivity.class);
        wifiDemo.startActivity(intent1);
    	
    	outdoorMapFlag=0;
      }

	//here we proceed to remaining part of receiver (which should be better implemented in Runnable)

	
	for(i=0; i<results.size(); i++)   
	   {
		
		if(TestMapActivity.listOfAPs.contains(results.get(i).BSSID))
					
		   CurPositionRSSList[TestMapActivity.listOfAPs.indexOf(results.get(i).BSSID)] = results.get(i).level;
			
	   }
	
	
	
	for(i=0; i<TestMapActivity.numOfRefPoints; i++)
	   {
		
		refp = TestMapActivity.referPoints.get(i);
						
		for(j=0; j<TestMapActivity.numOfAps; j++)
		   {
			
			SumsOfDifferencesRSS[i] = SumsOfDifferencesRSS[i] + 
					                  Math.abs(CurPositionRSSList[j] - refp.RSSs.get(j));
				   
		   }

	   }
	
	
	    	
    //none of calculated entries can be zero
	
	//zero theoretically implies that we are standing right on some reference point
	
	//but even in that case, there is no reference point having integer values for all its RSSs
	//and our position RSSs always have integer values
	
	
    //i=0;
    
    //while(SumsOfDifferencesRSS[i]==0)
    //i++;
    
    int minRSSindex = 0;  
    
    for(i=1; i<TestMapActivity.numOfRefPoints; i++)
    
        {
           	    
               if(SumsOfDifferencesRSS[i] < SumsOfDifferencesRSS[minRSSindex])
        	  
    		   minRSSindex = i;
    	     
    		    		
        }
    
    //now modify xscrll and yscrll based on this reference point
    
    //also modify the list of available apps based on information provided in the radiomap
    
    
    while(ApplicationsListActivity.lock==1){}
    
    ApplicationsListActivity.lock=1; //list activity onStart or Refresh may want to modify it as well
    ApplicationsListActivity.refPointOnTheGo = minRSSindex;
    ApplicationsListActivity.lock=0;
    
    
    
    
    while(AnimatedView.lock==1)
    {}
    
    AnimatedView.lock = 1; 
    AnimatedView.xscrll = Math.round(TestMapActivity.referPoints.get(minRSSindex).x) * (1630/92) - AnimatedView.xMax/2;
    AnimatedView.yscrll = (1700 - Math.round(TestMapActivity.referPoints.get(minRSSindex).y) * (1490/82)) - AnimatedView.yMax/2;     		
    AnimatedView.lock = 0;
    
    //device canvas range is (0, 0) - (1850, 1700)
    //reference point coordinates range is (0, 0) - (92, 82)

    // (0, 1700) corresponds to (0, 0)
    // (1630, 215) corresponds to (92, 82)
      
    
    
    
  }//end onReceive

}