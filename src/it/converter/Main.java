package it.converter;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import main.WelcomeScreen;

import XML.BuildXML;
import aboutus.AboutUsMain;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.StateListDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import eHandy.gtbit.R;
 
public class Main extends Activity {
    /** Called when the activity is first created. */
	
	private EditText input, output; 	
	private Button classes, part, arr;	 
	private ImageButton cancel, scam, copy; 
	private Context context;
	
	private String[] grp;	
	private ArrayList<GroupMeasures> ms;	 
	
	private Measures part_c = null, arr_c = null; 
	
	private int part_v = -1;	
	private int arr_v = -1; 	
	private int grp_v = -1;	
	
	private GroupMeasures part_measure, arr_measure; 

	private int GRP;	
	
	private ArrayList<int[]> gruppi_ordinati;	
	private boolean cn = false;	
	SharedPreferences sp;
	Editor edit;
	
	private final String TEST_UPDATE = "test_update";  
	private final double VERSION = 1.0;	
	private boolean update_measures = false;	
	
	
	private int EDITOR = 31;		
	private int ADDMEASURE = 42;
	
	
	public static final String sort = "sort_list";
	private final String CONTATORE = "contatore_uso";
	private final int TEST_RESET_CONTATORE = 10;
	
	public static final String MODI_ORDINAMENTO = "modi_ordinamento";
	
