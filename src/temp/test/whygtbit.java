package temp.test;

import eHandy.gtbit.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

public class whygtbit extends Activity{
	TextView tev1,tev2;
    ScrollView srv;	

	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.whygtbit);
	 tev1 = (TextView)findViewById(R.id.textView1);
	 tev2 = (TextView) findViewById(R.id.textView2);
	 srv = (ScrollView) findViewById(R.id.scrollView1);
	}
	public void onBackPressed() {
		Intent intent = new Intent(this, TempActivity.class);
		startActivity(intent);
		this.finish();
	}

}
