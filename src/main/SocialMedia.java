package main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;
import eHandy.gtbit.R;

public class SocialMedia extends Activity {
	Intent intent = new Intent("android.intent.category.LAUNCHER");

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(eHandy.gtbit.R.layout.socialmedia);
		
		//Home Button on Top
		ImageButton homebtn = (ImageButton)findViewById(R.id.homebtn);
		selector(homebtn, R.drawable.icon_home_green, R.drawable.icon_home);
		homebtn.setOnClickListener(new OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(SocialMedia.this, WelcomeScreen.class);
				startActivity(in);
				SocialMedia.this.finish();
			}
		});
		
		
        ImageButton fb = (ImageButton) findViewById(R.id.facebook_btn);
        fb.setOnClickListener (new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					
					intent.setClassName("com.facebook.katana", "com.facebook.katana.LoginActivity");
					startActivity(intent);
					 
					String uri = "fb://page/479991402060984" ; 
					Intent intent_fb = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
					startActivity(intent_fb);

	            	
				} catch (Exception e) {
					// TODO Auto-generated catch block
            		Toast.makeText(SocialMedia.this, R.string.error_facebook,Toast.LENGTH_LONG).show();

					e.printStackTrace();
				}   
			}
        	
        });
		
        ImageButton tweet = (ImageButton) findViewById(R.id.twitter_btn);
        tweet.setOnClickListener (new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					String uri = "twitter://twitter.com/EHandyy" ; 
					Intent intent_tw = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
					startActivity(intent_tw);
	}
	            	
	            	catch (Exception e) {
						// TODO Auto-generated catch block
	            		Toast.makeText(SocialMedia.this, R.string.error_twitter,Toast.LENGTH_LONG).show();
						e.printStackTrace();
					}   
			}
        	
        });
    
        ImageButton gplus = (ImageButton) findViewById(R.id.googleplus_btn);
        gplus.setOnClickListener (new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent gplus = new Intent (SocialMedia.this, gplus.class);
			startActivity(gplus);
			SocialMedia.this.finish();
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
		this.finish();
	}

}
