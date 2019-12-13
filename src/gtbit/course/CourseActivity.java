package gtbit.course;

import main.WelcomeScreen;
import eHandy.gtbit.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

public class CourseActivity extends Activity implements OnItemSelectedListener {
    /** Called when the activity is first created. */
	static int Flag = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_spinner);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ImageButton homebtn = (ImageButton)findViewById(R.id.homebtn);
        selector(homebtn, R.drawable.icon_home_darkg, R.drawable.icon_home);
		homebtn.setOnClickListener(new OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(CourseActivity.this, WelcomeScreen.class);
				startActivity(in);
				CourseActivity.this.finish();
			}
		});
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.sem_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        
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

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		if (arg2==1)
		{ Flag = 1;
		Intent in=new Intent(this,Course.class);
		startActivity(in);
		this.finish();
		}
		if (arg2==2)
		{
			Flag = 2;
		Intent in=new Intent(this,Course.class);
		startActivity(in);
		this.finish();
		}
		if (arg2==3)
		{ Flag = 3;
		Intent in=new Intent(this,Course.class);
		startActivity(in);
		this.finish();
		}
		if (arg2==4)
		{
			Flag = 4;
		Intent in=new Intent(this,Course.class);
		startActivity(in);
		this.finish();
		}
		if (arg2==5)
		{ Flag = 5;
		Intent in=new Intent(this,Course.class);
		startActivity(in);
		this.finish();
		}
		if (arg2==6)
		{
			Flag = 6;
		Intent in=new Intent(this,Course.class);
		startActivity(in);
		this.finish();
		}
		if (arg2==7)
		{ Flag = 7;
		Intent in=new Intent(this,Course.class);
		startActivity(in);
		this.finish();
		}
		if (arg2==8)
		{
			Flag = 8;
		Intent in=new Intent(this,Course.class);
		startActivity(in);
		this.finish();
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	public static int retFlag() {
		return Flag;
	}
	
	 public void onBackPressed() {
			Intent ex = new Intent(this, WelcomeScreen.class);
			startActivity(ex);
			this.finish();
		}
	
	
}