	private final int MAX_MEASURES_SHARING = 5;
	private final String SHARING_SETTINGS = "Sharing settings";
	private final String SHARING_DATE = "data";
	private final String SHARING_COUNT = "conteggio";
	private int count = 0;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_unit);
        
      //Home Button on Top
		ImageButton homebtn = (ImageButton)findViewById(R.id.homebtn);
		selector(homebtn, R.drawable.icon_home_red, R.drawable.icon_home);
		homebtn.setOnClickListener(new OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(Main.this, WelcomeScreen.class);
				startActivity(in);
				Main.this.finish();
			}
		});
        
        context = this;
        
        GRP = Integer.parseInt(this.getString(R.string.GROUPS));    		
        load_ordinamento();
	
        bindButtonsAndText();
       
        resetButton();
       
        setGroupListener();
       
       
      
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
    
   
    
    private void load_ordinamento() {
  
	    sp = context.getSharedPreferences(sort, MODE_PRIVATE);
	    edit = sp.edit();
	    
	    int numero_utilizzi = sp.getInt(CONTATORE, 1);
	    
	    gruppi_ordinati = new ArrayList<int[]>();
	    
	   
	    for(int i=0; i<GRP; i++) {
	    
	    	int[] value = new int[2];
	    	
	    	value[0] = i;
	    	value[1] = sp.getInt("c"+i, 0);
	    	
	    	gruppi_ordinati.add(value);
	    }
	 
	   
	    if (numero_utilizzi >= TEST_RESET_CONTATORE) {
	    	for(int i=0; i<GRP; i++) 
	    		edit.putInt("c"+i, (int)gruppi_ordinati.get(i)[1]/numero_utilizzi);
	    		
	    	numero_utilizzi = 0;
	    	}
	    
	    numero_utilizzi++;
	    edit.putInt(CONTATORE, numero_utilizzi++);
	    edit.commit();
	    
	   
	    Collections.sort(gruppi_ordinati, new Comparator<int[]>() {
			@Override
			public int compare(int[] lhs, int[] rhs) {
				
				String t1 = lhs[1]+"";
				String t2 = rhs[1]+"";
				return t2.compareTo(t1);
			}
	    });
	     
	    loadMeasures(gruppi_ordinati);
    }
    	
    	
    	
 
    private void testUpdate() {
    	
    	SharedPreferences sp = context.getSharedPreferences(context.getString(R.string.app_name), MODE_PRIVATE);
    	double test = Double.parseDouble(sp.getString(TEST_UPDATE, "0"));
    	Log.i("test", "versione attuale:"+test);
    	
    	
    	if(test<VERSION) {
    		update_measures = true;	
    		Editor ed = sp.edit();
    		ed.putString(TEST_UPDATE, VERSION+"");	
    		ed.commit();
    	}
    }
    
    
    private GroupMeasures testUpdate(InputStream is, int i) {
    	GroupMeasures t, tR;
    
    	InputStream isR = loadFromResources(i);
    	
    	t = GroupMeasures.load(is, context);
    	tR = GroupMeasures.load(isR, context);
  
    	if (tR.getVersione()>t.getVersione()) 
    		
    		t.update(tR.getVersione(), tR.getMeasures(), context);    		
    	return t;
    }
    
   
    private static int getResId(String variableName, Context context, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } 
    }
    
    
  
    private InputStream loadFromResources(int i) {
    	
   	int id = getResId("c"+i, this,R.raw.class);
    	
    	InputStream is = this.getApplicationContext().getResources().openRawResource(id);
		
		return is;
    }
    
    
    
    private void loadMeasures(ArrayList<int[]> ordine) {
    	  
    
    	testUpdate();
    	
    	
    	ms = new ArrayList<GroupMeasures>();
    	grp = new String[GRP];

    	
    	for (int i=0; i<GRP; i++) {
    		InputStream is;
    		GroupMeasures t;
    		
    	
    		int o = ordine.get(i)[0];
    		
    		
    		boolean testUpdate = false;
    		try {
    			
    			File file = BuildXML.buildPath(context.getString(R.string.app_name), o+".xml");
    			is = new FileInputStream(file);
    			testUpdate = true;
    			Log.i("load", "c"+o+" loaded from SD");
    		} catch (Exception e) {	

    			is = loadFromResources(o);
    		}
    		
    		if (testUpdate && update_measures) 
    				t = testUpdate(is, o); 
    		else
    			
    			t = GroupMeasures.load(is, context);
    		
    		ms.add(t);
    	
    		grp[i] = t.getGroup(); 
    	}
    }
    
    
	private void bindButtonsAndText() {
    	
    	input = (EditText)findViewById(R.id.editTextConvert);
    	output = (EditText)findViewById(R.id.editTextResult);
    	
    	classes = (Button)findViewById(R.id.button_classi);
    	part = (Button)findViewById(R.id.button_partenza);
    	arr = (Button)findViewById(R.id.button_arrivo);
    	
    	cancel = (ImageButton)findViewById(R.id.button_reset);
    	scam = (ImageButton)findViewById(R.id.button_scambia);
    	
    
    	
    	copy = (ImageButton)findViewById(R.id.button_copy);
    	
    	resetMeasures();
    	setScambiaListener();
    	setCopyListener();
    }

	
	private void resetMeasures() {
    	part.setText(R.string.select_subtype);
    	arr.setText(R.string.select_subtype_to);
    	part_v = -1;
    	arr_v = -1;
    }

	
	private void setScambiaListener() {
    	
		
    	scam.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if ((part_v > -1) && (arr_v > -1)) {
					
					part.setText(arr_measure.getListaVisibile()[arr_v]);
					part_c = arr_measure.getMeasure(arr_v);
					
					arr.setText(part_measure.getListaVisibile()[part_v]);
					arr_c = part_measure.getMeasure(part_v);
					
					int t = part_v;
					part_v = arr_v;
					arr_v = t;
					
					calculate();					
				}
			}
    	});
    }
	

	private void setCopyListener() {
    	copy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			
				if (output.getText().toString().length()>0) {
					ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE); 
					clipboard.setText(output.getText().toString());	
					Toast t = Toast.makeText(context, R.string.copy_s, Toast.LENGTH_SHORT);
					t.show();
				}
			}
    	});
    }

	
   
    private void resetButton() {
    	cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				input.setText("");
				output.setText("");
			}
    	});
    }
    
   
   
    
    private void setGroupListener() {
    	
    	
    	final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
    	        android.R.layout.simple_spinner_dropdown_item, grp);
    	
    	
    	classes.setOnClickListener(new OnClickListener () {

			@Override
			public void onClick(View v) {
				
				 new AlertDialog.Builder(context)
				 .setTitle(context.getText(R.string.select_type)) 
				
				 .setSingleChoiceItems(adapter, grp_v, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						classes.setText(grp[which]);
						
						cancel.performClick();
						
						resetMeasures();
						
						updateMeasuresListeners(which);
						
						enableTextListener();
						
						grp_v = which;
						
						cn = true;
						dialog.dismiss();
					}
				 }).create().show();
			}			
    	});	
    }
    
    
   
    private void updateMeasuresListeners(int which) {
    	
    	
    	part_c = null;
    	arr_c = null;
    	
    
    	part_measure = ms.get(which);
    	
    	
    	final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
    			android.R.layout.simple_spinner_dropdown_item, part_measure.getListaVisibile());
    	
    	part.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				new AlertDialog.Builder(context)
				 .setTitle(context.getText(R.string.select_subtype))
				 .setSingleChoiceItems(adapter, part_v, new DialogInterface.OnClickListener() {
					 
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						part.setText(part_measure.getListaVisibile()[which]);
					
						part_c = part_measure.getVisibleMeasure(which);
						 
						part_v = which;
						
						calculate();
						dialog.dismiss();
					}
				 }).create().show();	
			}
    	});
    
    	
    	arr_measure = ms.get(which);
    	
    	final ArrayAdapter<String> adapterA = new ArrayAdapter<String>(this,
    			android.R.layout.simple_spinner_dropdown_item, arr_measure.getListaVisibile());
    	
    	arr.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				new AlertDialog.Builder(context)
				 .setTitle(context.getText(R.string.select_subtype))
				 .setSingleChoiceItems(adapterA, arr_v, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						arr.setText(arr_measure.getListaVisibile()[which]);
						 
						arr_c = arr_measure.getVisibleMeasure(which);
						
						arr_v = which;
						calculate();
						dialog.dismiss();
					}
				 }).create().show();	
			}
    	});	
    }
    
    
    private void enableTextListener() {
    	
    	try {
	    	input.addTextChangedListener(new TextWatcher() {
	
				@Override
				public void afterTextChanged(Editable s) {}
	
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {}
	
				@Override
				public void onTextChanged(CharSequence s, int start, int before,
						int count) {
										calculate();
				}
	    	});
    	} catch (Exception e) {
    		cancel.performClick();
    	}
    }
    
   
    private void calculate() {
    	
    	String textI = input.getText().toString();
    	
   
    	if ((part_c!=null) && (arr_c!=null) && (textI.length()>0)) {
	    	double in = Double.parseDouble(textI);
	    	double temp = part_c.getFrom(in);
	    	double out = arr_c.getTo(temp);
	    	output.setText(out+ " " + arr_c.getSymbol());
	    	
	    	if (cn) {
	    		int[] t = gruppi_ordinati.get(grp_v);
	    		int ref = t[0];
	    		int value = t[1];
	    		value++;
	    		t[1] = value;
	    		gruppi_ordinati.remove(grp_v);
	    		gruppi_ordinati.add(grp_v, t);
	    		edit.putInt("c"+ref, value);
	    		Log.i("ordinamento", "ref:"+ref+", value:"+value);
	    		edit.commit();
	    		cn = false;
	    	}
    	}
    }
     
   
 	@Override
     public boolean onCreateOptionsMenu(Menu menu) {
         MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.main_menu_unit, menu);
         return true;
     } 
 	
 
 	private void setPreferences() {
 		
 		
 		int numero_id = getResId(MODI_ORDINAMENTO, this, R.string.class);
 		int numero = Integer.parseInt(context.getString(numero_id));
 				
 		String[] tipi_ordinamento = new String[numero];
 		
 		for(int i=0; i<numero; i++) {
 			int id = getResId("o"+i, this, R.string.class);
 			tipi_ordinamento[i] = context.getString(id);	
 			Log.i("ordinamento", "modi:"+tipi_ordinamento[i]);
 		}
 		
 		final int choosed = context.getSharedPreferences(sort, Context.MODE_PRIVATE).getInt(MODI_ORDINAMENTO, 0);
 		
 		
 		new AlertDialog.Builder(context)
 		.setTitle(R.string.preferenze_o)
 		.setSingleChoiceItems(tipi_ordinamento, choosed, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				context.getSharedPreferences(sort, Context.MODE_PRIVATE)
				.edit().putInt(MODI_ORDINAMENTO, which).commit();
				
				if (choosed != which) {
					for(int i=0; i< ms.size(); i++) {
						GroupMeasures gm = ms.get(i);
						gm.reOrder(context);
						ms.remove(i);
						ms.add(i, gm);
					}
					cancel.performClick();
					
					resetMeasures();
					
					updateMeasuresListeners(grp_v);
				}
				dialog.dismiss();
			}
		})
 		.create()
 		.show();
 	}
 	
 	
 	private void sendPersonalMeasures() {
 		
 		
 		final SharedPreferences sp = context.getSharedPreferences(SHARING_SETTINGS, Context.MODE_PRIVATE);
 		
 		
 		long old_date = sp.getLong(SHARING_DATE, 0);
 		count = sp.getInt(SHARING_COUNT, 0);
 		
 		Calendar current_time = Calendar.getInstance();
 		Calendar oldtime = Calendar.getInstance();
 		oldtime.setTimeInMillis(old_date);
 	
 		oldtime.add(Calendar.DAY_OF_MONTH, 1);
 		
 		
 		final boolean test_oldtime = oldtime.after(current_time);
 		
 		if ((test_oldtime && (count < MAX_MEASURES_SHARING)) || (!test_oldtime)) {
 		
	 		final ArrayList<Measures> pm = new ArrayList<Measures>();
	 		
	 	
	 		for(GroupMeasures gm: ms) {
	 			for(Measures m: gm.getMeasures()) {
	 				if(m.isPersonal())
	 					pm.add(m);
	 			}
	 		}
	 		
	 
	 		String[] mp = new String[pm.size()];
	 		final boolean[] show = new boolean[pm.size()];
	 		for(int i=0; i< pm.size(); i++) {
	 			mp[i] = pm.get(i).getId();
	 			show[i] = false;
	 		}
	 		
	 		
	 		new AlertDialog.Builder(context)
	 		.setTitle(context.getText(R.string.select_type_to_send))
	 		
	 		.setMultiChoiceItems(mp, show, new OnMultiChoiceClickListener() {
	
	 			@Override
	 			public void onClick(DialogInterface arg0, int arg1,
	 					boolean arg2) {
					show[arg1] = arg2;
				}				 
			 })
			 
			 .setPositiveButton(R.string.next, new DialogInterface.OnClickListener() {
	
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					
					String output = "";
					
					for(int i=0; i<show.length; i++) {
						if(show[i]) 
							output += pm.get(i).toString() + " \n";
					}
					
					if (output.length()> 0) {
						
						ConnectivityManager connManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
						
						NetworkInfo netInfo= connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
						
						NetworkInfo wifiInfo= connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
						
						
						if ( netInfo.getState() != NetworkInfo.State.CONNECTED && wifiInfo.getState() != NetworkInfo.State.CONNECTED ) 
							Toast.makeText(Main.this, R.string.no_internet, Toast.LENGTH_SHORT).show();
						else {
							
							try {
								
								Toast.makeText(Main.this, R.string.send_ok, Toast.LENGTH_SHORT).show();
								Editor ed = sp.edit();
								
								if (test_oldtime) {
						 			ed.putInt(SHARING_COUNT,  count+1);Log.i("test", "++:"+(count+1));}
						 		else {
						 			ed.putInt(SHARING_COUNT, 0);
						 			ed.putLong(SHARING_DATE, Calendar.getInstance().getTimeInMillis());
						 		}
								ed.commit();
								
							} catch (Exception e) {
								e.printStackTrace();
								Toast.makeText(Main.this, "error", Toast.LENGTH_SHORT).show();
							}
						}
						dialog.dismiss();
					}
					else	
						Toast.makeText(Main.this, R.string.error_no_selection, Toast.LENGTH_SHORT).show();
				} 
			 }).create().show();
 		}
 		else 
 			Toast.makeText(Main.this, R.string.error_exceed_sharing, Toast.LENGTH_SHORT).show();
 	}
 
 	
 	protected void onActivityResult(int requestCode, int resultCode,
            Intent data) {
 		
 		ArrayList<GroupMeasures> g = null;
 		
        if ((requestCode == EDITOR) && (resultCode == RESULT_OK)) 
        	
        	g = data.getParcelableArrayListExtra("misure");	
        
        else if ((requestCode == ADDMEASURE) && (resultCode == RESULT_OK)) {
        
        	GroupMeasures m = data.getParcelableExtra("misura");	
        	if (m!= null) {
        		g = ms;
        		
            	for(int i=0; i<ms.size(); i++)	{
            		if(m.getId()==g.get(i).getId()) {
            			g.remove(i);
            			g.add(i, m);
            			break;
            		}
            	}
        	}
        }

       
        if (g!= null) {
        	
        	ms = g;
        	
            this.resetButton();
        	this.setGroupListener();
        	if (grp_v >-1) {
        		
				cancel.performClick();
				
				resetMeasures();
				
				updateMeasuresListeners(grp_v);
        	}
        }
    }
	public void onBackPressed() {
		Intent intent = new Intent(this, WelcomeScreen.class);
		startActivity(intent);
		this.finish();
	}
}
