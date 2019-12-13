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

public class Question_Bank extends Activity {
	
	TextView title;
	ListView list;
	ArrayAdapter<String> QB_list;
	final int Cor_Flag = CourseActivity.retFlag();
	public static final int MESSAGE_DOWNLOAD_STARTED = 1000;
	public static final int MESSAGE_DOWNLOAD_COMPLETE = 1001;
	public static final int MESSAGE_UPDATE_PROGRESS_BAR = 1002;
	public static final int MESSAGE_DOWNLOAD_CANCELED = 1003;
	public static final int MESSAGE_CONNECTING_STARTED = 1004;
	public static final int MESSAGE_ENCOUNTERED_ERROR = 1005;
	private Question_Bank thisActivity;
	private Thread downloaderThread;
	private ProgressDialog progressDialog;
   
	public String[] QB1 =  { "https://dl.dropbox.com/s/xeme4w4hlcqc5a6/assgnqbistsem1.doc",
			"https://dl.dropbox.com/s/m25tlu1gvhnabpu/qbankmaths1sem.doc",
			"https://dl.dropbox.com/s/mhfji6f245aou4u/qbankphy.doc",
			"https://dl.dropbox.com/s/ckuceg2biznb43o/qbanksem1chem.doc",
			"https://dl.dropbox.com/s/0pxwp6iiak4qn26/qnaireiitcsem1.doc"
	};
	
	public String[] QB2 = { "https://dl.dropbox.com/s/gttilhx5gcfa4c3/qbank2phy.doc",
			"https://dl.dropbox.com/s/gr64469kqmh8rmf/qbankchemsem2.doc",
			"https://dl.dropbox.com/s/unulruohmndk4sc/qbankescience2sem.doc",
			"https://dl.dropbox.com/s/rwiy6kcw3fadngf/qbankipcmsem2.doc",
			"https://dl.dropbox.com/s/1d4vsz7b23pmfv7/qbankmat2sem.doc",
			"https://dl.dropbox.com/s/oct7047gr4p1c5f/qbanksem2csk.doc"
	};
	
	public String[] QB3 = { "https://dl.dropbox.com/s/s4h5mjt99o0lua4/AEqbank.doc",
			"https://dl.dropbox.com/s/kez4ql58oodfkth/dsqbanksem3.doc",
			"https://dl.dropbox.com/s/6k3otun2d4w54rp/oopsqbanksem3.doc",
			"https://dl.dropbox.com/s/a7281l2zrp2zy4h/qbankcssem3.doc",
			"https://dl.dropbox.com/s/gb4qvx76hc0n04v/qbankfcssem3.doc",
			"https://dl.dropbox.com/s/dp8w4f4acnwnqk9/qbankmaths3sem.doc",
			"https://dl.dropbox.com/s/x4nhkc9z3duiiz5/qbanksignalnsys3sem.doc"
	};
	
	public String[] QB4 = { "https://dl.dropbox.com/s/2kaav0qm4oj5eau/adasem4qbank.doc",
			"https://dl.dropbox.com/s/uzl2t3nviw5mmdv/analogeeesem4qbank.doc",
			"https://dl.dropbox.com/s/oxxdoojs25bwwjk/CCSqbankCS_IT.doc",
			"https://dl.dropbox.com/s/f3wlq62thigdq29/ccssem4qbankECE.doc",
			"https://dl.dropbox.com/s/uzwf8pme0fcnoy3/cgsem4qbank.doc",
			"https://dl.dropbox.com/s/8nn4wlqrjcj0yxm/emetsem4qbank.docx",
			"https://dl.dropbox.com/s/tunwi9es4f2dm1b/ps1eeesem4qbank.doc",
			"https://dl.dropbox.com/s/g5bfmqmuhcq5y0j/sesem4qb.doc"

	};
	
	public String[] QB5 = { "https://dl.dropbox.com/s/sdurvrhdk138chk/caqb1sem5.doc",
			"https://dl.dropbox.com/s/j0m5uvpdemnwss5/dbmsqbanksem5.doc",
			"https://dl.dropbox.com/s/wb1j9ccino1s83o/javaqbanksem5.doc",
			"https://dl.dropbox.com/s/vjk7tchkz4wmbfh/obqbanksem5.doc",
			"https://dl.dropbox.com/s/xfxgi0ew4mslb73/qbankdcsem5.doc",
			"https://dl.dropbox.com/s/b3wwep5g3mcvjo1/qbankdcssem5.doc",
			"https://dl.dropbox.com/s/akz9dfzo0d7o61i/qbcesem5.doc"
	};
	
