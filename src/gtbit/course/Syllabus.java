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

public class Syllabus extends Activity {
	
	TextView title;
	ListView list;
	ArrayAdapter<String> syllabus_list;
	final int Cor_Flag = CourseActivity.retFlag();
	public static final int MESSAGE_DOWNLOAD_STARTED = 1000;
	public static final int MESSAGE_DOWNLOAD_COMPLETE = 1001;
	public static final int MESSAGE_UPDATE_PROGRESS_BAR = 1002;
	public static final int MESSAGE_DOWNLOAD_CANCELED = 1003;
	public static final int MESSAGE_CONNECTING_STARTED = 1004;
	public static final int MESSAGE_ENCOUNTERED_ERROR = 1005;
	private Syllabus thisActivity;
	private Thread downloaderThread;
	private ProgressDialog progressDialog;
   
	public String[] Syllabus = { "http://ipu.ac.in/syllabus/affiliated/sybtechece.htm",
			"http://ipu.ac.in/syllabus/affiliated/sybtechcse.htm",
			"http://ipu.ac.in/syllabus/affiliated/sybtechit.htm",
			"http://ipu.ac.in/syllabus/affiliated/sybtecheee.htm"
	};
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.syllabus);
		getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);

        thisActivity = this;
        downloaderThread = null;
        progressDialog = null;

		list = (ListView) findViewById(R.id.listView1);

		String[] Stream = { "Electronics & Communications Engineering", 
				"Computer Science & Engineering",
				"Information Technology",
				"Electrical & Electronics Engineering"
				 };
		

			
				list.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, Stream));
				list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						
						switch (arg2) {
						case 0:
										downloaderThread = new Downloader_Syllabus(thisActivity, Syllabus[0]);
				   						downloaderThread.start();
				                   	
				         break;
						case 1:
			   						downloaderThread = new Downloader_Syllabus(thisActivity, Syllabus[1]);
			   						downloaderThread.start();
			           
							break;
							
						case 2:
							downloaderThread = new Downloader_Syllabus(thisActivity, Syllabus[2]);
				   						downloaderThread.start();
				                   
							break;
							
						case 3:
			   						downloaderThread = new Downloader_Syllabus(thisActivity, Syllabus[3]);
			   						downloaderThread.start();
			            
							break;
						default:
							break;
						}
					}
				});
			
	
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
