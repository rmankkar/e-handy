package learn.student;

import java.util.ArrayList;

import eHandy.gtbit.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ebooks extends Activity {
	private ImageAdapter_vid imgAdapter;
    private ArrayList<String> desc;
    private ArrayList<Integer> image;
    private GridView gridView_2;
    public static final int MESSAGE_DOWNLOAD_STARTED = 1000;
	public static final int MESSAGE_DOWNLOAD_COMPLETE = 1001;
	public static final int MESSAGE_UPDATE_PROGRESS_BAR = 1002;
	public static final int MESSAGE_DOWNLOAD_CANCELED = 1003;
	public static final int MESSAGE_CONNECTING_STARTED = 1004;
	public static final int MESSAGE_ENCOUNTERED_ERROR = 1005;
	public String[] strarray = {
			"https://dl.dropbox.com/s/08p76i9e8430ozi/C_The_Complete_Herbert_Schildt.pdf",
			"https://dl.dropbox.com/s/0blxaa03g374d0r/C%23_Complete_Reference.pdf",
			"https://dl.dropbox.com/s/4lfd9th5wcghk4h/C%2B%2B_Complete_Reference_Schildt.pdf",
			"https://dl.dropbox.com/s/fipbo9e1rco0iwm/Mastering_C%2B%2B_Venugopal.pdf",
			"https://dl.dropbox.com/s/nuvq9c8zjkcts2o/Core_Java_Fundamentals.pdf",
			"https://dl.dropbox.com/s/umznlmyquh2svzc/Java2_The_Complete_Reference_Herbert_Schildt.pdf",
			"https://dl.dropbox.com/s/yuhv0mnlrx85uoa/Computer_Networks_frouzan.pdf",
			"https://dl.dropbox.com/s/sb95dlfghg4l2ia/Computer_Networks_Andrew_S_Tanenbaum.pdf",
			"https://dl.dropbox.com/s/vvw2j1dsrie1255/Artificial_Intelligence_Stuart_Russell_and_Peter_Norvig.pdf",
			"https://dl.dropbox.com/s/5bjeeqzb8vacp1m/Cryptography_and_Network_Security_Principles_and_Practices_William_Stallings.pdf",
			"https://dl.dropbox.com/s/7gfzkgeop37bk31/Elmasri_Navathe_Fundamentals_Of_Database_Systems.pdf",
			"https://dl.dropbox.com/s/p0sj3hj1yiw66gt/Introduction_to_Algorithms_Cormen.pdf",
			"https://dl.dropbox.com/s/5xdw550u4clglfx/Roger_S_Pressman_Software_Engineering.pdf",
			"https://dl.dropbox.com/s/k12qf136l42zkom/Operating_System_Concepts_Silberschatz_Galvin.pdf",
			"https://dl.dropbox.com/s/wtfih57yol9aiix/Mobile_Communications.pdf",
			"https://dl.dropbox.com/s/t6b9ctg28zbi9a0/Aho_Compilers.pdf",
			"https://dl.dropbox.com/s/cqdwzloghqkgyy3/Digital_Signal_Processing.pdf",
			"https://dl.dropbox.com/s/35gafnyew7za354/Digital_Design_Morris_Mano.pdf"
		};

	public String[] str_array_books = {
			"http://docs.google.com/gview?embedded=true&url=https://dl.dropbox.com/s/qozad5bkztbz0pi/C_The_Complete_Herbert_Schildt.pdf",
			"http://docs.google.com/gview?embedded=true&url=https://dl.dropbox.com/s/ppjtkb9slfcthl0/C_h_complete_reference.pdf",
			"http://docs.google.com/gview?embedded=true&url=https://dl.dropbox.com/s/ndizz9fy3vf1xn0/C%2B%2B_Complete_Reference_Schildt.pdf",
			"http://docs.google.com/gview?embedded=true&url=https://dl.dropbox.com/s/a70gxflgc732hh2/Mastering_C%2B%2B_Venugopal.pdf",
			"http://docs.google.com/gview?embedded=true&url=https://dl.dropbox.com/s/tazztj8wi9xs9up/Core_Java_Fundamentals.pdf",
			"http://docs.google.com/gview?embedded=true&url=https://dl.dropbox.com/s/asmy1ztqbgd1f62/Java2_The_Complete_Reference_Herbert_Schildt.pdf",
			"http://docs.google.com/gview?embedded=true&url=https://dl.dropbox.com/s/gqcu219zeinwufr/Computer_Networks_frouzan.pdf",
			"http://docs.google.com/gview?embedded=true&url=https://dl.dropbox.com/s/es08u4urqkwj8f4/Computer_Networks_Andrew_S_Tanenbaum.pdf",
			"http://docs.google.com/gview?embedded=true&url=https://dl.dropbox.com/s/lzrdsq4kcokggo9/Artificial_Intelligence_Stuart_Russell_and_Peter_Norvig.pdf",
			"http://docs.google.com/gview?embedded=true&url=https://dl.dropbox.com/s/mtmg1patie6ygin/Cryptography_and_Network_Security_Principles_and_Practices_William_Stallings.pdf",
			"http://docs.google.com/gview?embedded=true&url=https://dl.dropbox.com/s/5s74myc94j51dap/Elmasri_Navathe_Fundamentals_Of_Database_Systems.pdf",
			"http://docs.google.com/gview?embedded=true&url=https://dl.dropbox.com/s/220gihhhugu3gyp/Introduction_to_Algorithms_Cormen.pdf",
			"http://docs.google.com/gview?embedded=true&url=https://dl.dropbox.com/s/ifdewmwqp89owgu/Roger_S_Pressman_Software_Engineering.pdf",
			"http://docs.google.com/gview?embedded=true&url=https://dl.dropbox.com/s/5kb5xu1yzhlxb4i/Operating_System_Concepts_Silberschatz_Galvin.pdf",
			"http://docs.google.com/gview?embedded=true&url=https://dl.dropbox.com/s/qhtzxsva3eac4wh/Mobile_Communications.pdf",
			"http://docs.google.com/gview?embedded=true&url=https://dl.dropbox.com/s/zhaebhfy8qqg3r3/Aho_Compilers.pdf",
			"http://docs.google.com/gview?embedded=true&url=https://dl.dropbox.com/s/539ba9z7a76i1c4/Digital_Signal_Processing.pdf",
			"http://docs.google.com/gview?embedded=true&url=https://dl.dropbox.com/s/bgm4f9l0ov8li6v/Digital_Design_Morris_Mano.pdf"
	};

	
	private ebooks thisActivity;
	private Thread downloaderThread;
	private ProgressDialog progressDialog;

   

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisActivity = this;
        downloaderThread = null;
        progressDialog = null;
        setContentView(R.layout.ebook_grid);
		getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);
		prepareList2();
        
        // prepared arraylist and passed it to the Adapter class
        imgAdapter = new ImageAdapter_vid(this,desc, image);
 
        // Set custom adapter to gridview
        gridView_2 = (GridView) findViewById(R.id.gridView_ebook);
        gridView_2.setAdapter(imgAdapter);
        // Implement On Item click listener
        gridView_2.setOnItemClickListener(new OnItemClickListener()
        {
           
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                    long arg3) {
        	
        		AlertDialog.Builder builder=new AlertDialog.Builder(ebooks.this);
     		   	builder.setIcon(R.drawable.ebook_icon);
     		   	builder.setTitle("eBooks");
                builder.setMessage("View or Download?");

    			  
                 
        		
            	  if(position == 0){
                	
            		  builder.setPositiveButton("Preview",new DialogInterface.OnClickListener() {
               		   public void onClick(DialogInterface dialog, int which) {
               			   
                           setContentView(R.layout.webview);
                           final WebView webView = (WebView) findViewById(R.id.webview);
                           webView.getSettings().setJavaScriptEnabled(true);
                           webView.loadUrl(str_array_books[0]);
               			   Toast.makeText(ebooks.this,  R.string.preview, Toast.LENGTH_LONG).show();
               			   
               			   webView.setWebViewClient(new WebViewClient() {
                   			@Override
                   			public boolean shouldOverrideUrlLoading(WebView view, String url) {
                   				if (url.startsWith("http")) {
                   					view.loadUrl(url);
                   					return true;
                   				} else 
                   					{
                   					return super.shouldOverrideUrlLoading(view, url);
                   					}
                   				}
                   			});
               		   }
               	  });
               	
	                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
	                	   public void onClick(DialogInterface dialog, int which) {
		                	
	                		downloaderThread = new DownloaderThread_ebook(thisActivity, strarray[0]);
	           				downloaderThread.start();
	                   	}
	                   	  });
		           AlertDialog alertdialog=builder.create();
	               alertdialog.show();
            	
                }
                if(position == 1){
                	 builder.setPositiveButton("Preview",new DialogInterface.OnClickListener() {
                 		   public void onClick(DialogInterface dialog, int which) {
                 			   
                             setContentView(R.layout.webview);
                             final WebView webView = (WebView) findViewById(R.id.webview);
                             webView.getSettings().setJavaScriptEnabled(true);
                             webView.loadUrl(str_array_books[1]);
                 			   Toast.makeText(ebooks.this, R.string.preview, Toast.LENGTH_LONG).show();
                 			   
                 			   webView.setWebViewClient(new WebViewClient() {
                     			@Override
                     			public boolean shouldOverrideUrlLoading(WebView view, String url) {
                     				if (url.startsWith("http")) {
                     					view.loadUrl(url);
                     					return true;
                     				} else 
                     					{
                     					return super.shouldOverrideUrlLoading(view, url);
                     					}
                     				}
                     			});
                 		   }
                 	  });
                 	
  	                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
  	                	   public void onClick(DialogInterface dialog, int which) {
  		                	
  	                		downloaderThread = new DownloaderThread_ebook(thisActivity, strarray[1]);
  	           				downloaderThread.start();
  	                   	}
  	                   	  });
  		           AlertDialog alertdialog=builder.create();
  	               alertdialog.show();
              	
                  }
                if(position == 2){
                	 builder.setPositiveButton("Preview",new DialogInterface.OnClickListener() {
                 		   public void onClick(DialogInterface dialog, int which) {
                 			   
                             setContentView(R.layout.webview);
                             final WebView webView = (WebView) findViewById(R.id.webview);
                             webView.getSettings().setJavaScriptEnabled(true);
                             webView.loadUrl(str_array_books[2]);
                 			   Toast.makeText(ebooks.this, R.string.preview, Toast.LENGTH_LONG).show();
                 			   
                 			   webView.setWebViewClient(new WebViewClient() {
                     			@Override
                     			public boolean shouldOverrideUrlLoading(WebView view, String url) {
                     				if (url.startsWith("http")) {
                     					view.loadUrl(url);
                     					return true;
                     				} else 
                     					{
                     					return super.shouldOverrideUrlLoading(view, url);
                     					}
                     				}
                     			});
                 		   }
                 	  });
                 	
  	                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
  	                	   public void onClick(DialogInterface dialog, int which) {
  		                	
  	                		downloaderThread = new DownloaderThread_ebook(thisActivity, strarray[2]);
  	           				downloaderThread.start();
  	                   	}
  	                   	  });
  		           AlertDialog alertdialog=builder.create();
  	               alertdialog.show();
              	
                  }
                if(position == 3){
                	 builder.setPositiveButton("Preview",new DialogInterface.OnClickListener() {
                 		   public void onClick(DialogInterface dialog, int which) {
                 			   
                             setContentView(R.layout.webview);
                             final WebView webView = (WebView) findViewById(R.id.webview);
                             webView.getSettings().setJavaScriptEnabled(true);
                             webView.loadUrl(str_array_books[3]);
                 			   Toast.makeText(ebooks.this, R.string.preview, Toast.LENGTH_LONG).show();
                 			   
                 			   webView.setWebViewClient(new WebViewClient() {
                     			@Override
                     			public boolean shouldOverrideUrlLoading(WebView view, String url) {
                     				if (url.startsWith("http")) {
                     					view.loadUrl(url);
                     					return true;
                     				} else 
                     					{
                     					return super.shouldOverrideUrlLoading(view, url);
                     					}
                     				}
                     			});
                 		   }
                 	  });
                 	
  	                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
  	                	   public void onClick(DialogInterface dialog, int which) {
  		                	
  	                		downloaderThread = new DownloaderThread_ebook(thisActivity, strarray[3]);
  	           				downloaderThread.start();
  	                   	}
  	                   	  });
  		           AlertDialog alertdialog=builder.create();
  	               alertdialog.show();
              	
                  }
                if(position == 4){
                	 builder.setPositiveButton("Preview",new DialogInterface.OnClickListener() {
                 		   public void onClick(DialogInterface dialog, int which) {
                 			   
                             setContentView(R.layout.webview);
                             final WebView webView = (WebView) findViewById(R.id.webview);
                             webView.getSettings().setJavaScriptEnabled(true);
                             webView.loadUrl(str_array_books[4]);
                 			   Toast.makeText(ebooks.this, R.string.preview, Toast.LENGTH_LONG).show();
                 			   
                 			   webView.setWebViewClient(new WebViewClient() {
                     			@Override
                     			public boolean shouldOverrideUrlLoading(WebView view, String url) {
                     				if (url.startsWith("http")) {
                     					view.loadUrl(url);
                     					return true;
                     				} else 
                     					{
                     					return super.shouldOverrideUrlLoading(view, url);
                     					}
                     				}
                     			});
                 		   }
                 	  });
                 	
  	                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
  	                	   public void onClick(DialogInterface dialog, int which) {
  		                	
  	                		downloaderThread = new DownloaderThread_ebook(thisActivity, strarray[4]);
  	           				downloaderThread.start();
  	                   	}
  	                   	  });
  		           AlertDialog alertdialog=builder.create();
  	               alertdialog.show();
              	
                  }
                if(position == 5){
                	 builder.setPositiveButton("Preview",new DialogInterface.OnClickListener() {
                 		   public void onClick(DialogInterface dialog, int which) {
                 			   
                             setContentView(R.layout.webview);
                             final WebView webView = (WebView) findViewById(R.id.webview);
                             webView.getSettings().setJavaScriptEnabled(true);
                             webView.loadUrl(str_array_books[5]);
                 			   Toast.makeText(ebooks.this, R.string.preview, Toast.LENGTH_LONG).show();
                 			   
                 			   webView.setWebViewClient(new WebViewClient() {
                     			@Override
                     			public boolean shouldOverrideUrlLoading(WebView view, String url) {
                     				if (url.startsWith("http")) {
                     					view.loadUrl(url);
                     					return true;
                     				} else 
                     					{
                     					return super.shouldOverrideUrlLoading(view, url);
                     					}
                     				}
                     			});
                 		   }
                 	  });
                 	
  	                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
  	                	   public void onClick(DialogInterface dialog, int which) {
  		                	
  	                		downloaderThread = new DownloaderThread_ebook(thisActivity, strarray[5]);
  	           				downloaderThread.start();
  	                   	}
  	                   	  });
  		           AlertDialog alertdialog=builder.create();
  	               alertdialog.show();
              	
                  }
                if(position == 6){
                	 builder.setPositiveButton("Preview",new DialogInterface.OnClickListener() {
                 		   public void onClick(DialogInterface dialog, int which) {
                 			   
                             setContentView(R.layout.webview);
                             final WebView webView = (WebView) findViewById(R.id.webview);
                             webView.getSettings().setJavaScriptEnabled(true);
                             webView.loadUrl(str_array_books[6]);
                 			   Toast.makeText(ebooks.this, R.string.preview, Toast.LENGTH_LONG).show();
                 			   
                 			   webView.setWebViewClient(new WebViewClient() {
                     			@Override
                     			public boolean shouldOverrideUrlLoading(WebView view, String url) {
                     				if (url.startsWith("http")) {
                     					view.loadUrl(url);
                     					return true;
                     				} else 
                     					{
                     					return super.shouldOverrideUrlLoading(view, url);
                     					}
                     				}
                     			});
                 		   }
                 	  });
                 	
  	                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
  	                	   public void onClick(DialogInterface dialog, int which) {
  		                	
  	                		downloaderThread = new DownloaderThread_ebook(thisActivity, strarray[6]);
  	           				downloaderThread.start();
  	                   	}
  	                   	  });
  		           AlertDialog alertdialog=builder.create();
  	               alertdialog.show();
              	
                  }
                if(position == 7){
                	 builder.setPositiveButton("Preview",new DialogInterface.OnClickListener() {
                 		   public void onClick(DialogInterface dialog, int which) {
                 			   
                             setContentView(R.layout.webview);
                             final WebView webView = (WebView) findViewById(R.id.webview);
                             webView.getSettings().setJavaScriptEnabled(true);
                             webView.loadUrl(str_array_books[7]);
                 			   Toast.makeText(ebooks.this, R.string.preview, Toast.LENGTH_LONG).show();
                 			   
                 			   webView.setWebViewClient(new WebViewClient() {
                     			@Override
                     			public boolean shouldOverrideUrlLoading(WebView view, String url) {
                     				if (url.startsWith("http")) {
                     					view.loadUrl(url);
                     					return true;
                     				} else 
                     					{
                     					return super.shouldOverrideUrlLoading(view, url);
                     					}
                     				}
                     			});
                 		   }
                 	  });
                 	
  	                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
  	                	   public void onClick(DialogInterface dialog, int which) {
  		                	
  	                		downloaderThread = new DownloaderThread_ebook(thisActivity, strarray[7]);
  	           				downloaderThread.start();
  	                   	}
  	                   	  });
  		           AlertDialog alertdialog=builder.create();
  	               alertdialog.show();
              	
                  }
                if(position == 8){
                	 builder.setPositiveButton("Preview",new DialogInterface.OnClickListener() {
                 		   public void onClick(DialogInterface dialog, int which) {
                 			   
                             setContentView(R.layout.webview);
                             final WebView webView = (WebView) findViewById(R.id.webview);
                             webView.getSettings().setJavaScriptEnabled(true);
                             webView.loadUrl(str_array_books[8]);
                 			   Toast.makeText(ebooks.this, R.string.preview, Toast.LENGTH_LONG).show();
                 			   
                 			   webView.setWebViewClient(new WebViewClient() {
                     			@Override
                     			public boolean shouldOverrideUrlLoading(WebView view, String url) {
                     				if (url.startsWith("http")) {
                     					view.loadUrl(url);
                     					return true;
                     				} else 
                     					{
                     					return super.shouldOverrideUrlLoading(view, url);
                     					}
                     				}
                     			});
                 		   }
                 	  });
                 	
  	                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
  	                	   public void onClick(DialogInterface dialog, int which) {
  		                	
  	                		downloaderThread = new DownloaderThread_ebook(thisActivity, strarray[8]);
  	           				downloaderThread.start();
  	                   	}
  	                   	  });
  		           AlertDialog alertdialog=builder.create();
  	               alertdialog.show();
              	
                  }
                if(position == 9){
                	 builder.setPositiveButton("Preview",new DialogInterface.OnClickListener() {
                 		   public void onClick(DialogInterface dialog, int which) {
                 			   
                             setContentView(R.layout.webview);
                             final WebView webView = (WebView) findViewById(R.id.webview);
                             webView.getSettings().setJavaScriptEnabled(true);
                             webView.loadUrl(str_array_books[9]);
                 			   Toast.makeText(ebooks.this, R.string.preview, Toast.LENGTH_LONG).show();
                 			   
                 			   webView.setWebViewClient(new WebViewClient() {
                     			@Override
                     			public boolean shouldOverrideUrlLoading(WebView view, String url) {
                     				if (url.startsWith("http")) {
                     					view.loadUrl(url);
                     					return true;
                     				} else 
                     					{
                     					return super.shouldOverrideUrlLoading(view, url);
                     					}
                     				}
                     			});
                 		   }
                 	  });
                 	
  	                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
  	                	   public void onClick(DialogInterface dialog, int which) {
  		                	
  	                		downloaderThread = new DownloaderThread_ebook(thisActivity, strarray[9]);
  	           				downloaderThread.start();
  	                   	}
  	                   	  });
  		           AlertDialog alertdialog=builder.create();
  	               alertdialog.show();
              	
                  }
                if(position == 10){
                	 builder.setPositiveButton("Preview",new DialogInterface.OnClickListener() {
                 		   public void onClick(DialogInterface dialog, int which) {
                 			   
                             setContentView(R.layout.webview);
                             final WebView webView = (WebView) findViewById(R.id.webview);
                             webView.getSettings().setJavaScriptEnabled(true);
                             webView.loadUrl(str_array_books[10]);
                 			   Toast.makeText(ebooks.this, R.string.preview, Toast.LENGTH_LONG).show();
                 			   
                 			   webView.setWebViewClient(new WebViewClient() {
                     			@Override
                     			public boolean shouldOverrideUrlLoading(WebView view, String url) {
                     				if (url.startsWith("http")) {
                     					view.loadUrl(url);
                     					return true;
                     				} else 
                     					{
                     					return super.shouldOverrideUrlLoading(view, url);
                     					}
                     				}
                     			});
                 		   }
                 	  });
                 	
  	                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
  	                	   public void onClick(DialogInterface dialog, int which) {
  		                	
  	                		downloaderThread = new DownloaderThread_ebook(thisActivity, strarray[10]);
  	           				downloaderThread.start();
  	                   	}
  	                   	  });
  		           AlertDialog alertdialog=builder.create();
  	               alertdialog.show();
              	
                  }
                if(position == 11){
                	 builder.setPositiveButton("Preview",new DialogInterface.OnClickListener() {
                 		   public void onClick(DialogInterface dialog, int which) {
                 			   
                             setContentView(R.layout.webview);
                             final WebView webView = (WebView) findViewById(R.id.webview);
                             webView.getSettings().setJavaScriptEnabled(true);
                             webView.loadUrl(str_array_books[11]);
                 			   Toast.makeText(ebooks.this, R.string.preview, Toast.LENGTH_LONG).show();
                 			   
                 			   webView.setWebViewClient(new WebViewClient() {
                     			@Override
                     			public boolean shouldOverrideUrlLoading(WebView view, String url) {
                     				if (url.startsWith("http")) {
                     					view.loadUrl(url);
                     					return true;
                     				} else 
                     					{
                     					return super.shouldOverrideUrlLoading(view, url);
                     					}
                     				}
                     			});
                 		   }
                 	  });
                 	
  	                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
  	                	   public void onClick(DialogInterface dialog, int which) {
  		                	
  	                		downloaderThread = new DownloaderThread_ebook(thisActivity, strarray[11]);
  	           				downloaderThread.start();
  	                   	}
  	                   	  });
  		           AlertDialog alertdialog=builder.create();
  	               alertdialog.show();
              	
                  }
                if(position == 12){
                	 builder.setPositiveButton("Preview",new DialogInterface.OnClickListener() {
                 		   public void onClick(DialogInterface dialog, int which) {
                 			   
                             setContentView(R.layout.webview);
                             final WebView webView = (WebView) findViewById(R.id.webview);
                             webView.getSettings().setJavaScriptEnabled(true);
                             webView.loadUrl(str_array_books[12]);
                 			   Toast.makeText(ebooks.this, R.string.preview, Toast.LENGTH_LONG).show();
                 			   
                 			   webView.setWebViewClient(new WebViewClient() {
                     			@Override
                     			public boolean shouldOverrideUrlLoading(WebView view, String url) {
                     				if (url.startsWith("http")) {
                     					view.loadUrl(url);
                     					return true;
                     				} else 
                     					{
                     					return super.shouldOverrideUrlLoading(view, url);
                     					}
                     				}
                     			});
                 		   }
                 	  });
                 	
  	                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
  	                	   public void onClick(DialogInterface dialog, int which) {
  		                	
  	                		downloaderThread = new DownloaderThread_ebook(thisActivity, strarray[12]);
  	           				downloaderThread.start();
  	                   	}
  	                   	  });
  		           AlertDialog alertdialog=builder.create();
  	               alertdialog.show();
              	
                  }
                if(position == 13){
                	 builder.setPositiveButton("Preview",new DialogInterface.OnClickListener() {
                 		   public void onClick(DialogInterface dialog, int which) {
                 			   
                             setContentView(R.layout.webview);
                             final WebView webView = (WebView) findViewById(R.id.webview);
                             webView.getSettings().setJavaScriptEnabled(true);
                             webView.loadUrl(str_array_books[13]);
                 			   Toast.makeText(ebooks.this, R.string.preview, Toast.LENGTH_LONG).show();
                 			   
                 			   webView.setWebViewClient(new WebViewClient() {
                     			@Override
                     			public boolean shouldOverrideUrlLoading(WebView view, String url) {
                     				if (url.startsWith("http")) {
                     					view.loadUrl(url);
                     					return true;
                     				} else 
                     					{
                     					return super.shouldOverrideUrlLoading(view, url);
                     					}
                     				}
                     			});
                 		   }
                 	  });
                 	
  	                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
  	                	   public void onClick(DialogInterface dialog, int which) {
  		                	
  	                		downloaderThread = new DownloaderThread_ebook(thisActivity, strarray[13]);
  	           				downloaderThread.start();
  	                   	}
  	                   	  });
  		           AlertDialog alertdialog=builder.create();
  	               alertdialog.show();
              	
                  }
                if(position == 14){
                	 builder.setPositiveButton("Preview",new DialogInterface.OnClickListener() {
                 		   public void onClick(DialogInterface dialog, int which) {
                 			   
                             setContentView(R.layout.webview);
                             final WebView webView = (WebView) findViewById(R.id.webview);
                             webView.getSettings().setJavaScriptEnabled(true);
                             webView.loadUrl(str_array_books[14]);
                 			   Toast.makeText(ebooks.this, R.string.preview, Toast.LENGTH_LONG).show();
                 			   
                 			   webView.setWebViewClient(new WebViewClient() {
                     			@Override
                     			public boolean shouldOverrideUrlLoading(WebView view, String url) {
                     				if (url.startsWith("http")) {
                     					view.loadUrl(url);
                     					return true;
                     				} else 
                     					{
                     					return super.shouldOverrideUrlLoading(view, url);
                     					}
                     				}
                     			});
                 		   }
                 	  });
                 	
  	                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
  	                	   public void onClick(DialogInterface dialog, int which) {
  		                	
  	                		downloaderThread = new DownloaderThread_ebook(thisActivity, strarray[14]);
  	           				downloaderThread.start();
  	                   	}
  	                   	  });
  		           AlertDialog alertdialog=builder.create();
  	               alertdialog.show();
              	
                  }
                if(position == 15){
                	 builder.setPositiveButton("Preview",new DialogInterface.OnClickListener() {
                 		   public void onClick(DialogInterface dialog, int which) {
                 			   
                             setContentView(R.layout.webview);
                             final WebView webView = (WebView) findViewById(R.id.webview);
                             webView.getSettings().setJavaScriptEnabled(true);
                             webView.loadUrl(str_array_books[15]);
                 			   Toast.makeText(ebooks.this, R.string.preview, Toast.LENGTH_LONG).show();
                 			   
                 			   webView.setWebViewClient(new WebViewClient() {
                     			@Override
                     			public boolean shouldOverrideUrlLoading(WebView view, String url) {
                     				if (url.startsWith("http")) {
                     					view.loadUrl(url);
                     					return true;
                     				} else 
                     					{
                     					return super.shouldOverrideUrlLoading(view, url);
                     					}
                     				}
                     			});
                 		   }
                 	  });
                 	
  	                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
  	                	   public void onClick(DialogInterface dialog, int which) {
  		                	
  	                		downloaderThread = new DownloaderThread_ebook(thisActivity, strarray[15]);
  	           				downloaderThread.start();
  	                   	}
  	                   	  });
  		           AlertDialog alertdialog=builder.create();
  	               alertdialog.show();
              	
                  }
                if(position == 16){
                	 builder.setPositiveButton("Preview",new DialogInterface.OnClickListener() {
                 		   public void onClick(DialogInterface dialog, int which) {
                 			   
                             setContentView(R.layout.webview);
                             final WebView webView = (WebView) findViewById(R.id.webview);
                             webView.getSettings().setJavaScriptEnabled(true);
                             webView.loadUrl(str_array_books[16]);
                 			   Toast.makeText(ebooks.this, R.string.preview, Toast.LENGTH_LONG).show();
                 			   
                 			   webView.setWebViewClient(new WebViewClient() {
                     			@Override
                     			public boolean shouldOverrideUrlLoading(WebView view, String url) {
                     				if (url.startsWith("http")) {
                     					view.loadUrl(url);
                     					return true;
                     				} else 
                     					{
                     					return super.shouldOverrideUrlLoading(view, url);
                     					}
                     				}
                     			});
                 		   }
                 	  });
                 	
  	                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
  	                	   public void onClick(DialogInterface dialog, int which) {
  		                	
  	                		downloaderThread = new DownloaderThread_ebook(thisActivity, strarray[16]);
  	           				downloaderThread.start();
  	                   	}
  	                   	  });
  		           AlertDialog alertdialog=builder.create();
  	               alertdialog.show();
              	
                  }
                if(position == 17){
                	 builder.setPositiveButton("Preview",new DialogInterface.OnClickListener() {
               		   public void onClick(DialogInterface dialog, int which) {
               			   
                           setContentView(R.layout.webview);
                           final WebView webView = (WebView) findViewById(R.id.webview);
                           webView.getSettings().setJavaScriptEnabled(true);
                           webView.loadUrl(str_array_books[17]);
               			   Toast.makeText(ebooks.this, R.string.preview, Toast.LENGTH_LONG).show();
               			   
               			   webView.setWebViewClient(new WebViewClient() {
                   			@Override
                   			public boolean shouldOverrideUrlLoading(WebView view, String url) {
                   				if (url.startsWith("http")) {
                   					view.loadUrl(url);
                   					return true;
                   				} else 
                   					{
                   					return super.shouldOverrideUrlLoading(view, url);
                   					}
                   				}
                   			});
               		   }
               	  });
               	
	                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
	                	   public void onClick(DialogInterface dialog, int which) {
		                	
	                		downloaderThread = new DownloaderThread_ebook(thisActivity, strarray[17]);
	           				downloaderThread.start();
	                   	}
	                   	  });
		           AlertDialog alertdialog=builder.create();
	               alertdialog.show();
            	
                }
            	
            }
        });
 
    }
 
    public void prepareList2()
    {
          desc = new ArrayList<String>();
 
          desc.add("C Herbert Schildt");
          desc.add("C# Herbert Schildt");
          desc.add("C++ Herbert Schildt");
          desc.add("Mastering C++ \n   Venugopal");
          desc.add("    Core Java   \nFundamentals");
          desc.add("Java2 Herbert Schildt");
          desc.add("Computer Networks  \n           Frouzan");
	      desc.add("Computer Networks  \n       Tanenbaum");
          desc.add("Artificial Intelligence \n           Russell");
          desc.add("Network Security\n William Stallings");
	      desc.add("Database Systems   \n        Navathe");
	      desc.add("Algorithm Cormen");
	      desc.add("Software Engineering\n         Pressman");
	      desc.add("Operating System\n          Galvin");
	      desc.add("Mobile Communications\n              Schiller");
	      desc.add("Compilers Aho");
	      desc.add("   Digital Signal   \nProcessing Smith");
	      desc.add("Digital Design \n Morris Mano");
	      
          image = new ArrayList<Integer>();
          image.add(R.drawable.pdf);
          image.add(R.drawable.pdf);
          image.add(R.drawable.pdf);
          image.add(R.drawable.pdf);
          image.add(R.drawable.pdf);
          image.add(R.drawable.pdf);
          image.add(R.drawable.pdf);
          image.add(R.drawable.pdf);
          image.add(R.drawable.pdf);
          image.add(R.drawable.pdf);
          image.add(R.drawable.pdf);
          image.add(R.drawable.pdf);
          image.add(R.drawable.pdf);
          image.add(R.drawable.pdf);
          image.add(R.drawable.pdf);
          image.add(R.drawable.pdf);
          image.add(R.drawable.pdf);
          image.add(R.drawable.pdf);
    
    }
    public Handler activityHandler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
				
				case MESSAGE_UPDATE_PROGRESS_BAR:
					if(progressDialog != null)
					{
						int currentProgress = msg.arg1;
						progressDialog.setProgress(currentProgress);
					}
					break;
				
			
				case MESSAGE_CONNECTING_STARTED:
					if(msg.obj != null && msg.obj instanceof String)
					{
						String url = (String) msg.obj;
						// truncate the url
						if(url.length() > 16)
						{
							String tUrl = url.substring(0, 15);
							tUrl += "...";
							url = tUrl;
						}
						String pdTitle = thisActivity.getString(R.string.progress_dialog_title_connecting);
						String pdMsg = thisActivity.getString(R.string.progress_dialog_message_prefix_connecting);
						pdMsg += " " + url;
						
						dismissCurrentProgressDialog();
						progressDialog = new ProgressDialog(thisActivity);
						progressDialog.setTitle(pdTitle);
						progressDialog.setMessage(pdMsg);
						progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
						progressDialog.setIndeterminate(true);
						// set the message to be sent when this dialog is canceled
						Message newMsg = Message.obtain(this, MESSAGE_DOWNLOAD_CANCELED);
						progressDialog.setCancelMessage(newMsg);
						progressDialog.show();
					}
					break;
					
			
				case MESSAGE_DOWNLOAD_STARTED:
					// obj will contain a String representing the file name
					if(msg.obj != null && msg.obj instanceof String)
					{
						int maxValue = msg.arg1;
						String fileName = (String) msg.obj;
						String pdTitle = thisActivity.getString(R.string.progress_dialog_title_downloading);
						String pdMsg = thisActivity.getString(R.string.progress_dialog_message_prefix_downloading);
						pdMsg += " " + fileName;
						
						dismissCurrentProgressDialog();
						progressDialog = new ProgressDialog(thisActivity);
						progressDialog.setTitle(pdTitle);
						progressDialog.setMessage(pdMsg);
						progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
						progressDialog.setProgress(0);
						progressDialog.setMax(maxValue);
						// set the message to be sent when this dialog is canceled
						Message newMsg = Message.obtain(this, MESSAGE_DOWNLOAD_CANCELED);
						progressDialog.setCancelMessage(newMsg);
						progressDialog.setCancelable(true);
						progressDialog.show();
					}
					break;
				
		
				case MESSAGE_DOWNLOAD_COMPLETE:
					dismissCurrentProgressDialog();
					displayMessage(getString(R.string.user_message_download_complete));
					break;
					
			
				case MESSAGE_DOWNLOAD_CANCELED:
					if(downloaderThread != null)
					{
						downloaderThread.interrupt();
					}
					dismissCurrentProgressDialog();
					displayMessage(getString(R.string.user_message_download_canceled));
					break;
				
			
				case MESSAGE_ENCOUNTERED_ERROR:
					// obj will contain a string representing the error message
					if(msg.obj != null && msg.obj instanceof String)
					{
						String errorMessage = (String) msg.obj;
						dismissCurrentProgressDialog();
						displayMessage(errorMessage);
					}
					break;
					
				default:
					break;
			}
		}
	};
	
	
	public void dismissCurrentProgressDialog()
	{
		if(progressDialog != null)
		{
			progressDialog.hide();
			progressDialog.dismiss();
			progressDialog = null;
		}
	}
	
	
	public void displayMessage(String message)
	{
		if(message != null)
		{
			Toast.makeText(thisActivity, message, Toast.LENGTH_SHORT).show();
		}
	}
	public void onBackPressed() {
		Intent intent = new Intent(this, LearnActivity.class);
		startActivity(intent);
		this.finish();
	}

}
