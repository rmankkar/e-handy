package com.Ehandy.Quizzer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.Ehandy.Quizzer.quiz.Constants;
import com.Ehandy.Quizzer.quiz.GamePlay;
import com.Ehandy.Quizzer.quiz.Helper;

import eHandy.gtbit.R;


public class EndGameActivity extends Activity implements OnClickListener {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_endgame);
		GamePlay currentGame = ((QuizApplication)getApplication()).getCurrentGame();
		String result = "You Got " + currentGame.getRight() + "/" + currentGame.getNumRounds() + ".. ";
		String comment = Helper.getResultComment(currentGame.getRight(), getDifficultySettings());
		
		
		TextView results = (TextView)findViewById(R.id.endgameResult);
		results.setText(result + comment);
		
		int image = Helper.getResultImage(currentGame.getRight(), getDifficultySettings());
		ImageView resultImage = (ImageView)findViewById(R.id.resultPage);
		resultImage.setImageResource(image);
		
		//handle button actions
		Button finishBtn = (Button) findViewById(R.id.finishBtn);
		finishBtn.setOnClickListener(this);
		Button answerBtn = (Button) findViewById(R.id.answerBtn);
		answerBtn.setOnClickListener(this);
		
	}
	
	
	/**
	 * Method to return the difficulty settings
	 * @return
	 */
	private int getDifficultySettings() {
		SharedPreferences settings = getSharedPreferences(Constants.CHOICE, 0);
		int diff = settings.getInt(Constants.SUBJECT, 2);
		return diff;
	}
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 * 
	 * This method is to override the back button on the phone
	 * to prevent users from navigating back in to the quiz
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		switch (keyCode)
		{
		case KeyEvent.KEYCODE_BACK :
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.finishBtn :
			finish();
			break;
			
		case R.id.answerBtn :
			Intent i = new Intent(this, AnswersActivity.class);
			startActivityForResult(i, Constants.PLAYBUTTON);
			break;
		}
	}

}


