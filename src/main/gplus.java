package main;

import eHandy.gtbit.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class gplus extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		WebView webView = (WebView) findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);

		webView.loadUrl("https://plus.google.com/u/0/116761223418826930313/posts");
		webView.setWebViewClient(new WebViewClient() {
		@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				if (url.startsWith("http")) {

					view.loadUrl(url);

					return true;

				} else {

					return super.shouldOverrideUrlLoading(view, url);

				}

			}

		});
	
	}
	public void onBackPressed() {
		Intent intent = new Intent(this, SocialMedia.class);
		startActivity(intent);
		this.finish();
	}
	
}

