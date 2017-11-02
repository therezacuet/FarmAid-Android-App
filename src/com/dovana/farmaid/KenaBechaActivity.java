package com.dovana.farmaid;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

@SuppressLint("NewApi")
public class KenaBechaActivity extends Activity {

	private static final String TAG = "Main";
    private ProgressDialog progressBar;
	WebView web1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kena_becha);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3983C0")));
		bar.setTitle(Html.fromHtml("<b>বেচা-কেনা</b>"));
		
		String selectedItem=getIntent().getExtras().getString("kena");
		web1=(WebView) findViewById(R.id.webkenabecha);
		
		//internet connection cheack and show dialog if no data connection
				if (!DetectConnection.checkInternetConnection(this)) {

					AlertDialog.Builder dialog = new AlertDialog.Builder(KenaBechaActivity.this);
			           dialog.setTitle("              সতর্কতা !!");
			           dialog.setMessage("      আপনার ডাটা কানেকশন বন্ধ আছে\n              চালু করুন !!");
			           dialog.setCancelable(false);
			           dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						
			        	   @Override
			        	   public void onClick(DialogInterface dialog, int which) {
			        		   dialog.cancel();
							
			        	   }
			           });
			           dialog.show();	         
					} 
				else { 
					web1.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

			        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

			        progressBar = ProgressDialog.show(KenaBechaActivity.this, "", "লোড হচ্ছে..অপেক্ষা করুন...");

			        web1.setWebViewClient(new WebViewClient() {
			            public boolean shouldOverrideUrlLoading(WebView view, String url) {
			                Log.i(TAG, "Processing webview url click...");
			                view.loadUrl(url);
			                return true;
			            }

			            public void onPageFinished(WebView view, String url) {
			                Log.i(TAG, "Finished loading URL: " + url);
			                if (progressBar.isShowing()) {
			                    progressBar.dismiss();
			                }
			            }

			            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			               
			                Toast.makeText(KenaBechaActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
			                alertDialog.setTitle("Error");
			                alertDialog.setMessage(description);
			                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			                    public void onClick(DialogInterface dialog, int which) {
			                        return;
			                    }
			                });
			                alertDialog.show();
			            }
			        });
			        if(selectedItem.equals("1")){
						
			        	web1.loadUrl("http://digitalfarmer.tk/setPrice.php");
				       
					
					}
					
					
					else if(selectedItem.equals("2")){
						

				        
				        
				        web1.loadUrl("http://digitalfarmer.tk/marketing/search.php");
					
					}
		
		
	  }
	
	}


}
