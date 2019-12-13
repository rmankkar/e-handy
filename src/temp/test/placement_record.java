package temp.test;

import eHandy.gtbit.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class placement_record extends Activity {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.placement_record);
	}
	public void onBackPressed() {
		Intent intent = new Intent(this, TempActivity.class);
		startActivity(intent);
		this.finish();
	}
}
