package com.example.filedialog;

import java.io.File;import java.io.FilenameFilter;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.Toast;

public class FileDialogFragment extends DialogFragment {

	//In an Activity
	public static String[] mFileList;
	public static File mPath = new File(Environment.getExternalStorageDirectory().toString()+"/");
	
	static int whatToSelect = 0; 
	
	static int selected1 = 0;
	static int selected2 = 0;
	static int selected3 = 0;
	
	public static String mChosenMapFile;
	public static String mChosenRMapFile;
	public static String mChosenAPFile;
	
	private static final String FTYPE1 = ".txt";    
	private static final String FTYPE2 = ".jpg";
  	private static final String FTYPE3 = ".png";
  	private static final String FTYPE4 = ".bmp";

  	public static void loadFileList() {

	    if(mPath.exists()) {
	        FilenameFilter filter = new FilenameFilter() {
	            public boolean accept(File dir, String filename) {
	                File sel = new File(dir, filename);
	                return filename.contains(FTYPE1) || filename.contains(FTYPE2)||
	                	   filename.contains(FTYPE3) || filename.contains(FTYPE4)|| sel.isDirectory();
	            }
	        };
	        mFileList = mPath.list(filter);
	    }
	    else {
	        mFileList= new String[0];
	    }
	}
    	
	
	public Dialog onCreateDialog(Bundle savedBundleInstance) {
	    Dialog dialog = null;
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

	    
	            builder.setTitle("Choose your file");
	            if(mFileList == null) {
	                Log.e("fileDialog", "Showing file picker before loading the file list");
	                dialog = builder.create();
	                return dialog;
	            }
	            builder.setItems(mFileList, new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int which) {
	                    
	                Message msg1 = new Message();
	                Message msg2 = new Message();
	                
	                
	                switch(whatToSelect){
	                
	                case 1:
	                	mChosenMapFile = mFileList[which];
                	    
	                	selected1 = 1;
                	    if(selected1==1&&
                	       selected2==1&&
                	       selected3==1)
                	    {  msg2.obj = "sendmsg";
 	                       MainActivity.btnHandler.sendMessage(msg2);
                	    }
                	    
	                    msg1.obj = "1. CHOSEN MAP FILE => "+Environment.getExternalStorageDirectory().toString()+"/"+mChosenMapFile;
 	                    MainActivity.txtHandler.sendMessage(msg1);
                        break;
	                
	                case 2:
	                	mChosenRMapFile = mFileList[which];
	                	
	                	selected2 = 1;
                	    if(selected1==1&&
                	       selected2==1&&
                	       selected3==1)
                	    {  msg2.obj = "sendmsg";
 	                       MainActivity.btnHandler.sendMessage(msg2);
                	    } 
                	    
	                    msg1.obj = "2. CHOSEN RMAP FILE => "+Environment.getExternalStorageDirectory().toString()+"/"+mChosenRMapFile;
 	                    MainActivity.txtHandler.sendMessage(msg1);
	                    break;
	                
	                case 3:
	                    mChosenAPFile = mFileList[which];
	                    
	                    selected3 = 1;
                	    if(selected1==1&&
                	       selected2==1&&
                	       selected3==1)
                	    {  msg2.obj = "sendmsg";
 	                       MainActivity.btnHandler.sendMessage(msg2);
                	    }
                	    
	                    msg1.obj = "3. CHOSEN AP FILE => "+Environment.getExternalStorageDirectory().toString()+"/"+mChosenAPFile;
 	                    MainActivity.txtHandler.sendMessage(msg1);
	                    break;
	                                    }//end switch
	                }
	            });
	           
	    dialog = builder.show();
	    return dialog;
	}
	
}
