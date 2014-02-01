package com.example.filedialog;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class WiFiScanService extends Service {

	
	WiFiScanRunnable r;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		
		Log.d("WiFiScanService", "service created");
		
		r = new WiFiScanRunnable(getApplicationContext());
		
	}
	
	@Override
	public void onDestroy() {
		
		Log.d("WiFiScanService", "service destroyed");
		
		r.off = 1;
	}
	
	@Override
	public void onStart(Intent intent, int startid) {

		
		new Thread(r).start();
		
		Toast.makeText(getApplicationContext(), "service started", Toast.LENGTH_LONG).show();
		
		Log.d("WiFiScanService", "service started");
		
	}	
}
