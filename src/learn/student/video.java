package learn.student;

import java.util.ArrayList;

import eHandy.gtbit.R;




import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.AdapterView.OnItemClickListener;

public class video extends Activity{
	 	private ImageAdapter_vid imgAdapter;
	    private ArrayList<String> desc;
	    private ArrayList<Integer> image;
	    private GridView gridView_l;
	    public static final int MESSAGE_DOWNLOAD_STARTED = 1000;
		public static final int MESSAGE_DOWNLOAD_COMPLETE = 1001;
		public static final int MESSAGE_UPDATE_PROGRESS_BAR = 1002;
		public static final int MESSAGE_DOWNLOAD_CANCELED = 1003;
		public static final int MESSAGE_CONNECTING_STARTED = 1004;
		public static final int MESSAGE_ENCOUNTERED_ERROR = 1005;
		VideoView view;

		
		public String[] str_array = {
				"https://dl.dropbox.com/s/2sdown0wd6lla4n/C_Tutorial.mp4",
				"https://dl.dropbox.com/s/yirnyv3apt90jop/C%23Tutorial.mp4",
				"https://dl.dropbox.com/s/llnfr14k5y9jwry/C%2B%2BTutorial.mp4",
				"https://dl.dropbox.com/s/t8fq9j0x13xccjj/Java_Tutorial.mp4",
				"https://dl.dropbox.com/s/sbqxxpuvb43kpix/SQL_Tutorial.mp4",
				"https://dl.dropbox.com/s/9vjjltg4caaxlgn/Shell_Programming_Tutorial.mp4",
				"https://dl.dropbox.com/s/vb5sk6vkml3tzjf/VHDL_Tutorial.mp4",
				"https://dl.dropbox.com/s/jty2ddxsd664sfw/8085Tutorial.mp4",
				"https://dl.dropbox.com/s/j4ke9f190wvtts3/Maths.mp4",
				"https://dl.dropbox.com/s/j64o1hlxh64ihgs/Data_Structures_and_Algorithms.mp4",
				"https://dl.dropbox.com/s/krzwse8ntnbbrdc/AI-%20Courses.mp4",
				"https://dl.dropbox.com/s/62tcnufakxsxyac/communicationEngineering.mp4",
				"https://dl.dropbox.com/s/4ag7trtjj3lf6xk/Compiler_Design.mp4",
				"https://dl.dropbox.com/s/2hggipo9n5fpi90/CompterArchitecture.mp4",
				"https://dl.dropbox.com/s/hb8xthd5e68vzu6/controlEngineeringTutorial.mp4",
				"https://dl.dropbox.com/s/aw7z22wva3ot9qu/DigitalCircuit%26System.mp4",
				"https://dl.dropbox.com/s/i6692v6exo58avc/DigitalSignalProcessing.mp4",
				"https://dl.dropbox.com/s/tyhgcohk0ycs2qq/Graphics.mp4",
				"https://dl.dropbox.com/s/b59ye0o25gzw22e/Signals_and_Systems.mp4",
				"https://dl.dropbox.com/s/xat0e17akqs77lx/SoftwareEngineering.mp4"};
		
		public String[] str_array_3gp = {
				"https://dl.dropbox.com/s/29d63b01ka9k2kk/C_Tutorial.3gp",
				"https://dl.dropbox.com/s/9ffk2ofjtpxgwsr/C%23Tutorial.3gp",
				"https://dl.dropbox.com/s/04hsytrqzrqqpe2/C%2B%2BTutorial.3gp",
				"https://dl.dropbox.com/s/h00s89vg79en7ei/Java_Tutorial.3gp",
				"https://dl.dropbox.com/s/71xapgiud3jia2i/SQL_Tutorial.3gp",
				"https://dl.dropbox.com/s/lcib7c2j7jkhnnm/Shell_Programming_Tutorial.3gp",
				"https://dl.dropbox.com/s/mpsosp9eq4kwhky/VHDL_Tutorial.3gp",
				"https://dl.dropbox.com/s/dya40wc5giiqarh/8085Tutorial.3gp",
				"https://dl.dropbox.com/s/56549n62sglexv7/Maths.3gp",
				"https://dl.dropbox.com/s/wj8cykppcuicicc/Data_Structures_and_Algorithms.3gp",
				"https://dl.dropbox.com/s/2plrkrgkf5ewicm/AI-%20Courses.3gp",
				"https://dl.dropbox.com/s/5p6tfqyro4tad2t/communicationEngineering.3gp",
				"https://dl.dropbox.com/s/4jgylfp7c7lmg55/Compiler%20Design.3gp",
				"https://dl.dropbox.com/s/h22ytfnmghnx7ai/CompterArchitecture.3gp",
				"https://dl.dropbox.com/s/0lpvl4bzlravz4k/controlEngineeringTutorial.3gp",
				"https://dl.dropbox.com/s/nyqnpgxu2aeifxi/DigitalCircuit%26System.3gp",
				"https://dl.dropbox.com/s/8jmg28v9sgbg37a/DigitalSignalProcessing.3gp",
				"https://dl.dropbox.com/s/dad8z7vv9rugscn/Graphics.3gp",
				"https://dl.dropbox.com/s/h73elqr5sfg23xi/Signals_and_Systems.3gp",
				"https://dl.dropbox.com/s/aqfdhf9n82a1ur1/SoftwareEngineering.3gp"};
	
