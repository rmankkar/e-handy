package learn.student;


import java.util.ArrayList;

import dictionary.q.SearchableDictionary;
import main.WelcomeScreen;
import eHandy.gtbit.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.AdapterView.OnItemClickListener;

public class LearnActivity extends Activity {
    /** Called when the activity is first created. */

    private ImageAdapter_learn imgAdapter;
    private ArrayList<String> desc;
    private ArrayList<Integer> image;
 
    private GridView gridView_learn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_learn);
        prepareList();
        
        ImageButton homebtn = (ImageButton)findViewById(R.id.homebtn);
        selector(homebtn, R.drawable.icon_home_orange, R.drawable.icon_home);
		homebtn.setOnClickListener(new OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(LearnActivity.this, WelcomeScreen.class);
				startActivity(in);
				LearnActivity.this.finish();
			}
		});
        
        // prepared arraylist and passed it to the Adapter class
        imgAdapter = new ImageAdapter_learn(this,desc, image);
 
        // Set custom adapter to gridview
        gridView_learn = (GridView) findViewById(R.id.gridView_learn);
        gridView_learn.setAdapter(imgAdapter);
        // Implement On Item click listener
        gridView_learn.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                    long arg3) {
            	Intent myIntent = null;
                if(position == 0){
                    myIntent = new Intent(arg1.getContext(), video.class);
                    LearnActivity.this.finish();
                }
                if(position == 1){
                    myIntent = new Intent(arg1.getContext(), ebooks.class);
                    LearnActivity.this.finish();
                }
                if(position == 2){
                    myIntent = new Intent(arg1.getContext(), SearchableDictionary.class);
                    LearnActivity.this.finish();
                }
            
               
                startActivity(myIntent);
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
    
    public void prepareList()
    {
          desc = new ArrayList<String>();
 
          desc.add("Video Tutorial");
          desc.add("eBooks");
          desc.add("Dictionary");

          
 
          image = new ArrayList<Integer>();
          image.add(R.drawable.video);
          image.add(R.drawable.ebook);
          image.add(R.drawable.dictionary);
    }
    public void onBackPressed() {
		Intent intent = new Intent(this, WelcomeScreen.class);
		startActivity(intent);
		this.finish();
	}
}