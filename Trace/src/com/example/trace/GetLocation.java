package com.example.trace;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class GetLocation implements LocationListener {
	
	private LocationManager locationManager;
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // The minimum distance to change updates in meters  
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // The minimum time between updates in milliseconds  (1 min)
	private Location cLoc=null;
	private double lattitude = 0.0;
	private double longitude = 0.0;
	private boolean isNetworkEnabled=false;
	private boolean isGPSEnabled = false;
	private MainActivity mainActivity;
	private Location location =null;
	
	public GetLocation(MainActivity mainActivity)
	{
		locationManager = (LocationManager) mainActivity.getSystemService(mainActivity.LOCATION_SERVICE);
		isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		this.mainActivity = mainActivity;
	}
	
	public double [] GetCurrentLocation()
	{
		if(!isNetworkEnabled && !isGPSEnabled)
		{
			Toast.makeText(mainActivity, "Turn on GPS!", Toast.LENGTH_LONG).show();
		} 
		else 
		{
			if (isNetworkEnabled) {
	            locationManager.requestLocationUpdates(
	                    LocationManager.NETWORK_PROVIDER,
	                    MIN_DISTANCE_CHANGE_FOR_UPDATES, MIN_TIME_BW_UPDATES, this);
	            
	            if(locationManager!=null) {
	            	location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	            	lattitude =location.getLatitude();
	            	longitude = location.getLongitude();
	            	
	            	Toast.makeText(mainActivity, "Lat: "+lattitude +" Long: "+ longitude , Toast.LENGTH_LONG).show();
	            }
	            else
	            	Toast.makeText(mainActivity, "locationManager object is NULL!", Toast.LENGTH_LONG).show();
	            
	        }
		}
		return new double [] {lattitude,longitude};
	}
	
	
	@Override
	public void onLocationChanged(Location location) {
		lattitude = location.getLatitude();
		longitude = location.getLongitude();
	}
	@Override
	public void onProviderDisabled(String provider) {
		
	}
	@Override
	public void onProviderEnabled(String provider) {
		//TODO: Add code
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
	}

}
