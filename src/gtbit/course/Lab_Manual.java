package gtbit.course;


import eHandy.gtbit.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Lab_Manual extends Activity {
    /** Called when the activity is first created. */
	
	TextView title;
	ListView list;
	ArrayAdapter<String> manual_list;
	final int Cor_Flag = CourseActivity.retFlag();
	public static final int MESSAGE_DOWNLOAD_STARTED = 1000;
	public static final int MESSAGE_DOWNLOAD_COMPLETE = 1001;
	public static final int MESSAGE_UPDATE_PROGRESS_BAR = 1002;
	public static final int MESSAGE_DOWNLOAD_CANCELED = 1003;
	public static final int MESSAGE_CONNECTING_STARTED = 1004;
	public static final int MESSAGE_ENCOUNTERED_ERROR = 1005;
	private Lab_Manual thisActivity;
	private Thread downloaderThread;
	private ProgressDialog progressDialog;
   
	public String[] manual1 = { "https://dl.dropbox.com/s/37p9cx4x040fzkx/autoCAD.pdf",
			"https://dl.dropbox.com/s/70s2sweciudajhk/autocadmanual.doc",
			"https://dl.dropbox.com/s/k92nq5wk12j4xmn/pptmanual.doc"
	};
	
	public String[] manual2 = { "https://dl.dropbox.com/s/7ewhlgsz177meqw/lmescience2sem.doc"
	};
	
	public String[] manual3 = { "https://dl.dropbox.com/s/xvfk1hq6cb75i82/AElabmanual.pdf"
	};
	
	public String[] manual4 = { "https://dl.dropbox.com/s/90ek9vrj41s0kap/dcssem6lmt.doc",
			"https://dl.dropbox.com/s/qz6a27sclydwz8r/sesem4lmannual.doc"
	};
	
	public String[] manual5 = { "https://dl.dropbox.com/s/c5ri9vh0p8uf9fp/linuxlmsem5.doc",
			"https://dl.dropbox.com/s/xz1n02ktnjvblu8/logic_gate_manual.pdf"
	};
	
	public String[] manual6 = { "https://dl.dropbox.com/s/1oebio7dpm0cvb6/microprocessor_labmanual.pdf",
			"https://dl.dropbox.com/s/awfeumqt2z5uo5q/ps2sem6lmannualnsyb.doc"
	};
	
	public String[] manual7 = { "https://dl.dropbox.com/s/96gaqfda54k5chb/ACN_labManual.pdf",
			"https://dl.dropbox.com/s/69yvyz8sij6nzvf/MW_OC_LAB_Manual.pdf"
	};
	
	public String[] manual8 = { "https://dl.dropbox.com/s/ckpf1l1hsnlexe9/AI_lab.pdf",
			"https://dl.dropbox.com/s/cqveye90rne5h42/NS_LABManual.pdf"
	};
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.lab_manual);
		getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);

        thisActivity = this;
        downloaderThread = null;
        progressDialog = null;

		list = (ListView) findViewById(R.id.listView1);

		String[] Sem1 = { "AutoCAD Tutorial", "AutoCAD" ,"Power Point Presentations"
				 };
		String[] Sem2 = { "Electrical Science"
		 };
		String[] Sem3 = { "Analog Electronics"
		 };
		String[] Sem4 = { "Digital Circuits & Systems-I" ,"Software Engineering"
		};
		String[] Sem5 = { "Linux", "Digital Circuits & System"
		 };
		String[] Sem6 = { "Microprocessor", "Power System-II"
		 };
		String[] Sem7 = { "Advanced Computer Network", "Optical Communication"
		 };
		String[] Sem8 = { "Artificial Intelligence","Network Security"
		 };

		if (Cor_Flag==1)
		{
				
				list.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, Sem1));
				list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						switch (arg2) {
						case 0:
							downloaderThread = new Downloader_Manual(thisActivity, manual1[0]);
							downloaderThread.start();
							break;
						case 1:
							downloaderThread = new Downloader_Manual(thisActivity, manual1[1]);
							downloaderThread.start();
							break;
						case 2:
							downloaderThread = new Downloader_Manual(thisActivity, manual1[2]);
							downloaderThread.start();
							break;
						default:
							break;
						}
					}
				});
			}
		
		if (Cor_Flag==2)
		{
				
				list.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, Sem2));
				list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						switch (arg2) {
						case 0:
							downloaderThread = new Downloader_Manual(thisActivity, manual2[0]);
							downloaderThread.start();
							break;
						default:
							break;
						}
					}
				});
			}
		if (Cor_Flag==3)
		{
				
				list.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, Sem3));
				list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						switch (arg2) {
						case 0:
							downloaderThread = new Downloader_Manual(thisActivity, manual3[0]);
							downloaderThread.start();
							break;
						default:
							break;
						}
					}
				});
			}
		if (Cor_Flag==4)
		{
				
				list.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, Sem4));
				list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						switch (arg2) {
						case 0:
							downloaderThread = new Downloader_Manual(thisActivity, manual4[0]);
							downloaderThread.start();
							break;
						case 1:
							downloaderThread = new Downloader_Manual(thisActivity, manual4[1]);
							downloaderThread.start();
							break;
						default:
							break;
						}
					}
				});
			}
		if (Cor_Flag==5)
		{
				
				list.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, Sem5));
				list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						switch (arg2) {
						case 0:
							downloaderThread = new Downloader_Manual(thisActivity, manual5[0]);
							downloaderThread.start();
							break;
						case 1:
							downloaderThread = new Downloader_Manual(thisActivity, manual5[1]);
							downloaderThread.start();
							break;
						default:
							break;
						}
					}
				});
			}
		if (Cor_Flag==6)
		{
				
				list.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, Sem6));
				list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						switch (arg2) {
						case 0:
							downloaderThread = new Downloader_Manual(thisActivity, manual6[0]);
							downloaderThread.start();
							break;
						case 1:
							downloaderThread = new Downloader_Manual(thisActivity, manual6[1]);
							downloaderThread.start();
							break;
						default:
							break;
						}
					}
				});
			}
		if (Cor_Flag==7)
		{
				
				list.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, Sem7));
				list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						switch (arg2) {
						case 0:
							downloaderThread = new Downloader_Manual(thisActivity, manual7[0]);
							downloaderThread.start();
							break;
						case 1:
							downloaderThread = new Downloader_Manual(thisActivity, manual7[1]);
							downloaderThread.start();
							break;
						default:
							break;
						}
					}
				});
			}
		if (Cor_Flag==8)
		{
				
				list.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, Sem8));
				list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						switch (arg2) {
						case 0:
							downloaderThread = new Downloader_Manual(thisActivity, manual8[0]);
							downloaderThread.start();
							break;
						case 1:
							downloaderThread = new Downloader_Manual(thisActivity, manual8[1]);
							downloaderThread.start();
							break;
						default:
							break;
						}
					}
				});
			}
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
				Intent ex = new Intent(this, Course.class);
				startActivity(ex);
				this.finish();
			}	
}

