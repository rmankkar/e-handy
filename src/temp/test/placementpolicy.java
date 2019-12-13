package temp.test;

import eHandy.gtbit.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

public class placementpolicy extends Activity {
	
TextView tex1,tex2,tex3;
ScrollView scr1;
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.placementpolicy);


	}
	public void onBackPressed() {
		Intent intent = new Intent(this, TempActivity.class);
		startActivity(intent);
		this.finish();
	}

}
