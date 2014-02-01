package com.example.filedialog;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class LocationPin extends ItemizedOverlay<OverlayItem>{

    private ArrayList<OverlayItem> locationPoint = new ArrayList<OverlayItem>();
    private Context context;


    public LocationPin(Drawable arg0) {
        super(boundCenter(arg0));
        // TODO Auto-generated constructor stub
    }

    public LocationPin(Drawable marker, Context context) {
        this(marker);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected OverlayItem createItem(int i) {
        // TODO Auto-generated method stub
        return locationPoint.get(i);
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return locationPoint.size();
    }

    public void getLocation(OverlayItem item){
        locationPoint.add(item);
        this.populate();
    }

}
