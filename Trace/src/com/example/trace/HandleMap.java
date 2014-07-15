package com.example.trace;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

public class HandleMap {
	
	private PackageManager packageManager;
	MainActivity mainActivity;
	GetLocation location;
	
	public HandleMap(MainActivity mainActivity)
	{
		location = new GetLocation(mainActivity);
		packageManager = mainActivity.getPackageManager();
		this.mainActivity= mainActivity;
	}
	
	public void InvokeGoogMap()
	{
		//is google Maps installed
		boolean mapsInstalled =false;
		try
		{
			PackageInfo pkInfo = packageManager.getPackageInfo("com.google.android.apps.maps", 0);
			mapsInstalled = true;
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		if(mapsInstalled){
			
			 String geoCode = "geo:0,0?q=" + location.GetCurrentLocation()[0] + ","
		                + location.GetCurrentLocation()[1] + "(" + "Here!" + ")";
		        Intent sendLocationToMap = new Intent(Intent.ACTION_VIEW,
		                Uri.parse(geoCode));
		        mainActivity.startActivity(sendLocationToMap);
		}
		else {
			Toast.makeText(mainActivity, "Google Maps not installed!", Toast.LENGTH_SHORT).show();
		}
	}

}
