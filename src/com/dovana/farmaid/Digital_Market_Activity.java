package com.dovana.farmaid;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

@SuppressLint("NewApi")
public class Digital_Market_Activity extends Activity {

	LinearLayout becha,kena;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_digital__market_);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3983C0")));
		bar.setTitle(Html.fromHtml("<b>শস্য বেচা-কেনা</b>"));
		
		becha=(LinearLayout) findViewById(R.id.becha);
		kena=(LinearLayout) findViewById(R.id.kena);
		
		kena.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Digital_Market_Activity.this,KenaBechaActivity.class);
				i.putExtra("kena", "1");
				startActivity(i);
			}
		});
		
		becha.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Digital_Market_Activity.this,KenaBechaActivity.class);
				i.putExtra("kena", "2");
				startActivity(i);
				
			}
		});
	}



}
