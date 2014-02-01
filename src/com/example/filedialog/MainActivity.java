package com.example.filedialog;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;


import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener {
	
	static TextView  mapStat;
    static TextView rmapStat;
    static TextView   apStat;		
	static Button   startPos; 
	
	static List<ApplicationClass> appList = new ArrayList<ApplicationClass>();
	
	
    
    static Handler btnHandler = new Handler() {             //handler to modify text views from dialog
        @Override
        public void handleMessage(Message msg) {
  	
        startPos.setEnabled(true);  
      	              
        }
    };
    
	static Handler txtHandler = new Handler() {             //handler to modify text views from dialog
        @Override
        public void handleMessage(Message msg) {
  	
      	String text = (String)msg.obj;

      	if(text.startsWith("1"))
      		mapStat.setText(text);
      	
   else if(text.startsWith("2"))
	       rmapStat.setText(text);
     
   else if(text.startsWith("3"))
	         apStat.setText(text);
      	              
        }
    };
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
  	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_dialog);
        
        Log.d("MainActivity", "onCreate" );
        
        
        Button  mapButtonDialog = (Button) findViewById(R.id.mapDialogButton);
        Button rmapButtonDialog = (Button) findViewById(R.id.rmapDialogButton);
        Button   apButtonDialog = (Button) findViewById(R.id.apDialogButton);
                       startPos = (Button) findViewById(R.id.startPosit); 
                       startPos.setEnabled(false);	
                       
                       
        mapStat = (TextView) findViewById(R.id.mapStatus);
       rmapStat = (TextView) findViewById(R.id.rmapStatus);
         apStat = (TextView) findViewById(R.id.apStatus);
        
        
        mapButtonDialog.setOnClickListener(this);
       rmapButtonDialog.setOnClickListener(this);
         apButtonDialog.setOnClickListener(this);
               startPos.setOnClickListener(this);
         
         
		FileDialogFragment.loadFileList();      //load all subfolders, txt and image files in /sdcard/ folder
   
		
	//add apps
		
	//we also need to specify which apps are associated with every reference point
		
	//probably in a separate file or in the radiomap	
    
	appList.clear();	
		
	appList.add(new ApplicationClass(1, "application1", ""));	
	appList.add(new ApplicationClass(2, "application2", ""));	
	appList.add(new ApplicationClass(3, "application3", ""));	
	appList.add(new ApplicationClass(4, "application4", ""));	
	appList.add(new ApplicationClass(5, "application5", ""));	
	appList.add(new ApplicationClass(6, "application6", ""));	
	appList.add(new ApplicationClass(7, "application7", ""));	
	appList.add(new ApplicationClass(8, "application8", ""));	
	appList.add(new ApplicationClass(9, "application9", ""));	
	appList.add(new ApplicationClass(10,"application10",""));	

    
    }// end onCreate
    
    
    @Override
    public void onResume() {
        super.onResume();

        Log.d("MainActivity", "onResume" );
  
    }
        
    
    public void onClick(View view) {
    	
    switch(view.getId()){
    
            case R.id.mapDialogButton: 
			
            FileDialogFragment.whatToSelect = 1;	
		    break;
	
            case R.id.rmapDialogButton:
            	
            FileDialogFragment.whatToSelect = 2;
            break;
            
            case R.id.apDialogButton:
            	
            FileDialogFragment.whatToSelect = 3;
            break;
                		
            case R.id.startPosit:
            	
            //now we move to map activity which shows us our position within the loaded map
            	
            Intent intent = new Intent(MainActivity.this, TestMapActivity.class);
            startActivity(intent);	
                        
            break;	
            
    }//end switch
	
    if(view.getId()==R.id.mapDialogButton||view.getId()==R.id.rmapDialogButton||view.getId()==R.id.apDialogButton){
    
    FileDialogFragment diag = new FileDialogFragment();
	diag.show(getSupportFragmentManager(), "files");
    }
	
    }//end onClick 
    		
    		
      
    
}
