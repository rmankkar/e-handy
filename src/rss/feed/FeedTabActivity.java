package rss.feed;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import main.WelcomeScreen;

import org.xml.sax.SAXException;

import eHandy.gtbit.R;

import aboutus.AboutUsMain;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.OnTabChangeListener;

import rss.feed.common.Feed;
import rss.feed.common.Item;
//import rss.feed.common.TrackerAnalyticsHelper;
import rss.feed.storage.DbFeedAdapter;
import rss.feed.storage.DbSchema;
import rss.feed.storage.SharedPreferencesHelper;

public class FeedTabActivity extends TabActivity implements OnItemClickListener {
	private static final String LOG_TAG = "FeedTabActivity";
	private long errorId = 0;
	
	private static final String TAB_CHANNEL_TAG = "tab_tag_channel";
	private static final String TAB_FAV_TAG = "tab_tag_favorite";
	
	private static final int KILL_ACTIVITY_CODE = 1;
	
	private DbFeedAdapter mDbFeedAdapter;
	
	private boolean mIsOnline = true;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mDbFeedAdapter = new DbFeedAdapter(this);
        mDbFeedAdapter.open();
        setContentView(R.layout.main_feed);
        
      //Home Button on Top
		ImageButton homebtn = (ImageButton)findViewById(R.id.homebtn);
		selector(homebtn, R.drawable.icon_home_brown, R.drawable.icon_home);
		homebtn.setOnClickListener(new OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(FeedTabActivity.this, WelcomeScreen.class);
				startActivity(in);
				FeedTabActivity.this.finish();
			}
		});
        
        long feedId = SharedPreferencesHelper.getPrefTabFeedId(this, mDbFeedAdapter.getFirstFeed().getId());
        
        Bundle extras = getIntent().getExtras();
		if (extras != null) {
			feedId = extras.getLong(DbSchema.FeedSchema._ID);
			SharedPreferencesHelper.setPrefTabFeedId(this, feedId);
		}
        
        Feed currentTabFeed = mDbFeedAdapter.getFeed(feedId);
        setTabs(TAB_CHANNEL_TAG, currentTabFeed.getTitle());
        
        getTabHost().setOnTabChangedListener(new OnTabChangeListener(){
	        	public void onTabChanged(String tabId) {
	        	    if(tabId.equals(TAB_FAV_TAG)) {
	        	    	
	        	    	List<Item> items = fillListData(R.id.favoritelist);
	        	        if (items.isEmpty())
	            			Toast.makeText(FeedTabActivity.this, R.string.no_fav_msg, Toast.LENGTH_LONG).show();
	        		} else if(tabId.equals(TAB_CHANNEL_TAG)) {
	        	    	Feed currentTabFeed = mDbFeedAdapter.getFeed(SharedPreferencesHelper.getPrefTabFeedId(FeedTabActivity.this, mDbFeedAdapter.getFirstFeed().getId()));
	        	    
	        	    		fillListData(R.id.feedlist);
	        	    }
	        	    setTabsBackgroundColor();
			}
		});

		ListView feedListView = (ListView) findViewById(R.id.feedlist);
        ListView favoriteListView = (ListView)findViewById(R.id.favoritelist);

        registerForContextMenu(feedListView);
        registerForContextMenu(favoriteListView);
        
        feedListView.setOnItemClickListener(this);
        favoriteListView.setOnItemClickListener(this);
     
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
    
	@Override
	protected void onStart() {
		super.onStart();

	}

	@Override
	protected void onResume() {
		super.onResume();

		if (getTabHost().getCurrentTabTag().equals(TAB_CHANNEL_TAG)) {
			Feed currentTabFeed = mDbFeedAdapter
					.getFeed(SharedPreferencesHelper.getPrefTabFeedId(
							FeedTabActivity.this, mDbFeedAdapter.getFirstFeed()
									.getId()));
			{
				fillListData(R.id.feedlist);

				if (SharedPreferencesHelper.getPrefStartupDialogOnUpdate(this,
						false)) {
					showDialog(SharedPreferencesHelper.DIALOG_STARTUP);
					SharedPreferencesHelper.setPrefStartupDialogOnUpdate(
							FeedTabActivity.this, false);
				}
			}
		} else
			fillData();
	}

	@Override
	protected void onPause() {
		super.onPause();


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

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	private void setTabs(String activeTab, String title) {
		getTabHost().addTab(
				getTabHost().newTabSpec(TAB_CHANNEL_TAG).setIndicator(title)
						.setContent(R.id.feedlist));
		getTabHost().addTab(getTabHost().newTabSpec(TAB_FAV_TAG).setIndicator(getResources().getText(R.string.favorites),getResources().getDrawable(R.drawable.fav)).setContent(R.id.favoritelist));
    	getTabHost().setCurrentTabByTag(activeTab);
	
		setTabsBackgroundColor();
	}

	private void setTabsBackgroundColor() {
		for (int i = 0; i < getTabHost().getTabWidget().getChildCount(); i++) {
			getTabHost().getTabWidget().getChildAt(i)
					.setBackgroundColor(Color.DKGRAY); // unselected
		}
		getTabHost().getTabWidget().getChildAt(getTabHost().getCurrentTab())
				.setBackgroundColor(Color.TRANSPARENT); // selected
	}

	private void refreshFeed(Feed feed, boolean alwaysDisplayOfflineDialog) {
		if (SharedPreferencesHelper.isOnline(this)) {
			mIsOnline = true;
			new UpdateFeedTask().execute(feed);
		} else {
			if (mIsOnline || alwaysDisplayOfflineDialog) 
				showDialog(SharedPreferencesHelper.DIALOG_NO_CONNECTION);
			mIsOnline = false;
			fillListData(R.id.feedlist);
		}
	}

	private List<Item> fillData() {

		return fillListData(R.id.feedlist);

	}

	private List<Item> fillListData(int listResource) {
		ListView feedListView = (ListView) findViewById(listResource);

		List<Item> items = null;
		if (listResource == R.id.favoritelist) {
			//TrackerAnalyticsHelper.trackPageView(this,"/favoriteListView");
			//items = mDbFeedAdapter.getFavoriteItems(SharedPreferencesHelper.getPrefMaxItems(this));
			items = mDbFeedAdapter.getFavoriteItems(0);
		} else {
			//TrackerAnalyticsHelper.trackPageView(this,"/itemListView");
			Feed currentFeed = mDbFeedAdapter.getFeed(SharedPreferencesHelper.getPrefTabFeedId(this, mDbFeedAdapter.getFirstFeed().getId()));
			if (currentFeed != null && currentFeed.getRefresh() != null) {
				CharSequence formattedUpdate = DateFormat.format(getResources().getText(R.string.update_format_pattern), currentFeed.getRefresh());
        		//getWindow().setTitle(getString(R.string.app_name) + " - " + getString(R.string.last_update) + " " + formattedUpdate);
				getWindow().setTitle(getString(R.string.app_name) + " - " + formattedUpdate);
			}
        	items = mDbFeedAdapter.getItems(SharedPreferencesHelper.getPrefTabFeedId(this, mDbFeedAdapter.getFirstFeed().getId()), 1, SharedPreferencesHelper.getPrefMaxItems(this));
		}

		FeedArrayAdapter arrayAdapter = new FeedArrayAdapter(this, R.id.title,
				items);
		feedListView.setAdapter(arrayAdapter);

		return items;
	}

	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		Item item = mDbFeedAdapter.getItem(id);
		ContentValues values = new ContentValues();
		values.put(DbSchema.ItemSchema.COLUMN_READ, DbSchema.ON);
		mDbFeedAdapter.updateItem(id, values, null);

		Intent intent = null;
		if (SharedPreferencesHelper.getItemView(this) == 0) {
		
				intent = new Intent(FeedTabActivity.this, FeedItem.class);

			
		
		} else {
			intent = new Intent(this, FeedWebActivity.class);
		}

		intent.putExtra(DbSchema.ItemSchema._ID, id);
		startActivityForResult(intent, KILL_ACTIVITY_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);

		switch (requestCode) {
		case KILL_ACTIVITY_CODE:
			if (resultCode == RESULT_OK)
				finish();
			break;
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		if (SharedPreferencesHelper.isDynamicMode(this)) {
			inflater.inflate(R.menu.opt_tab_menu_public_mode, menu);
			MenuItem channelsMenuItem = (MenuItem) menu
					.findItem(R.id.menu_opt_channels);
			channelsMenuItem.setIntent(new Intent(this,
					FeedChannelsActivity.class));
		} else {
			inflater.inflate(R.menu.opt_tab_menu_private_mode, menu);

			if (mDbFeedAdapter.getFeeds().size() > 1) {
				MenuItem channelsMenuItem = (MenuItem) menu
						.findItem(R.id.menu_opt_channels);
				SubMenu subMenu = channelsMenuItem.getSubMenu();

				List<Feed> feeds = mDbFeedAdapter.getFeeds();
				Iterator<Feed> feedIterator = feeds.iterator();
				Feed feed = null;
				MenuItem channelSubMenuItem = null;
				Intent intent = null;
				int order = 0;
				while (feedIterator.hasNext()) {
					feed = feedIterator.next();
					channelSubMenuItem = subMenu.add(
							SharedPreferencesHelper.CHANNEL_SUBMENU_GROUP,
							Menu.NONE, order, feed.getTitle());

					if (feed.getId() == SharedPreferencesHelper
							.getPrefTabFeedId(this, mDbFeedAdapter
									.getFirstFeed().getId()))
						channelSubMenuItem.setChecked(true);

					intent = new Intent(this, FeedTabActivity.class);
					intent.putExtra(DbSchema.FeedSchema._ID, feed.getId());
					channelSubMenuItem.setIntent(intent);

					order++;
				}

				subMenu.setGroupCheckable(
						SharedPreferencesHelper.CHANNEL_SUBMENU_GROUP, true,
						true);
			} else {
				menu.removeItem(R.id.menu_opt_channels);
			}
		}

		// Preferences menu item
		MenuItem preferencesMenuItem = (MenuItem) menu
				.findItem(R.id.menu_opt_preferences);
		preferencesMenuItem.setIntent(new Intent(this, FeedPrefActivity.class));

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_opt_refresh:
			if (getTabHost().getCurrentTabTag().equals(TAB_FAV_TAG)) {
	        	//TrackerAnalyticsHelper.trackEvent(this,LOG_TAG,"OptionMenu_RefreshFavoriteList","Refresh",1);
        		// Refreshing favorites will never find new favorite items, because they are local (not updated from Internet)
        		fillListData(R.id.favoritelist);
        		Toast.makeText(this, R.string.no_new_fav_item_msg, Toast.LENGTH_LONG).show();
        	} else if (getTabHost().getCurrentTabTag().equals(TAB_CHANNEL_TAG)) {
        		Feed currentTabFeed = mDbFeedAdapter.getFeed(SharedPreferencesHelper.getPrefTabFeedId(this, mDbFeedAdapter.getFirstFeed().getId()));
		    	if (currentTabFeed != null) {
		        	//TrackerAnalyticsHelper.trackEvent(this,LOG_TAG,"OptionMenu_RefreshItemList",currentTabFeed.getURL().toString(),1);
			    	refreshFeed(currentTabFeed,true);
		    	}
        	}
         
			return true;
			
	
		default:
			if (item.getGroupId() == SharedPreferencesHelper.CHANNEL_SUBMENU_GROUP) {
				startActivity(item.getIntent());
				finish();
				return true;
			}
		}
		return super.onOptionsItemSelected(item);
	}

	 
    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);
    	
		if (v.getId() == R.id.feedlist || v.getId() == R.id.favoritelist) {
			menu.setHeaderTitle (R.string.ctx_menu_title);
			MenuInflater inflater = getMenuInflater();
			AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
			Item item = mDbFeedAdapter.getItem(acmi.id);
			if (item != null) {
				if (item.isFavorite())
		    		inflater.inflate(R.menu.ctx_menu_notfav, menu);
		    	else
		    		inflater.inflate(R.menu.ctx_menu_fav, menu);
			}
		}
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
    	final AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) menuItem.getMenuInfo();
    	Item item = mDbFeedAdapter.getItem(acmi.id);
    	ContentValues values = null;
    	switch (menuItem.getItemId()) {
    		case R.id.add_fav:
	        	//TrackerAnalyticsHelper.trackEvent(this,LOG_TAG,"ContextMenu_AddFavorite",item.getLink().toString(),1);
    			//item.favorite();
    			values = new ContentValues();
    	    	values.put(DbSchema.ItemSchema.COLUMN_FAVORITE, DbSchema.ON);
    	    	mDbFeedAdapter.updateItem(acmi.id, values, null);
    			fillData();
    			Toast.makeText(this, R.string.add_fav_msg, Toast.LENGTH_SHORT).show();
    			return true;
    		case R.id.remove_fav:
	        	//TrackerAnalyticsHelper.trackEvent(this,LOG_TAG,"ContextMenu_RemoveFavorite",item.getLink().toString(),1);
    			//item.unfavorite();
    			Date now = new Date();
    			long diffTime = now.getTime() - item.getPubdate().getTime();
    			long maxTime = SharedPreferencesHelper.getPrefMaxHours(this) * 60 * 60 * 1000; // Max hours expressed in milliseconds
    			// test if item has expired
    			if (maxTime > 0 && diffTime > maxTime) {
	    			AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    			builder.setMessage(R.string.remove_fav_confirmation)
	    			       .setCancelable(false)
	    			       .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
	    			           public void onClick(DialogInterface dialog, int id) {
									ContentValues values = new ContentValues();
									values.put(DbSchema.ItemSchema.COLUMN_FAVORITE, DbSchema.OFF);
									mDbFeedAdapter.updateItem(acmi.id, values, null);
									fillData();
									Toast.makeText(FeedTabActivity.this, R.string.remove_fav_msg, Toast.LENGTH_SHORT).show();
	    			           }
	    			       })
	    			       .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
	    			           public void onClick(DialogInterface dialog, int id) {
	    			                dialog.cancel();
	    			           }
	    			       });
	    			builder.create().show();
    			} else {
    				values = new ContentValues();
        	    	values.put(DbSchema.ItemSchema.COLUMN_FAVORITE, DbSchema.OFF);
        	    	mDbFeedAdapter.updateItem(acmi.id, values, null);
        			fillData();
        			Toast.makeText(this, R.string.remove_fav_msg, Toast.LENGTH_SHORT).show();
    			}
    			return true;
    		case R.id.share:
    			if (item != null) {
    	        	//TrackerAnalyticsHelper.trackEvent(this,LOG_TAG,"ContextMenu_Share",item.getLink().toString(),1);
	    			Intent shareIntent = new Intent(Intent.ACTION_SEND);
	                shareIntent.putExtra(Intent.EXTRA_SUBJECT, String.format(getString(R.string.share_subject), getString(R.string.app_name)));
	                shareIntent.putExtra(Intent.EXTRA_TEXT, item.getTitle() + " " + item.getLink().toString());
	                shareIntent.setType("text/plain");
	                startActivity(Intent.createChooser(shareIntent, getString(R.string.share)));
    			}
    			return true;
    		default:
    			return super.onContextItemSelected(menuItem);
    	}
    }
    
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		CharSequence title = null;
		LayoutInflater inflater = null;
		View dialogLayout = null;
		AlertDialog.Builder builder = null;
		switch (id) {
		case SharedPreferencesHelper.DIALOG_UPDATE_PROGRESS:
			dialog = new ProgressDialog(this);
			((ProgressDialog) dialog).setTitle(getResources().getText(
					R.string.updating));
			((ProgressDialog) dialog).setIcon(R.drawable.ic_dialog);
			((ProgressDialog) dialog).setMessage(getResources().getText(
					R.string.downloading));
			((ProgressDialog) dialog).setIndeterminate(true);
			dialog.setCancelable(false);
			break;
	
			
		case SharedPreferencesHelper.DIALOG_NO_CONNECTION:
			title = getString(R.string.error);
			inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			dialogLayout = inflater
					.inflate(R.layout.dialog_no_connection, null);
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
		case SharedPreferencesHelper.DIALOG_STARTUP:
			title = getString(R.string.app_name) + " - "
					+ getString(R.string.version) + " "
					+ SharedPreferencesHelper.getVersionName(this);
			inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			if (SharedPreferencesHelper.getPrefStartupDialogOnInstall(this,
					false))
				dialogLayout = inflater.inflate(
						R.layout.dialog_startup_install, null);
			else if (SharedPreferencesHelper.getPrefStartupDialogOnUpdate(this,
					false))
				dialogLayout = inflater.inflate(R.layout.dialog_startup_update,
						null);

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

	private class FeedArrayAdapter extends ArrayAdapter<Item> {
		private LayoutInflater mInflater;

		public FeedArrayAdapter(Context context, int textViewResourceId,
				List<Item> objects) {
			super(context, textViewResourceId, objects);
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public long getItemId(int position) {
			return getItem(position).getId();
		}

		@Override
		  public View getView(int position, View convertView, ViewGroup parent) {
        	int[] item_rows = {R.layout.channel_item_row_notselected_notfavorite, R.layout.channel_item_row_selected_notfavorite,R.layout.channel_item_row_notselected_favorite,R.layout.channel_item_row_selected_favorite,R.layout.fav_item_row_notselected_favorite,R.layout.fav_item_row_selected_favorite,};
        	int item_row = item_rows[0]; // Default initialization
        	
        	Item item = getItem(position);
            
        	View view = convertView;
        	// Always inflate view, in order to display correctly the 'read' and 'favorite' states of the items => to apply the right layout+style.
            //if (view == null) {
	            //LayoutInflater li = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
	            if (item.isRead())
	            	if (item.isFavorite())
	            		if (getTabHost().getCurrentTabTag().equals(TAB_FAV_TAG))
	            			item_row = item_rows[5];
	            		else
	            			item_row = item_rows[3];
	            	else
	            		item_row = item_rows[1];
	            else
	            	if (item.isFavorite())
	            		if (getTabHost().getCurrentTabTag().equals(TAB_FAV_TAG))
	            			item_row = item_rows[4];
	            		else
	            			item_row = item_rows[2];
	            	else
	            		item_row = item_rows[0];
	            view = mInflater.inflate(item_row, null);
            //}
            
            TextView titleView = (TextView) view.findViewById(R.id.title);
            TextView channelView = (TextView) view.findViewById(R.id.channel); // only displayed in favorite view
            TextView pubdateView = (TextView) view.findViewById(R.id.pubdate);
            if (titleView != null)
            	titleView.setText(item.getTitle());
            if (channelView != null) {
            	Feed feed = mDbFeedAdapter.getFeed(mDbFeedAdapter.getItemFeedId(item.getId()));
            	if (feed != null)
            		channelView.setText(feed.getTitle());
            } if (pubdateView != null) {
            	//DateFormat df = new SimpleDateFormat(getResources().getText(R.string.pubdate_format_pattern);
            	//pubdateView.setText(df.format(item.getPubdate()));
            	CharSequence formattedPubdate = DateFormat.format(getResources().getText(R.string.pubdate_format_pattern), item.getPubdate());
            	pubdateView.setText(formattedPubdate);
            }
            
            return view;
        }
    
		
	}

	private class UpdateFeedTask extends AsyncTask<Feed, Void, Boolean> {
		private long feedId = -1;
		private long lastItemIdBeforeUpdate = -1;

		public UpdateFeedTask() {
			super();
		}

		protected Boolean doInBackground(Feed... params) {
			feedId = params[0].getId();
			Item lastItem = mDbFeedAdapter.getLastItem(feedId);
			if (lastItem != null)
				lastItemIdBeforeUpdate = lastItem.getId();

			FeedHandler feedHandler = new FeedHandler(FeedTabActivity.this);

			try {
				Feed handledFeed = feedHandler.handleFeed(params[0].getURL());

				handledFeed.setId(feedId);
				mDbFeedAdapter.updateFeed(handledFeed);
				mDbFeedAdapter.cleanDbItems(feedId);
				SharedPreferencesHelper.setPrefTabFeedId(FeedTabActivity.this,
						feedId);

			} catch (IOException ioe) {
				Log.e(LOG_TAG, "", ioe);
				errorId = errorId + 1;
				return new Boolean(false);
			} catch (SAXException se) {
				Log.e(LOG_TAG, "", se);
				errorId = errorId + 1;
				return new Boolean(false);
			} catch (ParserConfigurationException pce) {
				Log.e(LOG_TAG, "", pce);
				errorId = errorId + 1;
				return new Boolean(false);
			}

			return new Boolean(true);
		}

		protected void onPreExecute() {
			showDialog(SharedPreferencesHelper.DIALOG_UPDATE_PROGRESS);
		}

		protected void onPostExecute(Boolean result) {
			fillListData(R.id.feedlist);
			dismissDialog(SharedPreferencesHelper.DIALOG_UPDATE_PROGRESS);
			if (SharedPreferencesHelper.getPrefStartupDialogOnInstall(
					FeedTabActivity.this, false)
					|| SharedPreferencesHelper.getPrefStartupDialogOnUpdate(
							FeedTabActivity.this, false)) {
				showDialog(SharedPreferencesHelper.DIALOG_STARTUP);
				SharedPreferencesHelper.setPrefStartupDialogOnInstall(
						FeedTabActivity.this, false);
				SharedPreferencesHelper.setPrefStartupDialogOnUpdate(
						FeedTabActivity.this, false);
			}

			long lastItemIdAfterUpdate = -1;
			Item lastItem = mDbFeedAdapter.getLastItem(feedId);
			if (lastItem != null)
				lastItemIdAfterUpdate = lastItem.getId();
			if (lastItemIdAfterUpdate > lastItemIdBeforeUpdate)
				Toast.makeText(FeedTabActivity.this, R.string.new_item_msg,
						Toast.LENGTH_LONG).show();
			else
				Toast.makeText(FeedTabActivity.this, R.string.no_new_item_msg,
						Toast.LENGTH_LONG).show();
		}
	}

	public void onBackPressed() {
		Intent intent = new Intent(this, WelcomeScreen.class);
		startActivity(intent);
		this.finish();
	}

}