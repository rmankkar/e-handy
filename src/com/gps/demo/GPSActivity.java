package com.gps.demo;

import main.WelcomeScreen;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import eHandy.gtbit.R;

public class GPSActivity extends Activity {
	Button btnShowLocation;
	GPSTracker gps;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_map);
		btnShowLocation = (Button) findViewById(R.id.button1);
		ImageButton homebtn = (ImageButton) findViewById(R.id.homebtn);
		selector(homebtn, R.drawable.icon_home_sky, R.drawable.icon_home);
		homebtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(GPSActivity.this, WelcomeScreen.class);
				startActivity(in);
				GPSActivity.this.finish();
			}
		});

		// show location button click event
		btnShowLocation.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// create class object
				gps = new GPSTracker(GPSActivity.this);

				// check if GPS enabled
				if (gps.canGetLocation()) {

					double latitude = gps.getLatitude();
					double longitude = gps.getLongitude();

					// \n is for new line
					// Toast.makeText(getApplicationContext(),
					// "Your Location is - \nLat: " + latitude + "\nLong: " +
					// longitude, Toast.LENGTH_LONG).show();
					Intent intent = new Intent(GPSActivity.this,
							SharedDataActivity.class);
					Bundle b = new Bundle();
					b.putDouble("lat", latitude);
					b.putDouble("long", longitude);
					intent.putExtras(b);
					startActivity(intent);
					GPSActivity.this.finish();
				} else {
					// can't get location
					// GPS or Network is not enabled
					// Ask user to enable GPS/network in settings
					gps.showSettingsAlert();
				}

			}
		});
	}

	
	//Home Button Focus function
	public void selector(ImageButton b,int pressed_image,int normal_image )
    {
        StateListDrawable states = new StateListDrawable();
        states.addState(new int[] {android.R.attr.state_pressed},
            getResources().getDrawable(pressed_image));         
       states.addState(new int[] { },
            getResources().getDrawable(normal_image));      
        b.setBackgroundDrawable(states);
    }
	public void onBackPressed() {
		Intent intent = new Intent(this, WelcomeScreen.class);
		startActivity(intent);
		GPSActivity.this.finish();
	}
}
