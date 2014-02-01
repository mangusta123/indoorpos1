package com.example.filedialog;

import java.util.*;

public class referPoint {

	
	float x;     // x coordinate
	float y;     // y coordinate
	float z;     // z coordinate
	
	List<Double> RSSs;      // list of received signal strengths (RSS) at this reference point
	
	List<Integer> appIDs;   // list of app IDs available at the proximity of reference point
	
	
	public referPoint(String radioMapLine){    // this constructor parses given radiomap line
		                                       // and initializes instance variables. 
                                               // note that the number of RSS values equals 'numOfAps' calculated  		
	                                           // in TokenTestClass
	
	RSSs = new ArrayList<Double>();
	
	appIDs = new ArrayList<Integer>();
	
	StringTokenizer tok = new StringTokenizer(radioMapLine," \t");
	
	x = Float.parseFloat(tok.nextToken());    // initialize x coordinate
	
	y = Float.parseFloat(tok.nextToken());    // initialize y coordinate
	
	z = Float.parseFloat(tok.nextToken());    // initialize z coordinate
	
	int i;
	String tmp;
	                                          
	for(i=0; i<TestMapActivity.numOfAps; i++)  // populate RSS list
	{
		if((tmp=tok.nextToken()).equals("NaN"))
		
	        RSSs.add((double)0);
		
		else
			RSSs.add(Double.parseDouble(tmp));		
	}
	
	//here populate number of available apps based on reference point
	
	if(!tok.hasMoreTokens())
		System.out.println("no more tokens. no apps available");
	
	
	
	while(tok.hasMoreTokens())
	 
		  appIDs.add(Integer.parseInt(tok.nextToken()));
	 
	
		
	}//end of constructor
	
	
}
