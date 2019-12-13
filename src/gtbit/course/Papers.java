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

public class Papers extends Activity {
    /** Called when the activity is first created. */
	
	TextView title;
	ListView list;
	ArrayAdapter<String> papers_list;
	final int Cor_Flag = CourseActivity.retFlag();
	public static final int MESSAGE_DOWNLOAD_STARTED = 1000;
	public static final int MESSAGE_DOWNLOAD_COMPLETE = 1001;
	public static final int MESSAGE_UPDATE_PROGRESS_BAR = 1002;
	public static final int MESSAGE_DOWNLOAD_CANCELED = 1003;
	public static final int MESSAGE_CONNECTING_STARTED = 1004;
	public static final int MESSAGE_ENCOUNTERED_ERROR = 1005;
	private Papers thisActivity;
	private Thread downloaderThread;
	private ProgressDialog progressDialog;

	public String[] ppr1 = { "https://dl.dropbox.com/s/5t8ysz9entqq2nh/CHEM1.pdf",
			"https://dl.dropbox.com/s/vmw4kkz2tdr87gl/COMM1.pdf",
			"https://dl.dropbox.com/s/3vdrp87m02bgrfa/ITC.pdf",
			"https://dl.dropbox.com/s/9gbjo36jv3ww2mf/Maths1.pdf",
			"https://dl.dropbox.com/s/e402vw4d9vc4862/MP.pdf",
			"https://dl.dropbox.com/s/zcnnf172bpf5m6k/PHY1.pdf"
		};
	
	public String[] ppr2 = { "https://dl.dropbox.com/s/gknfa4dnv7p13f0/CHEM2.pdf",
			"https://dl.dropbox.com/s/gjyyh8o0s35xgbv/COMM2.pdf",
			"https://dl.dropbox.com/s/0z7ihn0knaeukfn/EM.pdf",
			"https://dl.dropbox.com/s/w2s5iw89z4wbab0/ES.pdf",
			"https://dl.dropbox.com/s/uhbciq0w77yb2oj/IP.pdf",
			"https://dl.dropbox.com/s/anrco6ccya7t28y/MATHS2.pdf",
			"https://dl.dropbox.com/s/l60fiifx2r3d0zm/PHY2.pdf"
		};
	
	public String[] ppr3 = { "https://dl.dropbox.com/s/ds9lwx1yr9tynxz/AE.pdf",
			"https://dl.dropbox.com/s/2iczdjuyacu84rb/CircuitAndSystem.pdf",
			"https://dl.dropbox.com/s/pd0ods6c9a4ypel/DS.pdf",
			"https://dl.dropbox.com/s/ix4jeov377dnbbn/EEM.pdf",
			"https://dl.dropbox.com/s/lfvsd0urh7yxgew/EMEC.pdf",
			"https://dl.dropbox.com/s/nxwrjf754yd2dvl/FCS.pdf",
			"https://dl.dropbox.com/s/ryzn5l9ed4mmeri/Maths3.pdf",
			"https://dl.dropbox.com/s/4x9ahdbqmkth19a/OOP.pdf",
			"https://dl.dropbox.com/s/7hhd3zsdu9s6hof/SnS.pdf"
	};
	
	public String[] ppr4 = { "https://dl.dropbox.com/s/xn9p4ctdrmz02yi/ADA.pdf",
			"https://dl.dropbox.com/s/ifxy47o1yimkybv/CCS.pdf",
			"https://dl.dropbox.com/s/dn1xz4tw7eel10h/CG.pdf",
			"https://dl.dropbox.com/s/s8wxyixv6y1dguw/ControlEngg_I.pdf",
			"https://dl.dropbox.com/s/8dk3xdxg9bsxlh8/DCS1.pdf",
			"https://dl.dropbox.com/s/xcji76bzh3cwdwb/OS.pdf",
			"https://dl.dropbox.com/s/wm24dxcbceahcjl/SE.pdf"
	};
	
