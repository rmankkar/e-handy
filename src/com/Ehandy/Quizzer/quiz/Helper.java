package com.Ehandy.Quizzer.quiz;

import eHandy.gtbit.R;

public class Helper {
	
	public static String getResultComment(int numCorrect, int diff)
	{
		String comm="";
		int numRounds=15;
		int percentage = calculatePercentage(numCorrect, numRounds);
		
			if (percentage > 90){
				comm = "Well Done!";
			}else if (percentage >= 80){
				comm="Nicely Done!";
			}else if (percentage >= 60){
				comm="Not too bad.";
			}else if (percentage >= 40){
				comm="Well, don't give up..";
			}else{
				comm="Practice Hard";
			}
		
		
		return comm;
	}
	
	public static int getResultImage(int numCorrect, int diff){
		//calculate percentage
		int numRounds=15;
		int percentage = calculatePercentage(numCorrect, numRounds);
		
		//work out which image
		if (percentage > 90){
			
				return R.drawable.hard_winner;
			
			
		}else if (percentage >= 80){
			return R.drawable.prettygood;
		}else if (percentage >= 60){
			return R.drawable.notbad;
		}else if (percentage >= 40){
			return R.drawable.tryagain;
		}else{
			return R.drawable.loser;
		}
	}
	
	private static int calculatePercentage(int numCorrect, int numRounds) {
		double frac = (double)numCorrect/(double)numRounds;
		int percentage = (int) (frac*100);
		return percentage;
	}

}
