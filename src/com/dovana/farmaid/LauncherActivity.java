package com.dovana.farmaid;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

@SuppressLint("NewApi")
public class LauncherActivity extends Activity implements OnItemSelectedListener {

	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	private Spinner farmer_type,farmers_crops;
	EditText phone,farmer_land;
	CheckBox ONE,TWO,THREE;
	Button start;
	Integer First,Second,Third;
	Integer Total;
	
	String rice,jute,wheat,rice_jute,rice_wheat,jute_wheat,rice_jute_wheat;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_launcher);
		
		//farmers_crops= (Spinner)findViewById(R.id.farmer_crops);
		farmer_type = (Spinner)findViewById(R.id.farmer_type);
		phone=(EditText) findViewById(R.id.phone);
		farmer_land=(EditText) findViewById(R.id.farmer_land_amm);
		
		String number =getMyPhoneNO();  
        phone.setText(number.toString());
		farmer_type.setOnItemSelectedListener(this);
		
		
		//check box value
		ONE = (CheckBox)findViewById(R.id.rice);
		TWO = (CheckBox)findViewById(R.id.jute);
		THREE = (CheckBox)findViewById(R.id.wheat);
		start = (Button)findViewById(R.id.start);
		
		
		start.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			
			new AsyncTaskRunnerCurrent().execute();
			
		
		}
		});
		
		SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
	    if(pref.getBoolean("activity_executed", false)){
	        Intent intent = new Intent(this, MainActivity.class);
	        startActivity(intent);
	        finish();
	    } else {
	        Editor ed = pref.edit();
	        ed.putBoolean("activity_executed", true);
	        ed.commit();
	    }
			
	
	}
	private String getMyPhoneNO(){  
	     TelephonyManager mTelephonyMgr;    
	     mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
	     String yourNumber = mTelephonyMgr.getLine1Number();  
	     return yourNumber;   
	    }
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}      
	
	//----------- Fetching Current Project
			@SuppressLint("NewApi")
			class AsyncTaskRunnerCurrent extends AsyncTask<String, String, String>
			{
				 ProgressDialog progressDialog = new ProgressDialog(LauncherActivity.this);
				
				String error="";
				
				
				@Override
				protected String doInBackground(String... params) {
					// TODO Auto-generated method stub
					
					String result = null;
	        	   	InputStream is = null;
					ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					String farmer_types =LauncherActivity.convertToUTF8(farmer_type.getSelectedItem().toString());
					
					//
					String land_amm = LauncherActivity.convertToUTF8(farmer_land.getText().toString());
					String phone_num = LauncherActivity.convertToUTF8(phone.getText().toString());
					
					if(ONE.isChecked())
					{
					First = 1;
					Total = First;
					}
					if(TWO.isChecked())
					{
					Second = 2;
					Total = Second;
					}
					if(THREE.isChecked())
					{
					Third = 4;
					Total = Third;
					}
					if(ONE.isChecked() && TWO.isChecked())
					{
					Total = 3;
					}
					if(ONE.isChecked() && THREE.isChecked())
					{
					Total = 5;
					}
					if(THREE.isChecked() && TWO.isChecked())
					{
					Total = 6;
					}
					if(THREE.isChecked() && ONE.isChecked() && TWO.isChecked())
					{
					Total = 7;
					}
					
					
					switch(Total)
					{
					case 1:
						rice="ধান";
						String crop_name1 = LauncherActivity.convertToUTF8(rice);
						nameValuePairs.add(new BasicNameValuePair("crop_name",crop_name1));

					break;
					case 2:
						jute="পাট";
						String crop_name2 = LauncherActivity.convertToUTF8(jute);
						nameValuePairs.add(new BasicNameValuePair("crop_name",crop_name2));
					
					break;
					case 4:
						wheat="গম";	
						String crop_name3 = LauncherActivity.convertToUTF8(wheat);
						nameValuePairs.add(new BasicNameValuePair("crop_name",crop_name3));
					break;
					case 3:
						rice_jute="ধান,পাট";
						String crop_name4 = LauncherActivity.convertToUTF8(rice_jute);
						nameValuePairs.add(new BasicNameValuePair("crop_name",crop_name4));
					
					break;
					case 5:
						rice_wheat="ধান,গম";
						String crop_name5 = LauncherActivity.convertToUTF8(rice_wheat);
						nameValuePairs.add(new BasicNameValuePair("crop_name",crop_name5));
					
					break;
					case 6:
						jute_wheat="পাট,গম";		
						String crop_name6 = LauncherActivity.convertToUTF8(jute_wheat);
						nameValuePairs.add(new BasicNameValuePair("crop_name",crop_name6));
					break;
					case 7:
						rice_jute_wheat="ধান,পাট,গম";
						String crop_name7 = LauncherActivity.convertToUTF8(rice_jute_wheat);
						nameValuePairs.add(new BasicNameValuePair("crop_name",crop_name7));
					
					break;
					}
	        	   	nameValuePairs.add(new BasicNameValuePair("farmer_type",farmer_types));
	        	   	nameValuePairs.add(new BasicNameValuePair("land_amm",land_amm));
	        	   	nameValuePairs.add(new BasicNameValuePair("phone_num",phone_num));

	        	   	StrictMode.setThreadPolicy(policy); 

					try
					{
						HttpClient httpclient = new DefaultHttpClient();
	        	        HttpPost httppost = new HttpPost("http://digitalfarmer.tk/farmer_identity.php");
	        	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        	        HttpResponse response = httpclient.execute(httppost); 
	        	        HttpEntity entity = response.getEntity();
	        	        is = entity.getContent();

	        	        Log.e("log_tag", "connection success ");
	        	        //Toast.makeText(getApplicationContext(), "pass", Toast.LENGTH_SHORT).show();
	        	   }
	        	
	        	
	        	catch(Exception e)
	        	{
	        	        Log.e("log_tag", "Error in http connection "+e.toString());
	        	        Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();

	        	}
	        	  //convert response to string
		        	try{
		        	        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		        	        StringBuilder sb = new StringBuilder();
		        	        String line = null;
		        	        while ((line = reader.readLine()) != null) 
		        	        {
		        	                sb.append(line + "\n");
		        	       	        //Intent i = new Intent(getBaseContext(),MainActivity.class);
		        	                //startActivity(i);
		        	        }
		        	        is.close();

		        	        result=sb.toString();
		        	}
		        	catch(Exception e)
		        	{
		        	       Log.e("log_tag", "Error converting result "+e.toString());
		           	}
	        	   	
	        	   	try{
		        		
						JSONObject json_data = new JSONObject(result);

		                CharSequence w= (CharSequence) json_data.get("re");
		              
		                //Toast.makeText(getApplicationContext(), w, Toast.LENGTH_SHORT).show();

		      
		           }
		          catch(JSONException e)
		          {
		        Log.e("log_tag", "Error parsing data "+e.toString());
		        Toast.makeText(getApplicationContext(), "JsonArray fail", Toast.LENGTH_SHORT).show();
		          }
					return result;
			}
				
				
				
				protected void onPostExecute(String string)
				{
					
					progressDialog.dismiss();
					Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
			        startActivity(intent);
					Toast.makeText(getApplicationContext(), "ধন্যবাদ", Toast.LENGTH_SHORT).show();
					
				
				}
				
				protected void onPreExecute()
				{
					 
					progressDialog.setMessage("পাঠানো হচ্ছে ..অনুগ্রহ করে অপেক্ষা করুন..");
					progressDialog.setCancelable(false);
					progressDialog.show();
				}
				
				
				
				
					}
			
			
			
			//------------------------------------------------------------------
			
			public static String convertToUTF8(String s) {
		        String out = null;
		        try {
		            out = new String(s.getBytes("UTF-8"), "ISO-8859-1");
		        } catch (java.io.UnsupportedEncodingException e) {
		            return null;
		        }
		        return out;
		    }
	

}