	public String[] ppr5 = { "https://dl.dropbox.com/s/7fulvz9c6g9gl4p/CA.pdf",
			"https://dl.dropbox.com/s/x0lnbip66lmihco/ControlEngg2.pdf",
			"https://dl.dropbox.com/s/7yorcmj0us6fa5h/CSC.pdf",
			"https://dl.dropbox.com/s/b3s56r7bohbhmxg/DBMS.pdf",
			"https://dl.dropbox.com/s/x8mbxjaqk1egqk5/DC.pdf",
			"https://dl.dropbox.com/s/91r8oi3arr7ugrp/DCS2.pdf",
			"https://dl.dropbox.com/s/dbtfoukp441kkww/Java.pdf",
			"https://dl.dropbox.com/s/guplznppftp1xq4/Linux.pdf",
			"https://dl.dropbox.com/s/1tkpvhhas8xl8jw/OB.pdf"
	};
	
	public String[] ppr6 = { "https://dl.dropbox.com/s/t5cg8idqnohbcip/CN.pdf",
			"https://dl.dropbox.com/s/o6ripawftx8iju5/DCS2IT.pdf",
			"https://dl.dropbox.com/s/6b0earknexuntes/DSP.pdf",
			"https://dl.dropbox.com/s/rum1ry5uiynjkiq/DWDM.pdf",
			"https://dl.dropbox.com/s/4xv93scf6j0l9ee/MicroWave.pdf",
			"https://dl.dropbox.com/s/s56gv5wghx2qpk4/MP.pdf",
			"https://dl.dropbox.com/s/gb3pj2tsk5e5loq/MT.pdf",
			"https://dl.dropbox.com/s/3rbp1axzc2tfgyt/OOSE.pdf",
			"https://dl.dropbox.com/s/6pt4ugs4s2w1wyq/VLSI.pdf"
	};
	
	public String[] ppr7 = { "https://dl.dropbox.com/s/pb5r1hsubxeht96/ACA.pdf",
			"https://dl.dropbox.com/s/orq76z8xu6cei10/ACN.pdf",
			"https://dl.dropbox.com/s/hah8ltm05ajbgbr/CC.pdf",
			"https://dl.dropbox.com/s/qo4y080t32f2pix/DIP.pdf",
			"https://dl.dropbox.com/s/wsd3bf742wfyvkf/ElectricalDrive.pdf",
			"https://dl.dropbox.com/s/x2j7mtbvqy7ohaf/ElectricalEnergy.pdf",
			"https://dl.dropbox.com/s/ykebrdh2nrozma6/MC.pdf",
			"https://dl.dropbox.com/s/lyq1kwnv1y9kes4/MP2.pdf",
			"https://dl.dropbox.com/s/xyr8bsry75kymvj/RET.pdf"	
	};
	
