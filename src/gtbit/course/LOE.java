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

public class LOE extends Activity {
    /** Called when the activity is first created. */
	
	TextView title;
	ListView list;
	ArrayAdapter<String> loe_list;
	final int Cor_Flag = CourseActivity.retFlag();
	public static final int MESSAGE_DOWNLOAD_STARTED = 1000;
	public static final int MESSAGE_DOWNLOAD_COMPLETE = 1001;
	public static final int MESSAGE_UPDATE_PROGRESS_BAR = 1002;
	public static final int MESSAGE_DOWNLOAD_CANCELED = 1003;
	public static final int MESSAGE_CONNECTING_STARTED = 1004;
	public static final int MESSAGE_ENCOUNTERED_ERROR = 1005;
	private LOE thisActivity;
	private Thread downloaderThread;
	private ProgressDialog progressDialog;
   
	public String[] LOE1 = {
			"https://dl.dropbox.com/s/d7oo3a0hurzdeoh/Physics1_loe.doc",
			"https://dl.dropbox.com/s/xhz1k5f6r5dnr7n/Chem1_loe.doc",
			"https://dl.dropbox.com/s/zodqys81qijdpgo/AutoCAD_loe.doc"
			};
	
	public String[] LOE2 = {
			"https://dl.dropbox.com/s/klfxcwijl5snkcn/ElectricalScience_loe.doc",
			"https://dl.dropbox.com/s/rg6l6n5ycmmu7gy/IP_loe.doc"
			};
	
	public String[] LOE3 = {
			"https://dl.dropbox.com/s/wk09p8drn607cvw/CktnSys_loe.doc",
			"https://dl.dropbox.com/s/jcamrlkbk3nmxg3/OOP_lop.doc",
			"https://dl.dropbox.com/s/y5kwl0xaodrt7rn/DS_loe.doc"
			};
	
	public String[] LOE4 = {
			"https://dl.dropbox.com/s/ugsr226nd8zjpsm/dcssem6loe.doc",
			"https://dl.dropbox.com/s/z8aqoyemn68p8fs/adasem6loe.doc",
			"https://dl.dropbox.com/s/037etsxwxqy59dx/cgsem4lop.doc",
			"https://dl.dropbox.com/s/tm4lpz2gmf5w60y/ccssem4loe.doc",
			"https://dl.dropbox.com/s/mjowul7e8ba7bls/loeemecsem4.doc",
			"https://dl.dropbox.com/s/tgcsbaup3922vd6/ps1eeesem4loe.doc",
			"https://dl.dropbox.com/s/ph5grxsyzmnbg6d/sesem4lop.doc"
			};
	
	public String[] LOE5 = {
			"https://dl.dropbox.com/s/q74lyad40hsfsre/dbmslopsem5.doc",
			"https://dl.dropbox.com/s/epgjrhvl7oohsqx/javalopsem5.doc",
			"https://dl.dropbox.com/s/efzq1687txvhw14/linuxloesem5.doc",
			"https://dl.dropbox.com/s/s06qycm7hkizyo3/listofexpdcssem5.doc",
			"https://dl.dropbox.com/s/o7p7m8eo74w8slr/listofexperimentscs2sem5.doc",
			"https://dl.dropbox.com/s/jycdcy7a50w5372/listofxperimentscesem5.doc"
			};
	
	public String[] LOE6 = {
			"https://dl.dropbox.com/s/i5yvi05jvvtfsam/dcssem6loe.doc",
			"https://dl.dropbox.com/s/pt2wywso1touzsd/dwdmsem6loe.docx",
			"https://dl.dropbox.com/s/q1z3vuma4gdh734/loemesem5.doc",
			"https://dl.dropbox.com/s/v7w1qcjqh74b2wr/mtsem6loe.doc",
			"https://dl.dropbox.com/s/8nuaa6lm5di45ua/oosesem6lop.docx",
			"https://dl.dropbox.com/s/ukad1e540wdo4vw/tcsem6loe.doc"
			};
	
	public String[] LOE7 = {
			"https://dl.dropbox.com/s/ohi7ti160fvonak/listofexpoptcomsem7.doc",
			"https://dl.dropbox.com/s/rxo6zx6yhrqd3l9/loempsem7.doc",
			"https://dl.dropbox.com/s/7fv322vouvnpoyc/lopacnsem8.docx",
			"https://dl.dropbox.com/s/m81wjli9ghpnb4s/mclopsem7.doc"
			};
	
