package com.Ehandy.Quizzer;


import java.io.IOException;
import java.util.List;

import main.GalleryActivity;
import main.WelcomeScreen;

import quiz.db.DBHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.Ehandy.Quizzer.quiz.Constants;
import com.Ehandy.Quizzer.quiz.GamePlay;
import com.Ehandy.Quizzer.quiz.Question;

import eHandy.gtbit.R;

public class SplashActivity extends Activity implements OnClickListener{

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_quiz);
		Button playBtn = (Button) findViewById(R.id.playBtn);
		playBtn.setOnClickListener(this);
		Button settingsBtn = (Button) findViewById(R.id.settingsBtn);
		settingsBtn.setOnClickListener(this);
		Button rulesBtn = (Button) findViewById(R.id.rulesBtn);
		rulesBtn.setOnClickListener(this);
		
		ImageButton homebtn = (ImageButton)findViewById(R.id.homebtn);
		selector(homebtn, R.drawable.icon_home_turquise, R.drawable.icon_home);
		homebtn.setOnClickListener(new OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(SplashActivity.this, WelcomeScreen.class);
				startActivity(in);
				SplashActivity.this.finish();
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

	/**
	 * Listener for game menu
	 */
	@Override
	public void onClick(View v) {
		Intent i;
		
		switch (v.getId()){
		case R.id.playBtn :
			//once logged in, load the main page
			Log.d("LOGIN", "User has started the game");
			
			//Get Question set //
			List<Question> questions = getQuestionSetFromDb();

			//Initialise Game with retrieved question set ///
			GamePlay c = new GamePlay();
			c.setQuestions(questions);
			c.setNumRounds(getNumQuestions());
			((QuizApplication)getApplication()).setCurrentGame(c);  

			//Start Game Now.. //
			i = new Intent(this, QuestionActivity.class);
			startActivityForResult(i, Constants.PLAYBUTTON);
			break;
			
		case R.id.rulesBtn :
			i = new Intent(this, RulesActivity.class);
			startActivityForResult(i, Constants.RULESBUTTON);
			break;
			
		case R.id.settingsBtn :
			i = new Intent(this, SubjectsActivity.class);
			startActivityForResult(i, Constants.SUBJBUTTON);
			break;
			
		
		}

	}


	
	private List<Question> getQuestionSetFromDb() throws Error {
		int diff = getDifficultySettings();
		int numQuestions = getNumQuestions();
		DBHelper myDbHelper = new DBHelper(this);
		try {
			myDbHelper.createDataBase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}
		try {
			myDbHelper.openDataBase();
		}catch(SQLException sqle){
			throw sqle;
		}
		List<Question> questions = myDbHelper.getQuestionSet(diff, numQuestions);
		myDbHelper.close();
		return questions;
	}


	
	private int getDifficultySettings() {
		SharedPreferences settings = getSharedPreferences(Constants.CHOICE, 0);
		int diff = settings.getInt(Constants.SUBJECT, Constants.Cplusplus);
		return diff;
	}

	
	private int getNumQuestions() {
		SharedPreferences settings = getSharedPreferences(Constants.CHOICE, 0);
		int numRounds = 15;
		return numRounds;
	}
	
	 public void onBackPressed() {
			Intent intent = new Intent(this, main.WelcomeScreen.class);
			startActivity(intent);
			this.finish();
		}

}

