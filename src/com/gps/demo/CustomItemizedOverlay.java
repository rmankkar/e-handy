package com.gps.demo;
import java.util.ArrayList;

import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;
public class CustomItemizedOverlay extends ItemizedOverlay<OverlayItem>{
	private final ArrayList<OverlayItem> mapOverlays = new ArrayList<OverlayItem>();

	public CustomItemizedOverlay(Drawable defaultMarker) {
		 super(boundCenterBottom(defaultMarker));
		// TODO Auto-generated constructor stub
	}

	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		 return mapOverlays.get(i);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return mapOverlays.size();
	}
	 public void addOverlay(OverlayItem overlay) {
         mapOverlays.add(overlay);
         this.populate();
     }
}
