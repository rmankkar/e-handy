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

public class ImageAdapter_vid extends BaseAdapter{
	private ArrayList<String> desc;
	private ArrayList<Integer> image;
	private Activity activity;

	public ImageAdapter_vid(Activity activity, ArrayList<String> desc,
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
		public ImageView ViewFl;
		public TextView ViewTi;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder view;
		LayoutInflater inflator = activity.getLayoutInflater();

		if (convertView == null) {
			view = new ViewHolder();
			convertView = inflator.inflate(R.layout.grid_vid, null);

			view.ViewTi = (TextView) convertView
					.findViewById(R.id.textView_vid);
			view.ViewFl = (ImageView) convertView
					.findViewById(R.id.imageView_vid);

			convertView.setTag(view);
		} else {
			view = (ViewHolder) convertView.getTag();
		}

		view.ViewTi.setText(desc.get(position));
		view.ViewFl.setImageResource(image.get(position));

		return convertView;
	}

}
