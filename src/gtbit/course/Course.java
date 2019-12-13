package gtbit.course;


import eHandy.gtbit.R;
import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Course extends Activity {
    /** Called when the activity is first created. */
	
	TextView title;
	ListView list;
	ArrayAdapter<String> Course_list;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.course_main);

		list = (ListView) findViewById(R.id.listView1);

		String[] names = { "Syllabus",
				"Lab Manual", "Previous Year papers","List of Experiment",
				"Question Bank"
				 };
		list.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, names));
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent;
				switch (arg2) {
				case 0:
					intent = new Intent(Course.this, Syllabus.class);
					startActivity(intent);
					Course.this.finish();

					break;
				case 1:
					intent = new Intent(Course.this, Lab_Manual.class);
					startActivity(intent);
					Course.this.finish();
					break;
				case 2:
					intent = new Intent(Course.this, Papers.class);
					startActivity(intent);
					Course.this.finish();
					break;
				case 3:
					intent = new Intent(Course.this, LOE.class);
					startActivity(intent);
					Course.this.finish();
					break;
				
				case 4:
					intent = new Intent(Course.this, Question_Bank.class);
					startActivity(intent);
					Course.this.finish();
					break;
		
				default:
					
					break;
				}
			}
		});
    }
    public void onBackPressed() {
		Intent intent = new Intent(Course.this, CourseActivity.class);
		startActivity(intent);
		CourseActivity.Flag = 0;
		Course.this.finish();
	}
}