package dictionary.q;

import eHandy.gtbit.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;


public class WordActivity extends Activity {

    private TextView mWord;
    private TextView mDefinition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.word);

        mWord = (TextView) findViewById(R.id.word);
        mDefinition = (TextView) findViewById(R.id.definition);

        Intent intent = getIntent();

        String word = intent.getStringExtra("word");
        String definition = intent.getStringExtra("definition");

        mWord.setText(word);
        mDefinition.setText(definition);
    }
    
    public void onBackPressed()
	{
		Intent intent = new Intent(this, SearchableDictionary.class);
		startActivity(intent);
		WordActivity.this.finish();
	}
    
}
