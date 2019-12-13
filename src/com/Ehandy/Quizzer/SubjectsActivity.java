package com.Ehandy.Quizzer;


import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;

import com.Ehandy.Quizzer.quiz.Constants;

import eHandy.gtbit.R;


public class SubjectsActivity extends Activity implements OnClickListener{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_subjects);
        
        /**
         * set listener on update button
         */
        Button updateBtn = (Button) findViewById(R.id.nextBtn);
		updateBtn.setOnClickListener(this);
		
		/**
		 * Set selected button if saved
		 */
		updateButtonWithPreferences();
		
    }


	/**
	 * Method to update default check box
	 */
	private void updateButtonWithPreferences() {
		RadioButton c1 = (RadioButton)findViewById(R.id.C);
		RadioButton c2 = (RadioButton)findViewById(R.id.Cpp);
		RadioButton c3 = (RadioButton)findViewById(R.id.Java);
		RadioButton c4 = (RadioButton)findViewById(R.id.SQL);
		RadioButton c5 = (RadioButton)findViewById(R.id.MP);
		RadioButton c6 = (RadioButton)findViewById(R.id.Aptitude);
		RadioButton c7 = (RadioButton)findViewById(R.id.LR);
		RadioButton c8 = (RadioButton)findViewById(R.id.VR);
		RadioButton c9 = (RadioButton)findViewById(R.id.VA);
		
		SharedPreferences settings = getSharedPreferences(Constants.CHOICE, 0);
		int diff = settings.getInt(Constants.SUBJECT, Constants.Cplusplus);
		
		switch (diff)
		{
		case Constants.C : 
			c1.toggle();
			break;
			
		case Constants.Cplusplus : 
			c2.toggle();
			break;
			
		case Constants.Java :
			c3.toggle();
			break;	
		}
	}





	@Override
	public void onClick(View arg0) {
		/**
		 * check which settings set and return to menu
		 */
		if (!checkSelected())
		{
			return;
		}
		else
		{
			SharedPreferences settings = getSharedPreferences(Constants.CHOICE, 0);
			Editor e = settings.edit();
			e.putInt(Constants.SUBJECT, getSelectedSetting());
			e.commit();
			finish();
		}
		
	}


	/**
	 * Method to check that a checkbox is selected
	 * 
	 * @return boolean
	 */
	private boolean checkSelected() {
		RadioButton c1 = (RadioButton)findViewById(R.id.C);
		RadioButton c2 = (RadioButton)findViewById(R.id.Cpp);
		RadioButton c3 = (RadioButton)findViewById(R.id.Java);
		RadioButton c4 = (RadioButton)findViewById(R.id.SQL);
		RadioButton c5 = (RadioButton)findViewById(R.id.MP);
		RadioButton c6 = (RadioButton)findViewById(R.id.Aptitude);
		RadioButton c7 = (RadioButton)findViewById(R.id.LR);
		RadioButton c8 = (RadioButton)findViewById(R.id.VR);
		RadioButton c9 = (RadioButton)findViewById(R.id.VA);
		return (c1.isChecked() || c2.isChecked() || c3.isChecked() || c4.isChecked() || c5.isChecked()|| c6.isChecked()||c7.isChecked()||c8.isChecked()||c9.isChecked());
	}


	/**
	 * Get the selected setting 
	 */
	private int getSelectedSetting() {
		RadioButton c1 = (RadioButton)findViewById(R.id.C);
		RadioButton c2 = (RadioButton)findViewById(R.id.Cpp);
		RadioButton c3 = (RadioButton)findViewById(R.id.Java);
		RadioButton c4 = (RadioButton)findViewById(R.id.SQL);
		RadioButton c5 = (RadioButton)findViewById(R.id.MP);
		RadioButton c6 = (RadioButton)findViewById(R.id.Aptitude);
		RadioButton c7 = (RadioButton)findViewById(R.id.LR);
		RadioButton c8 = (RadioButton)findViewById(R.id.VR);
		if (c1.isChecked())
		{
			return Constants.C;
		}
		if (c2.isChecked())
		{
			return Constants.Cplusplus;
		}
		if (c3.isChecked())
		{
			return Constants.Java;
		}
		if (c4.isChecked())
		{
			return Constants.SQL;
		}
		if (c5.isChecked())
		{
			return Constants.DE;
		}
		if (c6.isChecked())
		{
			return Constants.AP;
		}
		if (c7.isChecked())
		{
			return Constants.LR;
		}
		if (c8.isChecked())
		{
			return Constants.VR;
		}
		return Constants.VA;
	}
	
}