	public String[] QB6 = { "https://dl.dropbox.com/s/7hvnqikxei2w6uo/cnsem6qbank.docx",
			"https://dl.dropbox.com/s/3p3pqoa0oc74tiu/dcssem6aqbank.doc",
			"https://dl.dropbox.com/s/gekb33w10c135np/dspsem6qb.doc",
			"https://dl.dropbox.com/s/wdm1butqgkgcswv/dwdmsem6qb.doc",
			"https://dl.dropbox.com/s/lqiec7db6miqbm5/lqbankmesem5.doc",
			"https://dl.dropbox.com/s/nlpedthwvtmepr8/mpCSsem6qbank.doc",
			"https://dl.dropbox.com/s/glf28hop92h0put/mpEEEsem6qbank.doc",
			"https://dl.dropbox.com/s/hgg6cjalfuj23ee/mtsem6qbank.doc",
			"https://dl.dropbox.com/s/wial95eipbgluum/tcsem6qbank.doc",
			"https://dl.dropbox.com/s/78m8o2cg3qnsjml/vlsisem6qb.doc"
	};
	
	public String[] QB7 = { "https://dl.dropbox.com/s/ivulw8hd4qemkho/edriveqbanksem7.doc",
			"https://dl.dropbox.com/s/a39oid6999t3xnp/qbank8086mpsem7.doc",
			"https://dl.dropbox.com/s/hgg6cjalfuj23ee/mtsem6qbank.doc"
	};
	