		private video thisActivity;
		private Thread downloaderThread;
		private ProgressDialog progressDialog;
	
	   
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        thisActivity = this;
	        downloaderThread = null;
	        progressDialog = null;
	        setContentView(R.layout.video_grid);
			getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);
			prepareList1();

	        
	        imgAdapter = new ImageAdapter_vid(this,desc, image);
	        gridView_l = (GridView) findViewById(R.id.gridView_vid);
	        gridView_l.setAdapter(imgAdapter);
	        gridView_l.setOnItemClickListener(new OnItemClickListener()
	        {
	            
	            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
	                    long arg3) {
	            	
	            	AlertDialog.Builder builder=new AlertDialog.Builder(video.this);
         		   	builder.setIcon(R.drawable.video_icon);
         		   	builder.setTitle("Video");
                    builder.setMessage("Play or Download?");
	              
                   if(position == 0){
	                	
                	   builder.setPositiveButton("Play Online",new DialogInterface.OnClickListener() {
                		   public void onClick(DialogInterface dialog, int which) {
	                   		
                			String videoId = "fur0pzOOv0A";
	                   		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId)); 
	                   		intent.putExtra("VIDEO_ID", videoId); 
	                   		startActivity(intent);
                		     
                			  
	                   		}
	                   	  });
	                     
	                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
	                	   public void onClick(DialogInterface dialog, int which) {
	                		   
	                		   final CharSequence[] items = {"480p", "176p"};
	                		   AlertDialog.Builder builder = new AlertDialog.Builder(video.this);
	                		   builder.setTitle("Download Quality");
	                		   builder.setIcon(R.drawable.download);
	                		   builder.setItems(items, new DialogInterface.OnClickListener() {
	                			   public void onClick(DialogInterface dialog, int item) {
	                				   
	                				   if(item == 0)
	                				   {
	                					   downloaderThread = new DownloaderThread_video(thisActivity, str_array[0]);
	                					   downloaderThread.start();
	                				   }
		                   	    	   if(item == 1)
	                   	    			{
		                   	    		   downloaderThread = new DownloaderThread_video(thisActivity, str_array_3gp[0]);
		                   	    		   downloaderThread.start();
	                   	    			}
	                			   }
	                   	  });
	                   	  
	                		   AlertDialog alert = builder.create();
	                		   alert.show();
	                   	 		}
	                   	  });
		           AlertDialog alertdialog=builder.create();
	               alertdialog.show();
	              }
                   
	                if(position == 1){
	                	
	                		builder.setPositiveButton("Play Online",new DialogInterface.OnClickListener() {
	                		   public void onClick(DialogInterface dialog, int which) {
		                   		
	                			String videoId = "dDawwA2gYgU";
		                   		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId)); 
		                   		intent.putExtra("VIDEO_ID", videoId); 
		                   		startActivity(intent); 
		                   		}
		                   	  });
		                     
		                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
		                	   public void onClick(DialogInterface dialog, int which) {
		                		   
		                		   final CharSequence[] items = {"480p", "176p"};
		                		   AlertDialog.Builder builder = new AlertDialog.Builder(video.this);
		                		   builder.setTitle("Download Quality");
		                		   builder.setIcon(R.drawable.download);
		                		   builder.setItems(items, new DialogInterface.OnClickListener() {
		                			   public void onClick(DialogInterface dialog, int item) {
		                				   
		                				   if(item == 0)
		                				   {
		                					   	downloaderThread = new DownloaderThread_video(thisActivity, str_array[1]);
		               	    				  	downloaderThread.start();
		                				   }
			                   	    	   if(item == 1)
		                   	    			{
			                   	    		   downloaderThread = new DownloaderThread_video(thisActivity, str_array_3gp[1]);
			                   	    		   downloaderThread.start();
		                   	    			}
		                			   }
		                   	  });
		                   	  
		                		   AlertDialog alert = builder.create();
		                		   alert.show();
		                   	 		}
		                   	  });
			           AlertDialog alertdialog=builder.create();
		               alertdialog.show();
	                	
	               
	                }
	                if(position == 2){
	                	builder.setPositiveButton("Play Online",new DialogInterface.OnClickListener() {
	                		   public void onClick(DialogInterface dialog, int which) {
		                   		
	                			String videoId = "BB2qJrpMWVg";
		                   		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId)); 
		                   		intent.putExtra("VIDEO_ID", videoId); 
		                   		startActivity(intent); 
		                   		}
		                   	  });
		                     
		                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
		                	   public void onClick(DialogInterface dialog, int which) {
		                		   
		                		   final CharSequence[] items = {"480p", "176p"};
		                		   AlertDialog.Builder builder = new AlertDialog.Builder(video.this);
		                		   builder.setTitle("Download Quality");
		                		   builder.setIcon(R.drawable.download);
		                		   builder.setItems(items, new DialogInterface.OnClickListener() {
		                			   public void onClick(DialogInterface dialog, int item) {
		                				   
		                				   if(item == 0)
		                				   {
		                					   	downloaderThread = new DownloaderThread_video(thisActivity, str_array[2]);
		               	    					downloaderThread.start();
		                				   }
			                   	    	   if(item == 1)
		                   	    			{
			                   	    		   downloaderThread = new DownloaderThread_video(thisActivity, str_array_3gp[2]);
			                   	    		   downloaderThread.start();
		                   	    			}
		                			   }
		                   	  });
		                   	  
		                		   AlertDialog alert = builder.create();
		                		   alert.show();
		                   	 		}
		                   	  });
			           AlertDialog alertdialog=builder.create();
		               alertdialog.show();
	                	
	                }   
	                if(position == 3){
	                	builder.setPositiveButton("Play Online",new DialogInterface.OnClickListener() {
	                		   public void onClick(DialogInterface dialog, int which) {
		                   		
	                			String videoId = "61HAhD1yP54";
		                   		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId)); 
		                   		intent.putExtra("VIDEO_ID", videoId); 
		                   		startActivity(intent); 
		                   		}
		                   	  });
		                     
		                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
		                	   public void onClick(DialogInterface dialog, int which) {
		                		   
		                		   final CharSequence[] items = {"480p", "176p"};
		                		   AlertDialog.Builder builder = new AlertDialog.Builder(video.this);
		                		   builder.setTitle("Download Quality");
		                		   builder.setIcon(R.drawable.download);
		                		   builder.setItems(items, new DialogInterface.OnClickListener() {
		                			   public void onClick(DialogInterface dialog, int item) {
		                				   
		                				   if(item == 0)
		                				   {
		                					   	downloaderThread = new DownloaderThread_video(thisActivity, str_array[3]);
		                					   	downloaderThread.start();
		                				   }
			                   	    	   if(item == 1)
		                   	    			{
			                   	    		   downloaderThread = new DownloaderThread_video(thisActivity, str_array_3gp[3]);
			                   	    		   downloaderThread.start();
		                   	    			}
		                			   }
		                   	  });
		                   	  
		                		   AlertDialog alert = builder.create();
		                		   alert.show();
		                   	 		}
		                   	  });
			           AlertDialog alertdialog=builder.create();
		               alertdialog.show();
	                	
	                }   
	                if(position == 4){
	                	builder.setPositiveButton("Play Online",new DialogInterface.OnClickListener() {
	                		   public void onClick(DialogInterface dialog, int which) {
		                   		
	                			String videoId = "X8AwYHBUUEk";
		                   		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId)); 
		                   		intent.putExtra("VIDEO_ID", videoId); 
		                   		startActivity(intent); 
		                   		}
		                   	  });
		                     
		                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
		                	   public void onClick(DialogInterface dialog, int which) {
		                		   
		                		   final CharSequence[] items = {"480p", "176p"};
		                		   AlertDialog.Builder builder = new AlertDialog.Builder(video.this);
		                		   builder.setTitle("Download Quality");
		                		   builder.setIcon(R.drawable.download);
		                		   builder.setItems(items, new DialogInterface.OnClickListener() {
		                			   public void onClick(DialogInterface dialog, int item) {
		                				   
		                				   if(item == 0)
		                				   {
		                					    downloaderThread = new DownloaderThread_video(thisActivity, str_array[4]);
		               	    					downloaderThread.start();
		                				   }
			                   	    	   if(item == 1)
		                   	    			{
			                   	    		   downloaderThread = new DownloaderThread_video(thisActivity, str_array_3gp[4]);
			                   	    		   downloaderThread.start();
		                   	    			}
		                			   }
		                   	  });
		                   	  
		                		   AlertDialog alert = builder.create();
		                		   alert.show();
		                   	 		}
		                   	  });
			           AlertDialog alertdialog=builder.create();
		               alertdialog.show();
	                	
	                }   
	                if(position == 5){
	                	builder.setPositiveButton("Play Online",new DialogInterface.OnClickListener() {
	                		   public void onClick(DialogInterface dialog, int which) {
		                   		
	                			String videoId = "c19udFOgHlg";
		                   		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId)); 
		                   		intent.putExtra("VIDEO_ID", videoId); 
		                   		startActivity(intent); 
		                   		}
		                   	  });
		                     
		                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
		                	   public void onClick(DialogInterface dialog, int which) {
		                		   
		                		   final CharSequence[] items = {"480p", "176p"};
		                		   AlertDialog.Builder builder = new AlertDialog.Builder(video.this);
		                		   builder.setTitle("Download Quality");
		                		   builder.setIcon(R.drawable.download);
		                		   builder.setItems(items, new DialogInterface.OnClickListener() {
		                			   public void onClick(DialogInterface dialog, int item) {
		                				   
		                				   if(item == 0)
		                				   {
		                						downloaderThread = new DownloaderThread_video(thisActivity, str_array[5]);
		                						downloaderThread.start();
		                				   }
			                   	    	   if(item == 1)
		                   	    			{
			                   	    		   downloaderThread = new DownloaderThread_video(thisActivity, str_array_3gp[5]);
			                   	    		   downloaderThread.start();
		                   	    			}
		                			   }
		                   	  });
		                   	  
		                		   AlertDialog alert = builder.create();
		                		   alert.show();
		                   	 		}
		                   	  });
			           AlertDialog alertdialog=builder.create();
		               alertdialog.show();
	                
	                }   
	                if(position == 6){
	                	builder.setPositiveButton("Play Online",new DialogInterface.OnClickListener() {
	                		   public void onClick(DialogInterface dialog, int which) {
		                   		
	                			String videoId = "6o493R5tnzg";
		                   		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId)); 
		                   		intent.putExtra("VIDEO_ID", videoId); 
		                   		startActivity(intent); 
		                   		}
		                   	  });
		                     
		                 builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
		                	   public void onClick(DialogInterface dialog, int which) {
		                		   
		                		   final CharSequence[] items = {"480p", "176p"};
		                		   AlertDialog.Builder builder = new AlertDialog.Builder(video.this);
		                		   builder.setTitle("Download Quality");
		                		   builder.setIcon(R.drawable.download);
		                		   builder.setItems(items, new DialogInterface.OnClickListener() {
		                			   public void onClick(DialogInterface dialog, int item) {
		                				   
		                				   if(item == 0)
		                				   {
		                					   	downloaderThread = new DownloaderThread_video(thisActivity, str_array[6]);
		               	    				 	downloaderThread.start();
		                				   }
			                   	    	   if(item == 1)
		                   	    			{
			                   	    		   downloaderThread = new DownloaderThread_video(thisActivity, str_array_3gp[6]);
			                   	    		   downloaderThread.start();
		                   	    			}
		                			   }
		                   	  });
		                   	  
		                		   AlertDialog alert = builder.create();
		                		   alert.show();
		                   	 		}
		                   	  });
			           AlertDialog alertdialog=builder.create();
		               alertdialog.show();
	                	
	                }
	                if(position == 7){
	                	builder.setPositiveButton("Play Online",new DialogInterface.OnClickListener() {
	                		   public void onClick(DialogInterface dialog, int which) {
		                   		
	                			String videoId = "rV3sIY2SzHU";
		                   		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId)); 
		                   		intent.putExtra("VIDEO_ID", videoId); 
		                   		startActivity(intent); 
		                   		}
		                   	  });
		                     
		                   builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
		                	   public void onClick(DialogInterface dialog, int which) {
		                		   
		                		   final CharSequence[] items = {"480p", "176p"};
		                		   AlertDialog.Builder builder = new AlertDialog.Builder(video.this);
		                		   builder.setTitle("Download Quality");
		                		   builder.setIcon(R.drawable.download);
		                		   builder.setItems(items, new DialogInterface.OnClickListener() {
		                			   public void onClick(DialogInterface dialog, int item) {
		                				   
		                				   if(item == 0)
		                				   {
		                					    downloaderThread = new DownloaderThread_video(thisActivity, str_array[7]);
		               	    					downloaderThread.start();
		                				   }
			                   	    	   if(item == 1)
		                   	    			{
			                   	    		   downloaderThread = new DownloaderThread_video(thisActivity, str_array_3gp[7]);
			                   	    		   downloaderThread.start();
		                   	    			}
		                			   }
		                   	  });
		                   	  
		                		   AlertDialog alert = builder.create();
		                		   alert.show();
		                   	 		}
		                   	  });
			           AlertDialog alertdialog=builder.create();
		               alertdialog.show();
	                	
	                }
	                if(position == 8){
	                	builder.setPositiveButton("Play Online",new DialogInterface.OnClickListener() {
	                		   public void onClick(DialogInterface dialog, int which) {
		                   		
	                			String videoId = "P156Lw6KVSo";
		                   		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId)); 
		                   		intent.putExtra("VIDEO_ID", videoId); 
		                   		startActivity(intent); 
		                   		}
		                   	  });
		                     
		                 builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
		                	   public void onClick(DialogInterface dialog, int which) {
		                		   
		                		   final CharSequence[] items = {"480p", "176p"};
		                		   AlertDialog.Builder builder = new AlertDialog.Builder(video.this);
		                		   builder.setTitle("Download Quality");
		                		   builder.setIcon(R.drawable.download);
		                		   builder.setItems(items, new DialogInterface.OnClickListener() {
		                			   public void onClick(DialogInterface dialog, int item) {
		                				   
		                				   if(item == 0)
		                				   {
		                					   	downloaderThread = new DownloaderThread_video(thisActivity, str_array[8]);
		               	    				 	downloaderThread.start();
		                				   }
			                   	    	   if(item == 1)
		                   	    			{
			                   	    		   downloaderThread = new DownloaderThread_video(thisActivity, str_array_3gp[8]);
			                   	    		   downloaderThread.start();
		                   	    			}
		                			   }
		                   	  });
		                   	  
		                		   AlertDialog alert = builder.create();
		                		   alert.show();
		                   	 		}
		                   	  });
			           AlertDialog alertdialog=builder.create();
		               alertdialog.show();
	                	
	                }
	                if(position == 9){
	                	builder.setPositiveButton("Play Online",new DialogInterface.OnClickListener() {
	                		   public void onClick(DialogInterface dialog, int which) {
		                   		
	                			String videoId = "GO5_z6JFKqU";
		                   		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId)); 
		                   		intent.putExtra("VIDEO_ID", videoId); 
		                   		startActivity(intent); 
		                   		}
		                   	  });
		                     
		                 builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
		                	   public void onClick(DialogInterface dialog, int which) {
		                		   
		                		   final CharSequence[] items = {"480p", "176p"};
		                		   AlertDialog.Builder builder = new AlertDialog.Builder(video.this);
		                		   builder.setTitle("Download Quality");
		                		   builder.setIcon(R.drawable.download);
		                		   builder.setItems(items, new DialogInterface.OnClickListener() {
		                			   public void onClick(DialogInterface dialog, int item) {
		                				   
		                				   if(item == 0)
		                				   {
		                					   	downloaderThread = new DownloaderThread_video(thisActivity, str_array[9]);
		               	    				 	downloaderThread.start();
		                				   }
			                   	    	   if(item == 1)
		                   	    			{
			                   	    		   downloaderThread = new DownloaderThread_video(thisActivity, str_array_3gp[9]);
			                   	    		   downloaderThread.start();
		                   	    			}
		                			   }
		                   	  });
		                   	  
		                		   AlertDialog alert = builder.create();
		                		   alert.show();
		                   	 		}
		                   	  });
			           AlertDialog alertdialog=builder.create();
		               alertdialog.show();
	                	
	                }
	                if(position == 10){
	                	builder.setPositiveButton("Play Online",new DialogInterface.OnClickListener() {
	                		   public void onClick(DialogInterface dialog, int which) {
		                   		
	                			String videoId = "0IcJGA_9Gf0";
		                   		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId)); 
		                   		intent.putExtra("VIDEO_ID", videoId); 
		                   		startActivity(intent); 
		                   		}
		                   	  });
		                     
		                 builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
		                	   public void onClick(DialogInterface dialog, int which) {
		                		   
		                		   final CharSequence[] items = {"480p", "176p"};
		                		   AlertDialog.Builder builder = new AlertDialog.Builder(video.this);
		                		   builder.setTitle("Download Quality");
		                		   builder.setIcon(R.drawable.download);
		                		   builder.setItems(items, new DialogInterface.OnClickListener() {
		                			   public void onClick(DialogInterface dialog, int item) {
		                				   
		                				   if(item == 0)
		                				   {
		                					   	downloaderThread = new DownloaderThread_video(thisActivity, str_array[10]);
		               	    				 	downloaderThread.start();
		                				   }
			                   	    	   if(item == 1)
		                   	    			{
			                   	    		   downloaderThread = new DownloaderThread_video(thisActivity, str_array_3gp[10]);
			                   	    		   downloaderThread.start();
		                   	    			}
		                			   }
		                   	  });
		                   	  
		                		   AlertDialog alert = builder.create();
		                		   alert.show();
		                   	 		}
		                   	  });
			           AlertDialog alertdialog=builder.create();
		               alertdialog.show();
	                	
	                }
	                if(position == 11){
	                	builder.setPositiveButton("Play Online",new DialogInterface.OnClickListener() {
	                		   public void onClick(DialogInterface dialog, int which) {
		                   		
	                			String videoId = "VTa9h0g9xeI";
		                   		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId)); 
		                   		intent.putExtra("VIDEO_ID", videoId); 
		                   		startActivity(intent); 
		                   		}
		                   	  });
		                     
		                 builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
		                	   public void onClick(DialogInterface dialog, int which) {
		                		   
		                		   final CharSequence[] items = {"480p", "176p"};
		                		   AlertDialog.Builder builder = new AlertDialog.Builder(video.this);
		                		   builder.setTitle("Download Quality");
		                		   builder.setIcon(R.drawable.download);
		                		   builder.setItems(items, new DialogInterface.OnClickListener() {
		                			   public void onClick(DialogInterface dialog, int item) {
		                				   
		                				   if(item == 0)
		                				   {
		                					   	downloaderThread = new DownloaderThread_video(thisActivity, str_array[11]);
		               	    				 	downloaderThread.start();
		                				   }
			                   	    	   if(item == 1)
		                   	    			{
			                   	    		   downloaderThread = new DownloaderThread_video(thisActivity, str_array_3gp[11]);
			                   	    		   downloaderThread.start();
		                   	    			}
		                			   }
		                   	  });
		                   	  
		                		   AlertDialog alert = builder.create();
		                		   alert.show();
		                   	 		}
		                   	  });
			           AlertDialog alertdialog=builder.create();
		               alertdialog.show();
	                	
	                }
	                if(position == 12){
	                	builder.setPositiveButton("Play Online",new DialogInterface.OnClickListener() {
	                		   public void onClick(DialogInterface dialog, int which) {
		                   		
	                			String videoId = "UIBvnbropkU";
		                   		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId)); 
		                   		intent.putExtra("VIDEO_ID", videoId); 
		                   		startActivity(intent); 
		                   		}
		                   	  });
		                     
		                 builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
		                	   public void onClick(DialogInterface dialog, int which) {
		                		   
		                		   final CharSequence[] items = {"480p", "176p"};
		                		   AlertDialog.Builder builder = new AlertDialog.Builder(video.this);
		                		   builder.setTitle("Download Quality");
		                		   builder.setIcon(R.drawable.download);
		                		   builder.setItems(items, new DialogInterface.OnClickListener() {
		                			   public void onClick(DialogInterface dialog, int item) {
		                				   
		                				   if(item == 0)
		                				   {
		                					   	downloaderThread = new DownloaderThread_video(thisActivity, str_array[12]);
		               	    				 	downloaderThread.start();
		                				   }
			                   	    	   if(item == 1)
		                   	    			{
			                   	    		   downloaderThread = new DownloaderThread_video(thisActivity, str_array_3gp[12]);
			                   	    		   downloaderThread.start();
		                   	    			}
		                			   }
		                   	  });
		                   	  
		                		   AlertDialog alert = builder.create();
		                		   alert.show();
		                   	 		}
		                   	  });
			           AlertDialog alertdialog=builder.create();
		               alertdialog.show();
	                	
	                }
	                if(position == 13){
	                	builder.setPositiveButton("Play Online",new DialogInterface.OnClickListener() {
	                		   public void onClick(DialogInterface dialog, int which) {
		                   		
	                			String videoId = "XZlsSorZUuE";
		                   		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId)); 
		                   		intent.putExtra("VIDEO_ID", videoId); 
		                   		startActivity(intent); 
		                   		}
		                   	  });
		                     
		                 builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
		                	   public void onClick(DialogInterface dialog, int which) {
		                		   
		                		   final CharSequence[] items = {"480p", "176p"};
		                		   AlertDialog.Builder builder = new AlertDialog.Builder(video.this);
		                		   builder.setTitle("Download Quality");
		                		   builder.setIcon(R.drawable.download);
		                		   builder.setItems(items, new DialogInterface.OnClickListener() {
		                			   public void onClick(DialogInterface dialog, int item) {
		                				   
		                				   if(item == 0)
		                				   {
		                					   	downloaderThread = new DownloaderThread_video(thisActivity, str_array[13]);
		               	    				 	downloaderThread.start();
		                				   }
			                   	    	   if(item == 1)
		                   	    			{
			                   	    		   downloaderThread = new DownloaderThread_video(thisActivity, str_array_3gp[13]);
			                   	    		   downloaderThread.start();
		                   	    			}
		                			   }
		                   	  });
		                   	  
		                		   AlertDialog alert = builder.create();
		                		   alert.show();
		                   	 		}
		                   	  });
			           AlertDialog alertdialog=builder.create();
		               alertdialog.show();
	                	
	                }
	                if(position == 14){
	                	builder.setPositiveButton("Play Online",new DialogInterface.OnClickListener() {
	                		   public void onClick(DialogInterface dialog, int which) {
		                   		
	                			String videoId = "Y8GaKOK56Rg";
		                   		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId)); 
		                   		intent.putExtra("VIDEO_ID", videoId); 
		                   		startActivity(intent); 
		                   		}
		                   	  });
		                     
		                 builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
		                	   public void onClick(DialogInterface dialog, int which) {
		                		   
		                		   final CharSequence[] items = {"480p", "176p"};
		                		   AlertDialog.Builder builder = new AlertDialog.Builder(video.this);
		                		   builder.setTitle("Download Quality");
		                		   builder.setIcon(R.drawable.download);
		                		   builder.setItems(items, new DialogInterface.OnClickListener() {
		                			   public void onClick(DialogInterface dialog, int item) {
		                				   
		                				   if(item == 0)
		                				   {
		                					   	downloaderThread = new DownloaderThread_video(thisActivity, str_array[14]);
		               	    				 	downloaderThread.start();
		                				   }
			                   	    	   if(item == 1)
		                   	    			{
			                   	    		   downloaderThread = new DownloaderThread_video(thisActivity, str_array_3gp[14]);
			                   	    		   downloaderThread.start();
		                   	    			}
		                			   }
		                   	  });
		                   	  
		                		   AlertDialog alert = builder.create();
		                		   alert.show();
		                   	 		}
		                   	  });
			           AlertDialog alertdialog=builder.create();
		               alertdialog.show();
	                	
	                }
	                if(position == 15){
	                	builder.setPositiveButton("Play Online",new DialogInterface.OnClickListener() {
	                		   public void onClick(DialogInterface dialog, int which) {
		                   		
	                			String videoId = "8O_2om3tEew";
		                   		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId)); 
		                   		intent.putExtra("VIDEO_ID", videoId); 
		                   		startActivity(intent); 
		                   		}
		                   	  });
		                     
		                 builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
		                	   public void onClick(DialogInterface dialog, int which) {
		                		   
		                		   final CharSequence[] items = {"480p", "176p"};
		                		   AlertDialog.Builder builder = new AlertDialog.Builder(video.this);
		                		   builder.setTitle("Download Quality");
		                		   builder.setIcon(R.drawable.download);
		                		   builder.setItems(items, new DialogInterface.OnClickListener() {
		                			   public void onClick(DialogInterface dialog, int item) {
		                				   
		                				   if(item == 0)
		                				   {
		                					   	downloaderThread = new DownloaderThread_video(thisActivity, str_array[15]);
		               	    				 	downloaderThread.start();
		                				   }
			                   	    	   if(item == 1)
		                   	    			{
			                   	    		   downloaderThread = new DownloaderThread_video(thisActivity, str_array_3gp[15]);
			                   	    		   downloaderThread.start();
		                   	    			}
		                			   }
		                   	  });
		                   	  
		                		   AlertDialog alert = builder.create();
		                		   alert.show();
		                   	 		}
		                   	  });
			           AlertDialog alertdialog=builder.create();
		               alertdialog.show();
	                	
	                }
	                if(position == 16){
	                	builder.setPositiveButton("Play Online",new DialogInterface.OnClickListener() {
	                		   public void onClick(DialogInterface dialog, int which) {
		                   		
	                			String videoId = "hP-K2HWq-uw";
		                   		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId)); 
		                   		intent.putExtra("VIDEO_ID", videoId); 
		                   		startActivity(intent); 
		                   		}
		                   	  });
		                     
		                 builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
		                	   public void onClick(DialogInterface dialog, int which) {
		                		   
		                		   final CharSequence[] items = {"480p", "176p"};
		                		   AlertDialog.Builder builder = new AlertDialog.Builder(video.this);
		                		   builder.setTitle("Download Quality");
		                		   builder.setIcon(R.drawable.download);
		                		   builder.setItems(items, new DialogInterface.OnClickListener() {
		                			   public void onClick(DialogInterface dialog, int item) {
		                				   
		                				   if(item == 0)
		                				   {
		                					   	downloaderThread = new DownloaderThread_video(thisActivity, str_array[16]);
		               	    				 	downloaderThread.start();
		                				   }
			                   	    	   if(item == 1)
		                   	    			{
			                   	    		   downloaderThread = new DownloaderThread_video(thisActivity, str_array_3gp[16]);
			                   	    		   downloaderThread.start();
		                   	    			}
		                			   }
		                   	  });
		                   	  
		                		   AlertDialog alert = builder.create();
		                		   alert.show();
		                   	 		}
		                   	  });
			           AlertDialog alertdialog=builder.create();
		               alertdialog.show();
	                	
	                }
	                if(position == 17){
	                	builder.setPositiveButton("Play Online",new DialogInterface.OnClickListener() {
	                		   public void onClick(DialogInterface dialog, int which) {
		                   		
	                			String videoId = "7mXOIBGFkVA";
		                   		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId)); 
		                   		intent.putExtra("VIDEO_ID", videoId); 
		                   		startActivity(intent); 
		                   		}
		                   	  });
		                     
		                 builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
		                	   public void onClick(DialogInterface dialog, int which) {
		                		   
		                		   final CharSequence[] items = {"480p", "176p"};
		                		   AlertDialog.Builder builder = new AlertDialog.Builder(video.this);
		                		   builder.setTitle("Download Quality");
		                		   builder.setIcon(R.drawable.download);
		                		   builder.setItems(items, new DialogInterface.OnClickListener() {
		                			   public void onClick(DialogInterface dialog, int item) {
		                				   
		                				   if(item == 0)
		                				   {
		                					   	downloaderThread = new DownloaderThread_video(thisActivity, str_array[17]);
		               	    				 	downloaderThread.start();
		                				   }
			                   	    	   if(item == 1)
		                   	    			{
			                   	    		   downloaderThread = new DownloaderThread_video(thisActivity, str_array_3gp[17]);
			                   	    		   downloaderThread.start();
		                   	    			}
		                			   }
		                   	  });
		                   	  
		                		   AlertDialog alert = builder.create();
		                		   alert.show();
		                   	 		}
		                   	  });
			           AlertDialog alertdialog=builder.create();
		               alertdialog.show();
	                	
	                }
	                if(position == 18){
	                	builder.setPositiveButton("Play Online",new DialogInterface.OnClickListener() {
	                		   public void onClick(DialogInterface dialog, int which) {
		                   		
	                			String videoId = "8rMzqhVg9yE";
		                   		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId)); 
		                   		intent.putExtra("VIDEO_ID", videoId); 
		                   		startActivity(intent); 
		                   		}
		                   	  });
		                     
		                 builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
		                	   public void onClick(DialogInterface dialog, int which) {
		                		   
		                		   final CharSequence[] items = {"480p", "176p"};
		                		   AlertDialog.Builder builder = new AlertDialog.Builder(video.this);
		                		   builder.setTitle("Download Quality");
		                		   builder.setIcon(R.drawable.download);
		                		   builder.setItems(items, new DialogInterface.OnClickListener() {
		                			   public void onClick(DialogInterface dialog, int item) {
		                				   
		                				   if(item == 0)
		                				   {
		                					   	downloaderThread = new DownloaderThread_video(thisActivity, str_array[18]);
		               	    				 	downloaderThread.start();
		                				   }
			                   	    	   if(item == 1)
		                   	    			{
			                   	    		   downloaderThread = new DownloaderThread_video(thisActivity, str_array_3gp[18]);
			                   	    		   downloaderThread.start();
		                   	    			}
		                			   }
		                   	  });
		                   	  
		                		   AlertDialog alert = builder.create();
		                		   alert.show();
		                   	 		}
		                   	  });
			           AlertDialog alertdialog=builder.create();
		               alertdialog.show();
	                	
	                }
	                if(position == 19){
	                	builder.setPositiveButton("Play Online",new DialogInterface.OnClickListener() {
	                		   public void onClick(DialogInterface dialog, int which) {
		                   		
	                			String videoId = "zdqNLGmoKuU";
		                   		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId)); 
		                   		intent.putExtra("VIDEO_ID", videoId); 
		                   		startActivity(intent); 
		                   		}
		                   	  });
		                     
		                 builder.setNegativeButton("Download",new DialogInterface.OnClickListener() {
		                	   public void onClick(DialogInterface dialog, int which) {
		                		   
		                		   final CharSequence[] items = {"480p", "176p"};
		                		   AlertDialog.Builder builder = new AlertDialog.Builder(video.this);
		                		   builder.setTitle("Download Quality");
		                		   builder.setIcon(R.drawable.download);
		                		   builder.setItems(items, new DialogInterface.OnClickListener() {
		                			   public void onClick(DialogInterface dialog, int item) {
		                				   
		                				   if(item == 0)
		                				   {
		                					   	downloaderThread = new DownloaderThread_video(thisActivity, str_array[19]);
		               	    				 	downloaderThread.start();
		                				   }
			                   	    	   if(item == 1)
		                   	    			{
			                   	    		   downloaderThread = new DownloaderThread_video(thisActivity, str_array_3gp[19]);
			                   	    		   downloaderThread.start();
		                   	    			}
		                			   }
		                   	  });
		                   	  
		                		   AlertDialog alert = builder.create();
		                		   alert.show();
		                   	 		}
		                   	  });
			           AlertDialog alertdialog=builder.create();
		               alertdialog.show();
	                	
	                }
	            }
	            
	        });
	 
	    }
	 
	    public void prepareList1()
	    {
	          desc = new ArrayList<String>();
	 
	          desc.add("C Tutorial");
	          desc.add("C# Tutorial");
	          desc.add("C++ Tutorial");
	          desc.add("Java Tutorial");
	          desc.add("SQL Tutorial");
	          desc.add("Shell Programming\n          Tutorial");
	          desc.add("VHDL Tutorial");
	          desc.add("8085 Architecture");
	          desc.add("Maths");
	          desc.add("Data Structures");
	          desc.add("Artificial Intelligence");
	          desc.add("Communication \n   Engineering");
	          desc.add("Compiler Design");
	          desc.add("Compter Architecture");
	          desc.add("Control Engineering");
	          desc.add("Digital Circuit &\n      System");
	          desc.add("Digital Signal\n  Processing");
	          desc.add("Graphics");
	          desc.add("Signals and Systems");
	          desc.add("Software Engineering");

	          

	          image = new ArrayList<Integer>();
	          image.add(R.drawable.v1);
	          image.add(R.drawable.v2);
	          image.add(R.drawable.v3);
	          image.add(R.drawable.v4);
	          image.add(R.drawable.v5);
	          image.add(R.drawable.v6);
	          image.add(R.drawable.v7);
	          image.add(R.drawable.v8);
	          image.add(R.drawable.v9);
	          image.add(R.drawable.v10);
	          image.add(R.drawable.v11);
	          image.add(R.drawable.v12);
	          image.add(R.drawable.v13);
	          image.add(R.drawable.v14);
	          image.add(R.drawable.v15);
	          image.add(R.drawable.v16);
	          image.add(R.drawable.v17);
	          image.add(R.drawable.v18);
	          image.add(R.drawable.v19);
	          image.add(R.drawable.v20);
	          
	    }
	    public Handler  activityHandler= new Handler()
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
