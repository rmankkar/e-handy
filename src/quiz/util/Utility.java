package quiz.util;

import java.util.List;

import com.Ehandy.Quizzer.quiz.Question;

public class Utility {
	public static String capitalise(String s){
		if (s==null || s.length()==0) return s;
		
		String s1 = s.substring(0, 1).toUpperCase() + s.substring(1);
		return s1;
	}
	
	public static String getAnswers(List<Question> questions) {
		int question = 1;
		StringBuffer sb = new StringBuffer();
		
		for (Question q : questions){
			sb.append("Q").append(question).append(") ").append(q.getQuestion()).append("? \n");
			sb.append("Answer: ").append(q.getAnswer()).append("\n\n");
			question ++;
		}
		
		return sb.toString();
	}

}