	public String[] QB8 = { "https://dl.dropbox.com/s/nl0a7ix844soymg/essem8qbank.doc",
			"https://dl.dropbox.com/s/yc1re7iw2nxx4xt/stsem8qbank.doc"
	};
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.ques_bank);
		getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);

        thisActivity = this;
        downloaderThread = null;
        progressDialog = null;

		list = (ListView) findViewById(R.id.listView1);

		String[] Sem1 = { "Impact of Science and Technology","Applied Mathematics-I",
				"Applied Physics-I","Engineering Chemistry", "Word Questionnaire"
				 };
		
		String[] Sem2 = {  "Applied Physics-II","Environmental Studies",
				"Electrical Science","Introduction to Programming",
				"Applied Mathematics-II", "Communication Skills-II"
		 };
		
		String[] Sem3 = { "Analog Electronics", "Data Structures",
				"Object Oriented Programming", "Circuits and System",
				"Foundation of Computer System","Applied Mathematics-III",
				"Signal and System"
		 };
		
		String[] Sem4 = { "Algorithm Analysis & Design", "Analog Electronics-II",
				"Communication System CS/IT", "Communication System ECE",
				"Computer Graphics", "Electromagnetic Field Theory",
				"Power System-I", "Software Engineering"
		 };
		
		String[] Sem5 = { "Computer Architecture", "Database Management System",
				"JAVA Programming", "Organisational Behaviour",
				"Digital Communication-I", "Digital Circuits & System-II",
				"Control Enginerring"
		 };
		
		String[] Sem6 = { "Computer Networks", "Digital Communication-II",
				"Digital Signal Processing", "Data Warehousing and Data Mining",
				"Microwave Engineering", "Microprocessor CSE","Microprocessor EEE",
				"Multimedia Technologies", "Telecommunication Network",
				"VLSI Design"
		 };
		String[] Sem7 = { "Electric Drives", "Microprocessor System-II",
				"Multimedia Technologies"
		};
		
		String[] Sem8 = { "Embedded System", "Software Testing"
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
							downloaderThread = new Downloader_QB(thisActivity, QB1[0]);
							downloaderThread.start();
							
							break;
						case 1:
						downloaderThread = new Downloader_QB(thisActivity, QB1[1]);
						downloaderThread.start();
							
							break;
						case 2:
							downloaderThread = new Downloader_QB(thisActivity, QB1[2]);
							downloaderThread.start();
							break;
						case 3:
							downloaderThread = new Downloader_QB(thisActivity, QB1[3]);
							downloaderThread.start();
							break;
						case 4:
							downloaderThread = new Downloader_QB(thisActivity, QB1[4]);
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
							downloaderThread = new Downloader_QB(thisActivity, QB2[0]);
							downloaderThread.start();
							
							break;
						case 1:
							downloaderThread = new Downloader_QB(thisActivity, QB2[1]);
							downloaderThread.start();
							break;
						case 2:
							downloaderThread = new Downloader_QB(thisActivity, QB2[2]);
							downloaderThread.start();
							break;
						case 3:
							downloaderThread = new Downloader_QB(thisActivity, QB2[3]);
							downloaderThread.start();
							break;
						case 4:
							downloaderThread = new Downloader_QB(thisActivity, QB2[4]);
							downloaderThread.start();
							break;
						case 5:
							downloaderThread = new Downloader_QB(thisActivity, QB2[5]);
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
							downloaderThread = new Downloader_QB(thisActivity, QB3[0]);
							downloaderThread.start();
							
							break;
						case 1:
							downloaderThread = new Downloader_QB(thisActivity, QB3[1]);
							downloaderThread.start();
							break;
						case 2:
							downloaderThread = new Downloader_QB(thisActivity, QB3[2]);
							downloaderThread.start();
							break;
						case 3:
							downloaderThread = new Downloader_QB(thisActivity, QB3[3]);
							downloaderThread.start();
							break;
						case 4:
							downloaderThread = new Downloader_QB(thisActivity, QB3[4]);
							downloaderThread.start();
							break;
						case 5:
							downloaderThread = new Downloader_QB(thisActivity, QB3[5]);
							downloaderThread.start();
							break;
						case 6:
							downloaderThread = new Downloader_QB(thisActivity, QB3[6]);
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
							downloaderThread = new Downloader_QB(thisActivity, QB4[0]);
							downloaderThread.start();
							
							break;
						case 1:
							downloaderThread = new Downloader_QB(thisActivity, QB4[1]);
							downloaderThread.start();
							break;
						case 2:
							downloaderThread = new Downloader_QB(thisActivity, QB4[2]);
							downloaderThread.start();
							break;
						case 3:
							downloaderThread = new Downloader_QB(thisActivity, QB4[3]);
							downloaderThread.start();
							break;
						case 4:
							downloaderThread = new Downloader_QB(thisActivity, QB4[4]);
							downloaderThread.start();
							break;
						case 5:
							downloaderThread = new Downloader_QB(thisActivity, QB4[5]);
							downloaderThread.start();
							break;
						case 6:
							downloaderThread = new Downloader_QB(thisActivity, QB4[6]);
							downloaderThread.start();
							break;
						case 7:
							downloaderThread = new Downloader_QB(thisActivity, QB4[7]);
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
							downloaderThread = new Downloader_QB(thisActivity, QB5[0]);
							downloaderThread.start();
							
							break;
						case 1:
							downloaderThread = new Downloader_QB(thisActivity, QB5[1]);
							downloaderThread.start();
							break;
						case 2:
							downloaderThread = new Downloader_QB(thisActivity, QB5[2]);
							downloaderThread.start();
							break;
						case 3:
							downloaderThread = new Downloader_QB(thisActivity, QB5[3]);
							downloaderThread.start();
							break;
						case 4:
							downloaderThread = new Downloader_QB(thisActivity, QB5[4]);
							downloaderThread.start();
							break;
						case 5:
							downloaderThread = new Downloader_QB(thisActivity, QB5[5]);
							downloaderThread.start();
							break;
						case 6:
							downloaderThread = new Downloader_QB(thisActivity, QB5[6]);
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
							
							downloaderThread = new Downloader_QB(thisActivity, QB6[0]);
							downloaderThread.start();
							break;
						case 1:
							downloaderThread = new Downloader_QB(thisActivity, QB6[1]);
							downloaderThread.start();	
							break;
						case 2:
							downloaderThread = new Downloader_QB(thisActivity, QB6[2]);
							downloaderThread.start();
							break;
						case 3:
							downloaderThread = new Downloader_QB(thisActivity, QB6[3]);
							downloaderThread.start();
							break;
						case 4:
							downloaderThread = new Downloader_QB(thisActivity, QB6[4]);
							downloaderThread.start();
							break;
						case 5:
							downloaderThread = new Downloader_QB(thisActivity, QB6[5]);
							downloaderThread.start();
							break;
						case 6:
							downloaderThread = new Downloader_QB(thisActivity, QB6[6]);
							downloaderThread.start();
							break;
						case 7:
							downloaderThread = new Downloader_QB(thisActivity, QB6[7]);
							downloaderThread.start();
							break;
						case 8:
							downloaderThread = new Downloader_QB(thisActivity, QB6[8]);
							downloaderThread.start();
							break;
						case 9:
							downloaderThread = new Downloader_QB(thisActivity, QB6[9]);
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
							downloaderThread = new Downloader_QB(thisActivity, QB7[0]);
							downloaderThread.start();
							break;
						case 1:
							downloaderThread = new Downloader_QB(thisActivity, QB7[1]);
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
							downloaderThread = new Downloader_QB(thisActivity, QB8[0]);
							downloaderThread.start();
							break;
						case 1:
							downloaderThread = new Downloader_QB(thisActivity, QB8[1]);
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

