package temp.test;

import eHandy.gtbit.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class placementcell extends Activity {
	 public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.placementcell);
}
	 public void onBackPressed() {
			Intent intent = new Intent(this, TempActivity.class);
			startActivity(intent);
			this.finish();
		}
}
