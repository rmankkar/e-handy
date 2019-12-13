package dictionary.q;



import java.util.List;

import learn.student.LearnActivity;

import eHandy.gtbit.R;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TwoLineListItem;

public class SearchableDictionary extends Activity {

    private static final int MENU_SEARCH = 1;

    private TextView mTextView;
    private ListView mList;
    private  ImageButton search_button;
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        setContentView(R.layout.main_search);
        mTextView = (TextView) findViewById(R.id.textField);
        mList = (ListView) findViewById(R.id.list);
        search_button = (ImageButton) findViewById(R.id.srch_btn);
        search_button.setOnClickListener(new OnClickListener() {
        	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onSearchRequested();
			}
        });

        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            // from click on search results
            Dictionary.getInstance().ensureLoaded(getResources());
            String word = intent.getDataString();
            Dictionary.Word theWord = Dictionary.getInstance().getMatches(word).get(0);
            launchWord(theWord);
            finish();
        } else if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            mTextView.setText(getString(R.string.search_results, query));
            WordAdapter wordAdapter = new WordAdapter(Dictionary.getInstance().getMatches(query));
            mList.setAdapter(wordAdapter);
            mList.setOnItemClickListener(wordAdapter);
        }

        Log.d("dict", intent.toString());
        if (intent.getExtras() != null) {
            Log.d("dict", intent.getExtras().keySet().toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_SEARCH, 0, R.string.menu_search)
                .setIcon(android.R.drawable.ic_search_category_default)
                .setAlphabeticShortcut(SearchManager.MENU_KEY);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_SEARCH:
                onSearchRequested();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void launchWord(Dictionary.Word theWord) {
        Intent next = new Intent();
        next.setClass(this, WordActivity.class);
        next.putExtra("word", theWord.word);
        next.putExtra("definition", theWord.definition);
        startActivity(next);
    }

    class WordAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

        private final List<Dictionary.Word> mWords;
        private final LayoutInflater mInflater;

        public WordAdapter(List<Dictionary.Word> words) {
            mWords = words;
            mInflater = (LayoutInflater) SearchableDictionary.this.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return mWords.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            TwoLineListItem view = (convertView != null) ? (TwoLineListItem) convertView :
                    createView(parent);
            bindView(view, mWords.get(position));
            return view;
        }

        private TwoLineListItem createView(ViewGroup parent) {
            TwoLineListItem item = (TwoLineListItem) mInflater.inflate(
                    android.R.layout.simple_list_item_2, parent, false);
            item.getText2().setSingleLine();
            item.getText2().setEllipsize(TextUtils.TruncateAt.END);
            return item;
        }

        private void bindView(TwoLineListItem view, Dictionary.Word word) {
            view.getText1().setText(word.word);
            view.getText2().setText(word.definition);
        }

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            launchWord(mWords.get(position));
        }
    }
    public void onBackPressed()
	{
		Intent intent = new Intent(this, LearnActivity.class);
		startActivity(intent);
		SearchableDictionary.this.finish();
	}
    
    
    
}
