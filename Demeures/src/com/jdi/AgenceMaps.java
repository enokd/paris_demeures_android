/* 
 * @author enokd
 */
package com.jdi;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class AgenceMaps extends MapActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.in, R.anim.out);
		setContentView(R.layout.maps);

		MapView myMap = (MapView) findViewById(R.id.mapview);
		myMap.setBuiltInZoomControls(true);
		myMap.getController().setZoom(16);

		List<Overlay> mapOverlays = myMap.getOverlays();
		Drawable drawable = getResources().getDrawable(R.drawable.androidmarker);
		MyOverlayItem itemizedoverlay = new MyOverlayItem(drawable,this);
		
		GeoPoint point = new GeoPoint(48869687,2303263);
		myMap.getController().setCenter(point);
		OverlayItem overlayitem = new OverlayItem(point, "Paris Demeures!", "57 Rue Pierre Charron, Paris!");
		itemizedoverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedoverlay);
		
	}

	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
