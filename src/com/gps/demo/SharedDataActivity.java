package com.gps.demo;

import eHandy.gtbit.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SharedDataActivity extends Activity{
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.andrian);
	        Bundle bundle = getIntent().getExtras();
	        double src_lat=bundle.getDouble("lat");
	        double src_long=bundle.getDouble("long");
	        SharedData data = SharedData.getInstance();
	
	        data.setAPIKEY("0hX3QOjBX-nHlE8ob2K3aSZCGa6QnLk6orbMw4A");
	        data.setSrc_lat(src_lat);
	        data.setSrc_lng(src_long);
	        data.setDest_lat(28.6315029);// GTBIT
	        data.setDest_lng(77.11635230000002);
	      
	        startActivity(new Intent(SharedDataActivity.this,RoutePath.class));
	        SharedDataActivity.this.finish();
	    }
		
	

}
