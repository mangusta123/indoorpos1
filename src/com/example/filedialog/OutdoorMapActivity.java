package com.example.filedialog;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class OutdoorMapActivity extends MapActivity implements LocationListener { 
    
	   private LocationManager mgr;
	   private String best;

	   private MapView mapView;
	   private MapController mc;   
	   GeoPoint p;
	   
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_outdoor);

	      //now set up client connection////////////////////////////
	       try {
	    	  
		  
	      mgr = (LocationManager) getSystemService(LOCATION_SERVICE); 
	      Criteria criteria = new Criteria(); 
	      best = mgr.getBestProvider(criteria, true);
	  
	      mapView = (MapView) findViewById(R.id.mapview1);

	      mc = mapView.getController(); 
	      
	      mapView.setSatellite(true);
	            
	      Location loc = mgr.getLastKnownLocation(best);   // last position (immediate information), 
	                                                       // send it to server (to implement)
	   
	      getBaseContext();
	      
	      if (loc != null) {                
	          	                 
	          p = new GeoPoint(
	                  (int) (loc.getLatitude() * 1E6), 
	                  (int) (loc.getLongitude() * 1E6));
	          mc.animateTo(p);
	                    
	       OverlayItem locationIcon = new OverlayItem(p, null, null);   
	       
	       LocationPin myLocPin = new LocationPin(OutdoorMapActivity.this.getResources().getDrawable(R.drawable.marker), 
	       		                               OutdoorMapActivity.this);   
	      
	       myLocPin.getLocation(locationIcon); 

           mapView.getOverlays().clear();
	       
	       mapView.getOverlays().add(myLocPin); 
	                      
	       //   MapOverlay mapOverlay = new MapOverlay();
	       //   mapView.getOverlays().add(mapOverlay);
	              
	       mapView.invalidate();
	      
		   }
	            
	      //////////////////////////////////////////////////////////
	   
	        
	      }
	      catch(Exception e){
	    	  
	          Log.d(LOCATION_SERVICE, "exception occured");
	        	  
	          } 
	      
	   }
	   
	   public boolean onKeyDown(int keyCode, KeyEvent event) 
	   {
	       switch (keyCode) 
	       {
	           case KeyEvent.KEYCODE_3:
	               mc.zoomIn();
	               break;
	           case KeyEvent.KEYCODE_1:
	               mc.zoomOut();  
	               break;
	       }
	       return super.onKeyDown(keyCode, event);
	   }  
	   
	   
	   @Override
	   protected void onResume() {
	      super.onResume();
	      // Start updates (doc recommends delay >= 60000 ms)
	      mgr.requestLocationUpdates(best, 5000, 10, this);
	   }

	   @Override
	   protected void onPause() {
	      super.onPause();
	      // Stop updates to save power while app paused
	      mgr.removeUpdates(this);
	   }
	   
	  
	   protected boolean isRouteDisplayed() {
	       // TODO Auto-generated method stub
	       return false;
	   } 
	   
	   public void onLocationChanged(Location loc) {
		   
		   if (loc != null) {                
           
	           p = new GeoPoint(
	                   (int) (loc.getLatitude() * 1E6), 
	                   (int) (loc.getLongitude() * 1E6));
	           mc.animateTo(p);
	                     
	        OverlayItem locationIcon = new OverlayItem(p, null, null);   //new marker (OverlayItem) according to new coordinates
	        
	        LocationPin myLocPin = new LocationPin(OutdoorMapActivity.this.getResources().getDrawable(R.drawable.marker), 
	        		                               OutdoorMapActivity.this);   //arraylist of OverlayItems
	       
	        myLocPin.getLocation(locationIcon); //add new marker to this list (ItemizedOverlay)
	        
	        mapView.getOverlays().clear();
	        
	        mapView.getOverlays().add(myLocPin); //add ItemizedOverlay to overall mapView overlay collection
	                       
	        //   MapOverlay mapOverlay = new MapOverlay();
	        //   mapView.getOverlays().add(mapOverlay);
	               
	        Log.d(LOCATION_SERVICE, "overlay number = "+mapView.getOverlays().size());
	        mapView.invalidate();
	       
		   }
		       
	   }

	   public void onProviderDisabled(String provider) {
	   }

	   public void onProviderEnabled(String provider) {
	   }

	   public void onStatusChanged(String provider, int status,
	         Bundle extras) {
	      
	   }
	   
	   
	}
