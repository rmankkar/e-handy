package temp.test;


import eHandy.gtbit.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class placement_tips extends Activity {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.placement_tips);
	}
	


public void onBackPressed() {
	Intent intent = new Intent(this, TempActivity.class);
	startActivity(intent);
	this.finish();
}
}