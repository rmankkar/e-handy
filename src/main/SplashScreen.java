package main;

import eHandy.gtbit.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		// oursong=MediaPlayer.create(SplashScreen.this,R.raw.smooth);
		// oursong.start();
		final Intent in = new Intent(this, WelcomeScreen.class);
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(3000);
					startActivity(in);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {

				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// oursong.release();
		finish();
	}
}