	public String[] LOE8= {
			"https://dl.dropbox.com/s/qkqa9smwal04byt/EmbeddedSystemsLab8loe.doc",
			"https://dl.dropbox.com/s/m54a1xsoqye3sps/aisem8plist.doc",
			"https://dl.dropbox.com/s/jvackkdrvt6f1r0/nssem8plist.doc",
			"https://dl.dropbox.com/s/95opc9sn2qraajn/stsem8lop.doc"
			};
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.loe);
		getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);

        thisActivity = this;
        downloaderThread = null;
        progressDialog = null;

		list = (ListView) findViewById(R.id.listView_loe);

		String[] Sem1 = { "Applied Physics-I","Engineering Chemistry",
				"Introduction to Computers and AutoCAD"
				};
		
		String[] Sem2 = { "Electrical Science", "Introduction to Programming"
		 		};
		
		String[] Sem3 = { "Circuits and System","Object Oriented Programming using C++",
				"Data Structures"
		 		};
		
		String[] Sem4 = { "Digital Circuits & Systems-I","Algorithm Analysis & Design",
				"Computer Graphics","Communication Circuits and System 1",
				"Electro Mechanical Energy Conversion","Power System-I",
				"Software Engineering"
		 };
		
		String[] Sem5 = { "Database Management System","JAVA",
				"Linux","Digital Circuits & System-II",
				"Digital Communication-I","Control System"
		 };
		
		String[] Sem6 = { "Digital Communication-II","Data Warehousing and Data Mining",
				"Microwave Engineering","Multimedia Technologies",
				"Object Oriented Software Engineering","Telecommunication Network"
		 };
		
		String[] Sem7 = { "Optical Communication","Microprocessor",
				"Advanced Computer Network","Mobile Computing"
		 };
		
		String[] Sem8 = { "Embedded System","Artificial Intelligence",
				"Network Security","Software Testing"
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
							
							
							downloaderThread = new Downloader_LOE(thisActivity, LOE1[0]);
							downloaderThread.start();
							break;
						case 1:
							downloaderThread = new Downloader_LOE(thisActivity, LOE1[1]);
							downloaderThread.start();
							
							break;
						case 2:
							downloaderThread = new Downloader_LOE(thisActivity, LOE1[2]);
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
							downloaderThread = new Downloader_LOE(thisActivity, LOE2[0]);
							downloaderThread.start();
							
							break;
						case 1:
							downloaderThread = new Downloader_LOE(thisActivity, LOE2[1]);
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
							downloaderThread = new Downloader_LOE(thisActivity, LOE3[0]);
							downloaderThread.start();
							
							break;
						case 1:
							downloaderThread = new Downloader_LOE(thisActivity, LOE3[1]);
							downloaderThread.start();
							break;
						case 2:
							downloaderThread = new Downloader_LOE(thisActivity, LOE3[2]);
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
							downloaderThread = new Downloader_LOE(thisActivity, LOE4[0]);
							downloaderThread.start();
							break;
						case 1:
							downloaderThread = new Downloader_LOE(thisActivity, LOE4[1]);
							downloaderThread.start();
							break;
						case 2:
							downloaderThread = new Downloader_LOE(thisActivity, LOE4[2]);
							downloaderThread.start();
							break;
						case 3:
							downloaderThread = new Downloader_LOE(thisActivity, LOE4[3]);
							downloaderThread.start();
							break;
						case 4:
							downloaderThread = new Downloader_LOE(thisActivity, LOE4[4]);
							downloaderThread.start();
							break;
						case 5:
							downloaderThread = new Downloader_LOE(thisActivity, LOE4[5]);
							downloaderThread.start();
							break;
						case 6:
							downloaderThread = new Downloader_LOE(thisActivity, LOE4[6]);
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
							downloaderThread = new Downloader_LOE(thisActivity, LOE5[0]);
							downloaderThread.start();
							break;
						case 1:
							downloaderThread = new Downloader_LOE(thisActivity, LOE5[1]);
							downloaderThread.start();
							break;
						case 2:
							downloaderThread = new Downloader_LOE(thisActivity, LOE5[2]);
							downloaderThread.start();
							break;
						case 3:
							downloaderThread = new Downloader_LOE(thisActivity, LOE5[3]);
							downloaderThread.start();
							break;
						case 4:
							downloaderThread = new Downloader_LOE(thisActivity, LOE5[4]);
							downloaderThread.start();
							break;
						case 5:
							downloaderThread = new Downloader_LOE(thisActivity, LOE5[5]);
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
							downloaderThread = new Downloader_LOE(thisActivity, LOE6[0]);
							downloaderThread.start();
							break;
						case 1:
							downloaderThread = new Downloader_LOE(thisActivity, LOE6[1]);
							downloaderThread.start();
							break;
						case 2:
							downloaderThread = new Downloader_LOE(thisActivity, LOE6[2]);
							downloaderThread.start();
							break;
						case 3:
							downloaderThread = new Downloader_LOE(thisActivity, LOE6[3]);
							downloaderThread.start();
							break;
						case 4:
							downloaderThread = new Downloader_LOE(thisActivity, LOE6[4]);
							downloaderThread.start();
							break;
						case 5:
							downloaderThread = new Downloader_LOE(thisActivity, LOE6[5]);
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
							downloaderThread = new Downloader_LOE(thisActivity, LOE7[0]);
							downloaderThread.start();
							break;
						case 1:
							downloaderThread = new Downloader_LOE(thisActivity, LOE7[1]);
							downloaderThread.start();
							break;
						case 2:
							downloaderThread = new Downloader_LOE(thisActivity, LOE7[2]);
							downloaderThread.start();
							break;
						case 3:
							downloaderThread = new Downloader_LOE(thisActivity, LOE7[3]);
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
							downloaderThread = new Downloader_LOE(thisActivity, LOE8[0]);
							downloaderThread.start();
							break;
						case 1:
							downloaderThread = new Downloader_LOE(thisActivity, LOE8[1]);
							downloaderThread.start();
							break;
						case 2:
							downloaderThread = new Downloader_LOE(thisActivity, LOE8[2]);
							downloaderThread.start();
							break;
						case 3:
							downloaderThread = new Downloader_LOE(thisActivity, LOE8[3]);
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

