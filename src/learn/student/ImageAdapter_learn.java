package learn.student;

import java.util.ArrayList;

import eHandy.gtbit.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter_learn extends BaseAdapter {
	private ArrayList<String> desc;
	private ArrayList<Integer> image;
	private Activity activity;

	public ImageAdapter_learn(Activity activity, ArrayList<String> desc,
			ArrayList<Integer> image) {
		super();
		this.desc = desc;
		this.image = image;
		this.activity = activity;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return desc.size();
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return desc.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static class ViewHolder {
		public ImageView imgViewFlag;
		public TextView txtViewTitle;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder view;
		LayoutInflater inflator = activity.getLayoutInflater();
		
		if (convertView == null) {
		view = new ViewHolder();
		convertView = inflator.inflate(R.layout.grid_test, null);

		view.txtViewTitle = (TextView) convertView
		.findViewById(R.id.textView_learn);

		view.imgViewFlag = (ImageView) convertView
		.findViewById(R.id.imageView_learn);

		convertView.setTag(view);
		}

		 else {
			view = (ViewHolder) convertView.getTag();
		}

		view.txtViewTitle.setText(desc.get(position));
		view.imgViewFlag.setImageResource(image.get(position));

		return convertView;
	}
}