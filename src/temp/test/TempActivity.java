package temp.test;

import java.util.ArrayList;
import java.util.Arrays;
import main.WelcomeScreen;
import eHandy.gtbit.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class TempActivity extends Activity {

	private ArrayAdapter<String> listAdapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_placement);
		ImageButton homebtn = (ImageButton)findViewById(R.id.homebtn);
		selector(homebtn, R.drawable.icon_home_yellow, R.drawable.icon_home);
		homebtn.setOnClickListener(new OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(TempActivity.this, WelcomeScreen.class);
				startActivity(in);
				TempActivity.this.finish();
			}
		});
		

		ListView lview1 = (ListView) findViewById(R.id.listView1);
		// Find the ListView resource.

		// Create and populate a List of planet names.
		String[] place = new String[] { "PLACEMENT CELL", "TOP RECRUITERS",
				"PLACEMENT PROCESS","PLACEMENT TIPS", "PLACEMENT POLICY", "WHY RECRUIT @ GTBIT",
				"PLACEMENT RECORD"};
		ArrayList<String> planetList = new ArrayList<String>();
		planetList.addAll(Arrays.asList(place));

		// Create ArrayAdapter using the planet list.
		listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow,
				planetList);

		// Add more planets. If you passed a String[] instead of a List<String>
		// into the ArrayAdapter constructor, you must not add more items.
		// Otherwise an exception will occur.

		// Set the ArrayAdapter as the ListView's adapter.
		lview1.setAdapter(listAdapter);
		lview1.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent;
				switch (arg2) {
				
				case 0:
					intent = new Intent(TempActivity.this, placementcell.class);
					startActivity(intent);
					TempActivity.this.finish();
					break;
				case 1:
					intent = new Intent(TempActivity.this, toprecruiters.class);
					startActivity(intent);
					TempActivity.this.finish();
					break;
				case 2:
					intent = new Intent(TempActivity.this, placementprocess.class);
					startActivity(intent);
					TempActivity.this.finish();
					break;
				case 3:
					intent = new Intent(TempActivity.this, placement_tips.class);
					startActivity(intent);
					TempActivity.this.finish();
					break;
				case 4:
					intent = new Intent(TempActivity.this, placementpolicy.class);
					startActivity(intent);
					TempActivity.this.finish();
					break;
				case 5:
					intent = new Intent(TempActivity.this, whygtbit.class);
					startActivity(intent);
					TempActivity.this.finish();
					break;
				case 6:
					intent = new Intent(TempActivity.this, placement_record.class);
					startActivity(intent);
					TempActivity.this.finish();
					break;
				default:
					break;
				}
				
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