indoorpos1
==========

Basement Android project for fingerprint-based indoor positioning system

Interpolation mechanism is embedded within broadcast receiver called WiFiScanReceiver.

Simple interpolation based on Euclidean distance has been used as an initial template which may further be

enhanced to involve more complex interpolation models e.g. based on probability distributions.

In order to use positioning it is required to have 

-image files representing map(s) of indoor location(s) so that the program may automatically switch from 

 one map to the other as the user walks indoors; when the user leaves outdoors away from map-covered area, the 
 
 program launches GPS-based outdoor positioning and stays in that mode until the user is back indoors
 
-list of access point MACs with radio signals reaching at least one out of all indoor locations mentioned above

-radiomap consisting of 2-D points over all indoor locations and having information about RSS (Received Signal
 
 Strength) fingerprints from all access points 
 
Examples are included in the project's main trunk.

These data are supposed to be fetched on-the-fly from a remote storage, preferably NoSQL-based document-oriented

DB to enhance data availability and make the program better fit real-time constraints.

Prior to using the map images it is necessary to tune the program so that it works correctly with dimensions of 

Android device and properly shows user's current position on the screen.

