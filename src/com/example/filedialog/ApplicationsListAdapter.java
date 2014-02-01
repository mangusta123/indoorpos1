package com.example.filedialog;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * ListView Friends ArrayAdapter
 */
public class ApplicationsListAdapter extends ArrayAdapter<ApplicationClass> {
    private final Activity context;
    private final List<ApplicationClass> applications;
    private int resourceId;

  
    public ApplicationsListAdapter(
            Activity context, 
            int resourceId, 
            List<ApplicationClass> applications) {
        super(context, resourceId, applications);
        this.context = context;
        this.applications = applications;
        this.resourceId = resourceId;
    }

    /**
     * Updates the view
     * @param position the ArrayList position to update
     * @param convertView the view to update/inflate if needed
     * @param parent the groups parent view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = vi.inflate(resourceId, null);
        }
        String f = applications.get(position).AppName;
        TextView rowTxt = (TextView) rowView.findViewById(R.id.rowtext_app);
        rowTxt.setText(f);
        return rowView;
    }

}   

