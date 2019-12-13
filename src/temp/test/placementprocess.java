package temp.test;

import eHandy.gtbit.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

public class placementprocess extends Activity {
	ScrollView sv;
	TextView tvx;

	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.placementprocess);
	    
sv=(ScrollView) findViewById(R.id.scrollView1);
tvx = (TextView) findViewById(R.id.textView1);
}
	public void onBackPressed() {
		Intent intent = new Intent(this, TempActivity.class);
		startActivity(intent);
		this.finish();
	}
}