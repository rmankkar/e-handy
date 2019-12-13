package main;

import gtbit.course.CourseActivity;
import it.converter.Main;
import java.util.ArrayList;
import com.Ehandy.Quizzer.SplashActivity;
import com.gps.demo.GPSActivity;
import learn.student.LearnActivity;
import rss.feed.FeedTabActivity;
import temp.test.TempActivity;
import eHandy.gtbit.R;
import aboutus.AboutUsMain;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class WelcomeScreen extends Activity {
	/** Called when the activity is first created. */
	private ImageAdapter mAdapter;
	private ArrayList<String> listCountry;
	private ArrayList<Integer> listFlag;

	private GridView gridView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(eHandy.gtbit.R.layout.mainmenugrid);
		
		if (!isTaskRoot()) {
		    final Intent intent = getIntent();
		    final String intentAction = intent.getAction(); 
		    if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && intentAction != null && intentAction.equals(Intent.ACTION_MAIN))
		    {
		        //Log.w(LOG_TAG, "Main Activity is not the root.  Finishing Main Activity instead of launching.");
		        finish();
		        return;       
		    }
		}

		prepareList();

		// prepared arraylist and passed it to the Adapter class
		mAdapter = new ImageAdapter(this, listCountry, listFlag);

		// Set custom adapter to gridview
		gridView = (GridView) findViewById(R.id.main_grid);
		gridView.setAdapter(mAdapter);

		// Implement On Item click listener
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Intent myIntent = null;
				if (position == 0) {
					myIntent = new Intent(arg1.getContext(),
							FeedTabActivity.class);
					WelcomeScreen.this.finish();
				}
				if (position == 1) {
					myIntent = new Intent(arg1.getContext(),
							CourseActivity.class);
					WelcomeScreen.this.finish();

				}
				if (position == 2) {
					myIntent = new Intent(arg1.getContext(),
							LearnActivity.class);
					WelcomeScreen.this.finish();
				}
				if (position == 3) {
					myIntent = new Intent(arg1.getContext(),
							SplashActivity.class);
					WelcomeScreen.this.finish();

				}
				if (position == 4) {
					myIntent = new Intent(WelcomeScreen.this,
							TempActivity.class);
					WelcomeScreen.this.finish();
				}
				if (position == 5) {
					myIntent = new Intent(arg1.getContext(),
							GalleryActivity.class);
					WelcomeScreen.this.finish();
				}
				if (position == 6) {
					myIntent = new Intent(arg1.getContext(), Main.class);
					WelcomeScreen.this.finish();
				}
				if (position == 7) {
					myIntent = new Intent(arg1.getContext(), SocialMedia.class);
					WelcomeScreen.this.finish();
				}
				if (position == 8) {

					myIntent = new Intent(WelcomeScreen.this, GPSActivity.class);
					WelcomeScreen.this.finish();

				}
				startActivity(myIntent);
			}
		});

	}

	public void prepareList() {
		listCountry = new ArrayList<String>();

		listCountry.add("News");
		listCountry.add("Courses");
		listCountry.add("Learn");
		listCountry.add("Quiz");
		listCountry.add("Placement\n   Guide");
		listCountry.add("Gallery");
		listCountry.add("     Unit\nConverter");
		listCountry.add("    Social\n  Connect");
		listCountry.add("Map");

		listFlag = new ArrayList<Integer>();
		listFlag.add(R.drawable.rss);
		listFlag.add(R.drawable.course);
		listFlag.add(R.drawable.learn);
		listFlag.add(R.drawable.quiz);
		listFlag.add(R.drawable.placement);
		listFlag.add(R.drawable.gallery1);
		listFlag.add(R.drawable.unit);
		listFlag.add(R.drawable.share);
		listFlag.add(R.drawable.map);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.item1:
			Uri uri = Uri.parse("http://www.gtbit.org");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
			break;

		case R.id.item2:
			startActivity(new Intent(this, AboutUsMain.class));
			this.finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onBackPressed() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to exit?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

								WelcomeScreen.this.finish();
							

							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();

	}
}