	public String[] ppr8 = { "https://dl.dropbox.com/s/62of2y51ynmf2y9/ACS.pdf",
			"https://dl.dropbox.com/s/gwn9xdsim4trmt5/ACTransmission.pdf",
			"https://dl.dropbox.com/s/zqlzck8ll2xgofj/AI.pdf",
			"https://dl.dropbox.com/s/p8146c3j89nspc1/ConsumerElectronics.pdf",
			"https://dl.dropbox.com/s/mn78nihzeiinapt/ECom.pdf",
			"https://dl.dropbox.com/s/1fmm8n4epxqscal/Embedded.pdf",
			"https://dl.dropbox.com/s/vszjz6r3tit44jd/Instrumentation.pdf",
			"https://dl.dropbox.com/s/ya99l0l6rj8dspx/MC8sem.pdf",
			"https://dl.dropbox.com/s/uhx1ndaiwxuwtoa/NS.pdf",
			"https://dl.dropbox.com/s/vvbsf8puzov3lwb/POM.pdf",
			"https://dl.dropbox.com/s/6lws8u30harjg8a/ST.pdf"
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.papers);
		getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);

        thisActivity = this;
        downloaderThread = null;
        progressDialog = null;

		list = (ListView) findViewById(R.id.listView1);

		String[] Sem1 = {"Engineering Chemistry", "Communication Skills-I",
				"Introduction to Computers and AutoCAD", "Applied Mathematics-I", 
				"Manufacturing Process", "Applied Physics-I",
			  };

		String[] Sem2 = { "Environmental Studies", "Communication Skills-II",
				"Engineering Mechanics","Electrical Science",
				"Introduction to Programming", "Applied Mathematics-II", 
				"Applied Physics-II",
			};
		
		String[] Sem3 = { "Analog Electronics", "Circuits and System",
				"Data Structures","Electrical Engineering Materials",
				"Electro Mechanical Energy Conversion 1", "Foundation of Computer System",
				"Applied Mathematics-III","Object Oriented Programming using C++",
				"Signal and System"
			};
		
		String[] Sem4 = { "Algorithm Analysis & Design", "Communication System",
				"Computer Graphics", "Control Engineering-I",
				"Digital Circuits & Systems-I",	"Operating System",
				"Software Engineering", "Analog Electronics-II",
			 };
		
		String[] Sem5 = { "Computer Architecture", "Control Enginerring-II",
				"Communication Systems & Circuit-II ", "Database Management System",
				"Digital Communication-I", "Digital Circuits & System-II", 
				"JAVA Programming And Website Design",
				"Linux and X-windows Programming", "Organisational Behaviour",
			};
		
		String[] Sem6 = { "Computer Networks", "Digital Communication-II",
				"Digital Signal Processing", "Data Warehousing and Data Mining",
				"Microwave Engineering", "Microprocessor" ,"Multimedia Technologies",
				"Object Oriented Software Engineering","VLSI Design"
			};
		
		String[] Sem7 = { "Advance Computer Architecture", "Advanced Computer Network",
				"Compiler Construction", "Digital Image Processing",
				"Electrical Drives", "Electrical Energy",
				"Mobile Computing", "Microprocessor System-II", 
				"Requirements & Estimation Techniques"
			};
		
		String[] Sem8 = { "Advance Control System","Flexible AC transmission Systems",
				"Artificial Intelligence", "Consumer Electronics",
				"E-Commerce & ERP","Embedded System",
				"Instrumentation","Mobile Communication",
				"Network Security","Power Quality Management","Software Testing"
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
					downloaderThread = new Downloader_Papers(thisActivity, ppr1[0]);
					downloaderThread.start();
					
					break;
				case 1:
					downloaderThread = new Downloader_Papers(thisActivity, ppr1[1]);
					downloaderThread.start();
					break;
				case 2:
					downloaderThread = new Downloader_Papers(thisActivity, ppr1[2]);
					downloaderThread.start();
					break;
				case 3:
					downloaderThread = new Downloader_Papers(thisActivity, ppr1[3]);
					downloaderThread.start();
					break;
				case 4:
					downloaderThread = new Downloader_Papers(thisActivity, ppr1[4]);
					downloaderThread.start();
				
					break;
				case 5:
					downloaderThread = new Downloader_Papers(thisActivity, ppr1[5]);
					downloaderThread.start();
				
					break;
				default:
					break;
				}
			}
		});
	}

if(Cor_Flag==2)
{
	list.setAdapter(new ArrayAdapter<String>(this,
			android.R.layout.simple_list_item_1, Sem2));
	list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			switch (arg2) {
			case 0:
				downloaderThread = new Downloader_Papers(thisActivity, ppr2[0]);
				downloaderThread.start();
				break;
			case 1:
				downloaderThread = new Downloader_Papers(thisActivity, ppr2[1]);
				downloaderThread.start();
				break;
			case 2:
				downloaderThread = new Downloader_Papers(thisActivity, ppr2[2]);
				downloaderThread.start();
				break;
			case 3:
				downloaderThread = new Downloader_Papers(thisActivity, ppr2[3]);
				downloaderThread.start();
				break;
			case 4:
				downloaderThread = new Downloader_Papers(thisActivity, ppr2[4]);
				downloaderThread.start();
			
				break;
			case 5:
				downloaderThread = new Downloader_Papers(thisActivity, ppr2[5]);
				downloaderThread.start();
			
				break;
			case 6:
				downloaderThread = new Downloader_Papers(thisActivity, ppr2[6]);
				downloaderThread.start();
			
				break;
	
			default:
				break;
			}
		}
	});
}

