package rss.feed;


import java.net.URL;
import java.util.List;

import eHandy.gtbit.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import rss.feed.common.Feed;
import rss.feed.storage.DbFeedAdapter;
import rss.feed.storage.DbSchema;
import rss.feed.storage.SharedPreferencesHelper;

public class FeedChannelsActivity extends Activity implements
		OnItemClickListener {
	private static final String LOG_TAG = "FeedChannelsActivity";
	private long errorId = 0;
	private DbFeedAdapter mDbFeedAdapter;
	
	boolean mMustParseFeed = false;
	URL mUrl = null;
	Uri mUri = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mDbFeedAdapter = new DbFeedAdapter(this);
		mDbFeedAdapter.open();
		setContentView(R.layout.channels);

		ListView channelListView = (ListView) findViewById(R.id.channel_list);
		registerForContextMenu(channelListView);
		channelListView.setOnItemClickListener(this);
		
	}
	

	@Override
	protected void onStart() {
		super.onStart();

	}

	@Override
	protected void onResume() {
		super.onResume();
		fillData();
	}

	@Override
	protected void onStop() {
		super.onStop();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mDbFeedAdapter.close();
	}

	private void fillData() {
		getWindow().setTitle(
				getString(R.string.app_name) + " - "
						+ getString(R.string.menu_channels));

		ListView channelListView = (ListView) findViewById(R.id.channel_list);
		List<Feed> feeds = mDbFeedAdapter.getFeeds();
		ChannelsArrayAdapter channelsAdapter = new ChannelsArrayAdapter(this,
				R.id.title, feeds);
		channelListView.setAdapter(channelsAdapter);

		channelListView.setSelection(0);
	}



	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		Feed feed = mDbFeedAdapter.getFeed(id);
		Intent intent = new Intent(this, FeedTabActivity.class);
		intent.putExtra(DbSchema.FeedSchema._ID, id);
		setResult(RESULT_OK);
		startActivity(intent);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.opt_channel_menu_public_mode, menu);
		MenuItem preferencesMenuItem = (MenuItem) menu
				.findItem(R.id.menu_opt_preferences);
		preferencesMenuItem.setIntent(new Intent(this, FeedPrefActivity.class));

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_opt_preferences:
			startActivity(item.getIntent());
			this.finish();
			return true;
	
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		if (v.getId() == R.id.channel_list) {
			menu.setHeaderTitle(R.string.ctx_menu_title);
			MenuInflater inflater = getMenuInflater();
			AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
			Feed feed = mDbFeedAdapter.getFeed(acmi.id);
	
		}
	}

	public boolean onContextItemSelected(MenuItem menuItem) {
		final AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) menuItem
				.getMenuInfo();
		Feed feed = mDbFeedAdapter.getFeed(acmi.id);
	
			return super.onContextItemSelected(menuItem);
		}
	

	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		CharSequence title = null;
		LayoutInflater inflater = null;
		AlertDialog.Builder builder = null;
		switch (id) {
		
	
		case SharedPreferencesHelper.DIALOG_NO_CONNECTION:
			title = getString(R.string.error);
			inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			final View dialogLayout = inflater.inflate(
					R.layout.dialog_no_connection, null);
			builder = new AlertDialog.Builder(this);
			builder.setView(dialogLayout)
					.setTitle(title)
					.setIcon(R.drawable.ic_dialog)
					.setNeutralButton(R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
			dialog = builder.create();
			break;
		default:
			dialog = null;
		}
		return dialog;
	}

	private class UpdateThread extends Thread {

		private URL url;
		private ProgressDialog pd; 

		public UpdateThread(ProgressDialog pd, URL url) {
			this.pd = pd;
			this.url = url;
		} 
	
		
		
		@Override
		public void run() {
			try {
				FeedHandler feedHandler = new FeedHandler(
						FeedChannelsActivity.this);
				Feed handledFeed = feedHandler.handleFeed(url);
				mDbFeedAdapter.addFeed(handledFeed);
				handler.sendEmptyMessage(0);
			} catch (Exception e) {
				Log.e(LOG_TAG, "", e);
				errorId = errorId + 1;
				handler.sendEmptyMessage(1);
			}
		}

		private Handler handler = new Handler() {

			public void handleMessage(Message msg) {
				fillData();
				pd.dismiss();
			
			}
		};
	}

	private static class ChannelsArrayAdapter extends ArrayAdapter<Feed> {
		private LayoutInflater mInflater;

		public ChannelsArrayAdapter(Context context, int textViewResourceId,
				List<Feed> objects) {
			super(context, textViewResourceId, objects);

			mInflater = LayoutInflater.from(context);
		}

		@Override
		public long getItemId(int position) {
			return getItem(position).getId();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
	
				holder = (ViewHolder) convertView.getTag();
		

			Feed feed = getItem(position);
			holder.title.setText(feed.getTitle());
			holder.button.setTag(feed.getId());

			return convertView;
		}

		static class ViewHolder {
			TextView title;
			Button button;
		}
	}
}
