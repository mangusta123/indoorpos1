package com.example.filedialog;


import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ApplicationsListActivity extends ListActivity{
	
	static int lock=0;
	
	static List<ApplicationClass> list;
	
	static ApplicationsListAdapter adpt;
	
	
	static int refPointOnTheGo =0;       // contains index of closest reference point
	                                     // initially zero
	                                     // SHOULD BE MODIFIED BY RECEIVER, NOT BY AnimatedView
	                                     // Because AnimatedView stops running when this list is being watched

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.app_list);        //display list from app_list.xml
          
      registerForContextMenu(getListView());
     
      Log.d("ApplicationsMapActivity", "onCreate");

      list = new ArrayList<ApplicationClass>();
            
    }
    
    
	@Override
	  public void onStart() {
	  	
		super.onStart();

        Log.d("ApplicationsMapActivity", "onStart" );
        
        
        while(lock==1){}
        
        lock=1; //because receiver may want to modify it as well

        Log.d("ApplicationsMapActivity", "current ref.point index= "+refPointOnTheGo);

        list.clear();       
		    
        int i,j;
        
        for(i=0; i<TestMapActivity.referPoints.get(refPointOnTheGo).appIDs.size(); i++)
        
        {
         for(j=0; j<MainActivity.appList.size(); j++)	
        	
            {
        	 if(TestMapActivity.referPoints.get(refPointOnTheGo).appIDs.get(i)==MainActivity.appList.get(j).AppID)
        	    {
        		 
        	     Log.d("ApplicationsMapActivity", "found appID= "+MainActivity.appList.get(j).AppID);

        		 
        		 list.add(MainActivity.appList.get(j));
        		 
        	    }
        	 
            }
        	
        }
          
	    lock=0;
        
		adpt = new ApplicationsListAdapter(ApplicationsListActivity.this, R.layout.row_app, list);
		
		adpt.notifyDataSetChanged();
		
		setListAdapter(adpt);
	
		
    }//end onStart
	
	@Override
	  public void onListItemClick(ListView parent, View v, int position, long id) {
	
		//here we may launch the clicked application 
		//or do some other action, e.g. make a purchase through online server 	
		
	}
	
	
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_refresh, menu);
        return true;
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
 
        switch (item.getItemId())
        {
        case R.id.refreshoption:
            // Single menu item is selected do something
            // Ex: launching new activity/screen or show alert message
            Toast.makeText(getApplicationContext(), "Refreshing ...", Toast.LENGTH_LONG).show();
      
            while(lock==1){}
            
            lock=1; //because receiver may want to modify it as well
            
            Log.d("ApplicationsMapActivity", "current ref.point index= "+refPointOnTheGo);
            
            list.clear();       
		    
            int i,j;
            
            for(i=0; i<TestMapActivity.referPoints.get(refPointOnTheGo).appIDs.size(); i++)
            
            {
             for(j=0; j<MainActivity.appList.size(); j++)	
            	
                {
            	 if(TestMapActivity.referPoints.get(refPointOnTheGo).appIDs.get(i)==MainActivity.appList.get(j).AppID)
            	    {
            		 
            	     Log.d("ApplicationsMapActivity", "found appID= "+MainActivity.appList.get(j).AppID);

            		 
            		 list.add(MainActivity.appList.get(j));
            		 
            	    }
            	 
                }
            	
            }
            
            lock=0;
            
            adpt.notifyDataSetChanged();
    		
    		setListAdapter(adpt);
                		
            return true;
 
        default:
            return super.onOptionsItemSelected(item);    
            
        }//end switch
    }   
	
	

}
