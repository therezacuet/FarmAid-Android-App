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
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

@SuppressLint("NewApi")
public class ComplainActivity extends Activity implements OnItemSelectedListener {

	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	EditText selling_center_name,reporterr_name,reporter_phone_no,report_details;
	private Spinner spinner1, spinner2,spinner3;
	Button report;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_complain);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3983C0")));
		bar.setTitle(Html.fromHtml("<b>অভিযোগ</b>"));
		
		
		selling_center_name=(EditText) findViewById(R.id.name);
		
		reporterr_name=(EditText) findViewById(R.id.reporter_name);
		reporter_phone_no=(EditText) findViewById(R.id.reporter_phone);
		report_details=(EditText) findViewById(R.id.reporter_message);
		
		String number =getMyPhoneNO();  
		reporter_phone_no.setText(number.toString());
		
		
		spinner1 = (Spinner)findViewById(R.id.spinner1);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner3 = (Spinner)findViewById(R.id.spinner3);
        spinner1.setOnItemSelectedListener(this);
        report=(Button) findViewById(R.id.submit);
        report.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				new AsyncTaskRunnerCurrent().execute();
        	   	
        	   	
        	   	
        	   	
			}
		});

	}
	 public void onItemSelected(AdapterView<?> arg0, View v, int arg2,long arg3) {
	        // TODO Auto-generated method stub
	        String sp1= String.valueOf(spinner1.getSelectedItem());
	        if(sp1.contentEquals("ঢাকা")) {
	        	String[] dhaka_division = new String[]{"আপনার জেলা  সিলেক্ট করুন","ঢাকা","ফরিদপুর","গাজীপুর","গোপালগঞ্জ","কিশোরগঞ্জ","মাদারীপুর","মানিকগঞ্জ","মুন্সীগঞ্জ","নারায়ণগঞ্জ","নরসিংদী","রাজবাড়ী","শরীয়তপুর","টাঙ্গাইল"};
	        	
	            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
	                android.R.layout.simple_spinner_item, dhaka_division);
	            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	            dataAdapter.notifyDataSetChanged();
	            spinner2.setAdapter(dataAdapter);
	            
	            spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						
					   
						
						// TODO Auto-generated method stub
						String spf= String.valueOf(spinner2.getSelectedItem());
			            if(spf.contentEquals("টাঙ্গাইল")){
			            	String[] tangail = new String[]{"আপনার উপজেলা  সিলেক্ট করুন","মধুপুর","ধনবাড়ী","গোপালপুর","ভূঞাপুর","ঘাটাইল","কালিহাতী","টাঙ্গাইল সদর","নাগরপুর","দেলদুয়ার","মির্জাপুর","বাসাইল","সখিপুর"};
			            	
			            	ArrayAdapter<String> tangailadapter = new ArrayAdapter<String>(ComplainActivity.this,android.R.layout.simple_spinner_item, tangail);
			            	tangailadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			            	tangailadapter.notifyDataSetChanged();
			    	            spinner3.setAdapter(tangailadapter);
				            
			            }
						
					}
					 
					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
	            	
				});
	            
	            
	            
	            
	            
	        }
	 }
	
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	//----------- Fetching Current Project
		class AsyncTaskRunnerCurrent extends AsyncTask<String, String, String>
		{
			 ProgressDialog progressDialog = new ProgressDialog(ComplainActivity.this);
			
			String error="";
			
			
			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				
				String result = null;
        	   	InputStream is = null;
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				String center_name = ComplainActivity.convertToUTF8(selling_center_name.getText().toString());
				String center_district =ComplainActivity.convertToUTF8(spinner2.getSelectedItem().toString());
				String center_subdistrict = ComplainActivity.convertToUTF8(spinner3.getSelectedItem().toString());
				
				String reporter_name = ComplainActivity.convertToUTF8(reporterr_name.getText().toString());
				String reporter_phone = ComplainActivity.convertToUTF8(reporter_phone_no.getText().toString());
				String reporter_message = ComplainActivity.convertToUTF8(report_details.getText().toString());
				
				
				
        	   	nameValuePairs.add(new BasicNameValuePair("center_name",center_name));
        	   	nameValuePairs.add(new BasicNameValuePair("center_district",center_district));
        	   	nameValuePairs.add(new BasicNameValuePair("center_subdistrict",center_subdistrict));
        	   	nameValuePairs.add(new BasicNameValuePair("reporter_name",reporter_name));
        		nameValuePairs.add(new BasicNameValuePair("reporter_phone",reporter_phone));
        		nameValuePairs.add(new BasicNameValuePair("reporter_message",reporter_message));

        	   	StrictMode.setThreadPolicy(policy); 

				try
				{
					HttpClient httpclient = new DefaultHttpClient();
        	        HttpPost httppost = new HttpPost("http://digitalfarmer.tk/report_insert.php");
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
				Toast.makeText(getApplicationContext(), "ধন্যবাদ অভিযোগ করার জন্য", Toast.LENGTH_SHORT).show();
				
			
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

		private String getMyPhoneNO(){  
		     TelephonyManager mTelephonyMgr;    
		     mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		     String yourNumber = mTelephonyMgr.getLine1Number();  
		     return yourNumber;   
		    }
	

}
