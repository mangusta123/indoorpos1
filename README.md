indoorpos1
==========

Basement project for Android-based indoor positioning system

Interpolation mechanism is embedded within broadcast receiver called WiFiScanReceiver

Simple interpolation based on Euclidean distance has been used as an initial template which may further be

enhanced to involve more complex interpolation models e.g. based on probability distributions.

In order to use positioning it is required to have 

-image files representing map(s) of indoor location(s) so that the program may automatically switch from 

 one map to the other as the user walks indoors
 
-list of access point MACs with radio signals reaching at least one out of all indoor locations mentioned above

-radiomap consisting of 2-D points over all indoor locations and having information about RSS (Received Signal
 
 Strength) fingerprints from all access points 

Prior to using the map images it is necessary to tune the program so that it works correctly with dimensions of 

Android device and properly shows user's current position on the screen
