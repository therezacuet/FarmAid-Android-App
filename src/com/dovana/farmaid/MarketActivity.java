package com.dovana.farmaid;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MarketActivity extends Activity {

	Button complain,market;
	
	private static final String TAG = "Main";
    private ProgressDialog progressBar;
	WebView webview;
	@Override
	@SuppressLint("SetJavaScriptEnabled") protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_market);
		
		complain=(Button) findViewById(R.id.complain);
		market=(Button) findViewById(R.id.market);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3983C0")));
		bar.setTitle(Html.fromHtml("<b>শস্য বাজার</b>"));
		
		
		//internet connection cheack and show dialog if no data connection
		if (!DetectConnection.checkInternetConnection(this)) {

			AlertDialog.Builder dialog = new AlertDialog.Builder(MarketActivity.this);
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
			
		webview=(WebView) findViewById(R.id.webView1);
			//load data from server
		   
		webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        progressBar = ProgressDialog.show(MarketActivity.this, "", "লোড হচ্ছে..অপেক্ষা করুন...");

        webview.setWebViewClient(new WebViewClient() {
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
               
                Toast.makeText(MarketActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
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
        webview.loadUrl("http://digitalfarmer.tk/price.php");
		
		
		}
		complain.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MarketActivity.this,ComplainActivity.class);
				startActivity(i);
				overridePendingTransition(R.anim.slide_left_in,0);
 
			}
		});
		
		market.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MarketActivity.this,Digital_Market_Activity.class);
				startActivity(i);
				overridePendingTransition(R.anim.slide_left_in,0);
			}
		});
	}


}
