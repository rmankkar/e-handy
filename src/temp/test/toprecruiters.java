package temp.test;

import eHandy.gtbit.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class toprecruiters extends Activity{
	TextView t1,t2,t3,t4;
	ImageView i1,i2,i3;
	 public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.toprecruiters);
		   
	 }
	 public void onBackPressed() {
			Intent intent = new Intent(this, TempActivity.class);
			startActivity(intent);
			this.finish();
		}

}