if(Cor_Flag==3)
{
	list.setAdapter(new ArrayAdapter<String>(this,
			android.R.layout.simple_list_item_1, Sem3));
	list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			switch (arg2) {
			case 0:
				downloaderThread = new Downloader_Papers(thisActivity, ppr3[0]);
				downloaderThread.start();
				
				break;
			case 1:
				downloaderThread = new Downloader_Papers(thisActivity, ppr3[1]);
				downloaderThread.start();
				break;
			case 2:
				downloaderThread = new Downloader_Papers(thisActivity, ppr3[2]);
				downloaderThread.start();
				break;
			case 3:
				downloaderThread = new Downloader_Papers(thisActivity, ppr3[3]);
				downloaderThread.start();
				break;
			case 4:
				downloaderThread = new Downloader_Papers(thisActivity, ppr3[4]);
				downloaderThread.start();
			
				break;
			case 5:
				downloaderThread = new Downloader_Papers(thisActivity, ppr3[5]);
				downloaderThread.start();
			
				break;
			case 6:
				downloaderThread = new Downloader_Papers(thisActivity, ppr3[6]);
				downloaderThread.start();
			
				break;
			case 7:
				downloaderThread = new Downloader_Papers(thisActivity, ppr3[7]);
				downloaderThread.start();
			
				break;
			case 8:
				downloaderThread = new Downloader_Papers(thisActivity, ppr3[8]);
				downloaderThread.start();
			
				break;
	
			default:
				break;
			}
		}
	});
}
if(Cor_Flag==4)
{
	list.setAdapter(new ArrayAdapter<String>(this,
			android.R.layout.simple_list_item_1, Sem4));
	list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			switch (arg2) {
			case 0:
				downloaderThread = new Downloader_Papers(thisActivity, ppr4[0]);
				downloaderThread.start();
				break;
			case 1:
				downloaderThread = new Downloader_Papers(thisActivity, ppr4[1]);
				downloaderThread.start();
				break;
			case 2:
				downloaderThread = new Downloader_Papers(thisActivity, ppr4[2]);
				downloaderThread.start();
				break;
			case 3:
				downloaderThread = new Downloader_Papers(thisActivity, ppr4[3]);
				downloaderThread.start();
				break;
			case 4:
				downloaderThread = new Downloader_Papers(thisActivity, ppr4[4]);
				downloaderThread.start();
			
				break;
			case 5:
				downloaderThread = new Downloader_Papers(thisActivity, ppr4[5]);
				downloaderThread.start();
			
				break;
			case 6:
				downloaderThread = new Downloader_Papers(thisActivity, ppr4[6]);
				downloaderThread.start();
			
				break;
	
			default:
				break;
			}
		}
	});
}
if(Cor_Flag==5)
{
	list.setAdapter(new ArrayAdapter<String>(this,
			android.R.layout.simple_list_item_1, Sem5));
	list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			switch (arg2) {
			case 0:
				downloaderThread = new Downloader_Papers(thisActivity, ppr5[0]);
				downloaderThread.start();
				break;
			case 1:
				downloaderThread = new Downloader_Papers(thisActivity, ppr5[1]);
				downloaderThread.start();
				break;
			case 2:
				downloaderThread = new Downloader_Papers(thisActivity, ppr5[2]);
				downloaderThread.start();
				break;
			case 3:
				downloaderThread = new Downloader_Papers(thisActivity, ppr5[3]);
				downloaderThread.start();
				break;
			case 4:
				downloaderThread = new Downloader_Papers(thisActivity, ppr5[4]);
				downloaderThread.start();
			
				break;
			case 5:
				downloaderThread = new Downloader_Papers(thisActivity, ppr5[5]);
				downloaderThread.start();
			
				break;
			case 6:
				downloaderThread = new Downloader_Papers(thisActivity, ppr5[6]);
				downloaderThread.start();
			
				break;
			case 7:
				downloaderThread = new Downloader_Papers(thisActivity, ppr5[7]);
				downloaderThread.start();
			
				break;
			case 8:
				downloaderThread = new Downloader_Papers(thisActivity, ppr5[8]);
				downloaderThread.start();
			
				break;
	
			default:
				break;
			}
		}
	});
}
if(Cor_Flag==6)
{
	list.setAdapter(new ArrayAdapter<String>(this,
			android.R.layout.simple_list_item_1, Sem6));
	list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			switch (arg2) {
			case 0:
				downloaderThread = new Downloader_Papers(thisActivity, ppr6[0]);
				downloaderThread.start();
				break;
			case 1:
				downloaderThread = new Downloader_Papers(thisActivity, ppr6[1]);
				downloaderThread.start();
				break;
			case 2:
				downloaderThread = new Downloader_Papers(thisActivity, ppr6[2]);
				downloaderThread.start();
				break;
			case 3:
				downloaderThread = new Downloader_Papers(thisActivity, ppr6[3]);
				downloaderThread.start();
				break;
			case 4:
				downloaderThread = new Downloader_Papers(thisActivity, ppr6[4]);
				downloaderThread.start();
			
				break;
			case 5:
				downloaderThread = new Downloader_Papers(thisActivity, ppr6[5]);
				downloaderThread.start();
			
				break;
			case 6:
				downloaderThread = new Downloader_Papers(thisActivity, ppr6[6]);
				downloaderThread.start();
			
				break;
			case 7:
				downloaderThread = new Downloader_Papers(thisActivity, ppr6[7]);
				downloaderThread.start();
			
				break;
			case 8:
				downloaderThread = new Downloader_Papers(thisActivity, ppr6[8]);
				downloaderThread.start();
			
				break;
	
			default:
				break;
			}
		}
	});
}
if(Cor_Flag==7)
{
	list.setAdapter(new ArrayAdapter<String>(this,
			android.R.layout.simple_list_item_1, Sem7));
	list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			switch (arg2) {
			case 0:
				downloaderThread = new Downloader_Papers(thisActivity, ppr7[0]);
				downloaderThread.start();
				break;
			case 1:
				downloaderThread = new Downloader_Papers(thisActivity, ppr7[1]);
				downloaderThread.start();
				break;
			case 2:
				downloaderThread = new Downloader_Papers(thisActivity, ppr7[2]);
				downloaderThread.start();
				break;
			case 3:
				downloaderThread = new Downloader_Papers(thisActivity, ppr7[3]);
				downloaderThread.start();
				break;
			case 4:
				downloaderThread = new Downloader_Papers(thisActivity, ppr7[4]);
				downloaderThread.start();
			
				break;
			case 5:
				downloaderThread = new Downloader_Papers(thisActivity, ppr7[5]);
				downloaderThread.start();
			
				break;
			case 6:
				downloaderThread = new Downloader_Papers(thisActivity, ppr7[6]);
				downloaderThread.start();
			
				break;
			case 7:
				downloaderThread = new Downloader_Papers(thisActivity, ppr7[7]);
				downloaderThread.start();
			
				break;
			case 8:
				downloaderThread = new Downloader_Papers(thisActivity, ppr7[8]);
				downloaderThread.start();
			
				break;	
			default:
				break;
			}
		}
	});
}
if(Cor_Flag==8)
{
	list.setAdapter(new ArrayAdapter<String>(this,
			android.R.layout.simple_list_item_1, Sem8));
	list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			switch (arg2) {
			case 0:
				downloaderThread = new Downloader_Papers(thisActivity, ppr8[0]);
				downloaderThread.start();
				break;
			case 1:
				downloaderThread = new Downloader_Papers(thisActivity, ppr8[1]);
				downloaderThread.start();
				break;
			case 2:
				downloaderThread = new Downloader_Papers(thisActivity, ppr8[2]);
				downloaderThread.start();
				break;
			case 3:
				downloaderThread = new Downloader_Papers(thisActivity, ppr8[3]);
				downloaderThread.start();
				break;
			case 4:
				downloaderThread = new Downloader_Papers(thisActivity, ppr8[4]);
				downloaderThread.start();
			
				break;
			case 5:
				downloaderThread = new Downloader_Papers(thisActivity, ppr8[5]);
				downloaderThread.start();
			
				break;
			case 6:
				downloaderThread = new Downloader_Papers(thisActivity, ppr8[6]);
				downloaderThread.start();
			
				break;
			case 7:
				downloaderThread = new Downloader_Papers(thisActivity, ppr8[7]);
				downloaderThread.start();
			
				break;
			case 8:
				downloaderThread = new Downloader_Papers(thisActivity, ppr8[8]);
				downloaderThread.start();
			
				break;	
			case 9:
				downloaderThread = new Downloader_Papers(thisActivity, ppr8[9]);
				downloaderThread.start();
			
				break;	
			case 10:
				downloaderThread = new Downloader_Papers(thisActivity, ppr8[10]);
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