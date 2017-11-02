package com.dovana.farmaid;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

@SuppressLint("NewApi")
public class FertilizerActivity extends Activity implements OnItemSelectedListener {

	private Spinner crop_name,soil_type,land_type;
	TextView fosol,mati,jomi_type,jomi_amm;
	EditText land_ammount;
	Button result,ppp;
	MediaPlayer result_show,crop_type,land_amm,soil_t,soil_amm,land_t;
	boolean startstatus1,startstatus2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fertilizer);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3983C0")));
		bar.setTitle(Html.fromHtml("<b>সার সুপারিশ</b>"));
		
		result=(Button) findViewById(R.id.button1);
		crop_name = (Spinner)findViewById(R.id.crop_name);
		soil_type = (Spinner)findViewById(R.id.soil_type);
		land_type = (Spinner)findViewById(R.id.land_type);
        land_ammount = (EditText)findViewById(R.id.land_ammount);
        //crop_name.setOnItemSelectedListener(this);
        //soil_type.setOnItemSelectedListener(this);
        //land_type.setOnItemSelectedListener(this);
        
        fosol=(TextView) findViewById(R.id.fosol);
        mati=(TextView) findViewById(R.id.mati);
        jomi_type=(TextView) findViewById(R.id.jomi_type);
        jomi_amm=(TextView) findViewById(R.id.jomi_amm);
        
        
        
        
        crop_type=MediaPlayer.create(this, R.raw.soil_fosholer_dhoron);
        land_amm=MediaPlayer.create(this, R.raw.soil_jomir_poriman);
        land_t=MediaPlayer.create(this, R.raw.jomir_dhoron_nirbachon);
        soil_t=MediaPlayer.create(this, R.raw.soil_matir_dhoron);
        soil_amm=MediaPlayer.create(this, R.raw.soil_jomir_poriman);
        
        result_show=MediaPlayer.create(this, R.raw.soil_folafol_showing);
        startstatus1=false;
        
        crop_type.start();
        fosol.setTextColor(Color.parseColor("#FF0000"));
        
        crop_name.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String text = crop_name.getSelectedItem().toString();
				if(text.compareTo("ফসল নির্বাচন করুন") != 0){
					
					
					crop_type.stop();
					soil_t.start();
					fosol.setTextColor(Color.parseColor("#000000"));
					mati.setTextColor(Color.parseColor("#FF0000"));
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
        soil_type.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String text = soil_type.getSelectedItem().toString();
				if(text.compareTo("মাটির ধরণ নির্বাচন করুন") != 0){
					
					
					crop_type.stop();
					soil_t.stop();
					land_t.start();
					mati.setTextColor(Color.parseColor("#000000"));
					jomi_type.setTextColor(Color.parseColor("#FF0000"));
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        land_type.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				String text = land_type.getSelectedItem().toString();
				if(text.compareTo("জমির ধরণ নির্বাচন করুন") != 0){
					
					crop_type.stop();
					soil_t.stop();
					soil_amm.start();
					jomi_type.setTextColor(Color.parseColor("#000000"));
					jomi_amm.setTextColor(Color.parseColor("#FF0000"));
					
					
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
        result.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				new AsyncTaskRunnerCurrent().execute();
				
				
				
			}
		});
        
        
	}
	
	
	
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		
		
		
        	
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	//----------- Fetching Current Project
			class AsyncTaskRunnerCurrent extends AsyncTask<String, String, String>
			{
				 ProgressDialog progressDialog = new ProgressDialog(FertilizerActivity.this);
				
				
				@Override
				protected String doInBackground(String... params) {
					// TODO Auto-generated method stub
					final String crops= String.valueOf(crop_name.getSelectedItem());
					final String soil= String.valueOf(soil_type.getSelectedItem());
					final String land= String.valueOf(land_type.getSelectedItem());
					final String ammount= land_ammount.getText().toString();
					

					try
					{
						
						Intent intent=new Intent(FertilizerActivity.this,ResultActivity.class);
						intent.putExtra("crop_name",crops);
						intent.putExtra("soil_name",soil);
						intent.putExtra("land_name",land);
						intent.putExtra("land_ammount",ammount);
						startActivity(intent);
						overridePendingTransition(R.anim.slide_left_in,0);

						
						
						
	        	   }
	        	
	        	
	        	catch(Exception e)
	        	{
	        	        Log.e("log_tag", "Error in http connection "+e.toString());
	        	        

	        	}
					return null;

			}
				
				
				
				protected void onPostExecute(String string)
				{
					
					progressDialog.dismiss();
						
					
					
				
				}
				
				protected void onPreExecute()
				{
					if(startstatus1==false){
						
						result_show.start();
						startstatus1=true;
					}
					progressDialog.setMessage("ফলাফল তৈরী হচ্ছে..."); 
					progressDialog.setCancelable(false);
					progressDialog.show();
				}
				
				
				
				
					}
			
			
			
			//------------------------------------------------------------------



}
