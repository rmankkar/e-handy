package rss.feed;

import java.util.Iterator;

import java.util.List;

import main.WelcomeScreen;

import eHandy.gtbit.R;

import android.content.Intent;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

import rss.feed.common.Feed;
import rss.feed.storage.DbFeedAdapter;

public class FeedPrefActivity extends PreferenceActivity {
	private static final String LOG_TAG = "FeedPrefActivity";

	public static final String PREF_START_CHANNEL_KEY = "startChannel";
	public static final String PREF_ITEM_VIEW_KEY = "itemView";
	public static final String PREF_MAX_ITEMS_KEY = "maxItems";
	public static final String PREF_MAX_HOURS_KEY = "maxHours";
	public static final String PREF_UPDATE_PERIOD_KEY = "updatePeriod";
	public static final String PREF_USAGE_DATA_KEY = "usageData";

	public static final String DEFAULT_START_CHANNEL = "1";
	public static final String DEFAULT_ITEM_VIEW = "0";
	public static final String DEFAULT_MAX_ITEMS_PER_FEED = "20";
	public static final String DEFAULT_MAX_HOURS_PER_FEED = "-1";
	public static final String DEFAULT_UPDATE_PERIOD = "60";

	public static final boolean DEFAULT_USAGE_DATA = false;

	public static final String[] PREF_KEYS = { PREF_START_CHANNEL_KEY,
			PREF_ITEM_VIEW_KEY, PREF_MAX_ITEMS_KEY, PREF_MAX_HOURS_KEY,
			PREF_UPDATE_PERIOD_KEY, PREF_USAGE_DATA_KEY };

	private DbFeedAdapter mDbFeedAdapter;

	private class FeedPreferenceChangeListener implements
			Preference.OnPreferenceChangeListener {

		public boolean onPreferenceChange(Preference preference, Object newValue) {
			// TODO Auto-generated method stub
			String label = "";
			if (newValue instanceof String) {
				label = (String) newValue;
				label = label.replace(" ", "_");
			} else if (newValue instanceof Boolean)
				label = newValue.toString();
			if (preference.isEnabled()
					&& preference.getKey().equals(
							FeedPrefActivity.PREF_USAGE_DATA_KEY)) {
				boolean sendUsageData = ((Boolean) newValue).booleanValue();
				if (sendUsageData) {
				}
			}

			return true;

		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mDbFeedAdapter = new DbFeedAdapter(this);
		mDbFeedAdapter.open();

		/*CharSequence title = getString(R.string.app_name) + " - "
				+ getString(R.string.pref_name);
		setTitle(title);
*/
		addPreferencesFromResource(R.xml.preferences);

		Preference preference = null;
		for (int i = 0; i < PREF_KEYS.length; i++) {
			preference = findPreference(PREF_KEYS[i]);
			if (preference != null)
				preference
						.setOnPreferenceChangeListener(new FeedPreferenceChangeListener());
		}

		Preference dataUsagePreference = findPreference(PREF_USAGE_DATA_KEY);
		ListPreference listPref = (ListPreference) findPreference(PREF_START_CHANNEL_KEY);

		if (listPref != null) {
			List<Feed> feeds = mDbFeedAdapter.getFeeds();
			Iterator<Feed> feedIterator = feeds.iterator();
			Feed feed = null;
			CharSequence[] entries = new CharSequence[feeds.size()];
			CharSequence[] entryValues = new CharSequence[feeds.size()];
			int index = 0;
			while (feedIterator.hasNext()) {
				feed = feedIterator.next();
				entries[index] = feed.getTitle();
				entryValues[index] = Long.toString(feed.getId());
				index++;
			}

			listPref.setEntries(entries);
			listPref.setEntryValues(entryValues);
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		
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
	public void onBackPressed() {
		Intent intent = new Intent(this, FeedTabActivity.class);
		startActivity(intent);
		this.finish();
	}


}
