package main;

import eHandy.gtbit.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

public class GalleryActivity extends Activity {
	/** Called when the activity is first created. */
	private Gallery gallery;
	private ImageView imgView;

	private Integer[] Imgid = { R.drawable.a, R.drawable.b, R.drawable.c,
			R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g,
			R.drawable.h, R.drawable.j, R.drawable.k,
			R.drawable.l ,R.drawable.m, R.drawable.n , R.drawable.o,R.drawable.p, R.drawable.q, R.drawable.r, R.drawable.s, R.drawable.t,R.drawable.u };
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_gallery);
		
		//Home Button on Top
		ImageButton homebtn = (ImageButton)findViewById(R.id.homebtn);
		selector(homebtn, R.drawable.icon_home_purple, R.drawable.icon_home);
		homebtn.setOnClickListener(new OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(GalleryActivity.this, WelcomeScreen.class);
				startActivity(in);
				GalleryActivity.this.finish();
			}
		});

		imgView = (ImageView) findViewById(R.id.gallery_ImageView);
		imgView.setImageResource(Imgid[0]);

		gallery = (Gallery) findViewById(R.id.gallery);
		gallery.setAdapter(new AddImgAdp(this));

		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {
				imgView.setImageResource(Imgid[position]);
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

	public class AddImgAdp extends BaseAdapter {
		int GalItemBg;
		private Context cont;

		public AddImgAdp(Context c) {
			cont = c;
			TypedArray typArray = obtainStyledAttributes(R.styleable.GalleryTheme);
			GalItemBg = typArray.getResourceId(
					R.styleable.GalleryTheme_android_galleryItemBackground, 0);
			typArray.recycle();
		}

		public int getCount() {
			return Imgid.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imgView = new ImageView(cont);

			imgView.setImageResource(Imgid[position]);
			imgView.setLayoutParams(new Gallery.LayoutParams(150, 150));
			imgView.setScaleType(ImageView.ScaleType.FIT_XY);
			imgView.setBackgroundResource(GalItemBg);

			return imgView;
		}
	}

	public void onBackPressed() {
		Intent intent = new Intent(this, WelcomeScreen.class);
		startActivity(intent);
		this.finish();
	}


}
