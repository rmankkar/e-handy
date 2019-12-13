package com.Ehandy.Quizzer;

import quiz.util.Utility;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.Ehandy.Quizzer.quiz.GamePlay;
import com.Ehandy.Quizzer.QuizApplication;
import eHandy.gtbit.R;
public class AnswersActivity extends Activity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_answers);
		GamePlay currentGame = ((QuizApplication)getApplication()).getCurrentGame();
		
		TextView results = (TextView)findViewById(R.id.answers);
		String answers = Utility.getAnswers(currentGame.getQuestions());
		results.setText(answers);
		
		//handle button actions
		Button finishBtn = (Button) findViewById(R.id.finishBtn);
		finishBtn.setOnClickListener(this);
		
	}
	
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
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.finishBtn :
			finish();
		}
		
	}

}
