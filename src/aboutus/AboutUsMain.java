package aboutus;

import main.WelcomeScreen;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import eHandy.gtbit.R;

public class AboutUsMain extends TabActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutusmain);

		TabHost tabHost = getTabHost();

		// Tab for About College
		TabSpec clgspec = tabHost.newTabSpec("About College");
		// setting Title and Icon for the Tab
		clgspec.setIndicator("About College");
		Intent clgIntent = new Intent(this, AboutCollege.class);
		clgspec.setContent(clgIntent);

		// Tab for About Devs
		TabSpec devspec = tabHost.newTabSpec("About Devs");
		devspec.setIndicator("About Devs");
		Intent devIntent = new Intent(this, AboutDevs.class);
		devspec.setContent(devIntent);

		// Adding all TabSpec to TabHost
		tabHost.addTab(clgspec); // Adding photos tab
		tabHost.addTab(devspec); // Adding songs tab
		
		//Home Button on Top
		ImageButton homebtn = (ImageButton)findViewById(R.id.homebtn);
		selector(homebtn, R.drawable.icon_home_turquise, R.drawable.icon_home);
		homebtn.setOnClickListener(new OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(AboutUsMain.this, WelcomeScreen.class);
				startActivity(in);
				AboutUsMain.this.finish();
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
	
	public void onBackPressed()
	{
		Intent intent = new Intent(this, WelcomeScreen.class);
		startActivity(intent);
		this.finish();
	}
}
