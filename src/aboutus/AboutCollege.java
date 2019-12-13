package aboutus;

import main.WelcomeScreen;
import eHandy.gtbit.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class AboutCollege extends Activity{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutcollege);
    }
	
	public void onBackPressed()
	{
		Intent intent = new Intent(this, WelcomeScreen.class);
		startActivity(intent);
		this.finish();
	}
}
