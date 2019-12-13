package com.Ehandy.Quizzer;



import eHandy.gtbit.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class RulesActivity extends Activity implements OnClickListener{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_rules);
		
		//finish button
		Button backBtn = (Button) findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View arg0) {
		/**
		 * if the back button is clicked then go back to the main menu
		 */
		finish();
	}

}


