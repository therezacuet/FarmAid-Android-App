package com.dovana.farmaid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
MediaPlayer mp;
TextView markettext,diseasetext,fertilizertext;
	 private AudioManager audioManager = null; 
	ImageButton btnSound;
	Integer i=0,j=0;
	LinearLayout crop_market,diseases_l,fertilizer_l;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		crop_market=(LinearLayout) findViewById(R.id.market_l);
		diseases_l=(LinearLayout) findViewById(R.id.disease_l);
		fertilizer_l=(LinearLayout) findViewById(R.id.fertilizer_l); 
		mp=MediaPlayer.create(this, R.raw.first);
	    mp.setLooping(false);
	    mp.start();
		markettext=(TextView)findViewById(R.id.market_text);
		diseasetext=(TextView)findViewById(R.id.diseases_text);
		fertilizertext=(TextView)findViewById(R.id.fertilizer_text); 
        btnSound=(ImageButton) findViewById(R.id.btnSound);
        
        btnSound.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//mute audio
				if(i%2==0){
				mute();
				}else{
				unmute();
				}
				i++;
			}

		});
		
		crop_market.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				mp.stop();
				// TODO Auto-generated method stub
				Intent i=new Intent(MainActivity.this,MarketActivity.class);
				startActivity(i);
				overridePendingTransition(R.anim.slide_left_in,0);

				
				
			}
		});
		
		diseases_l.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				mp.stop();
				// TODO Auto-generated method stub
				Intent i=new Intent(MainActivity.this,DiseasesActivity.class);
				startActivity(i);
				overridePendingTransition(R.anim.slide_left_in,0);
			}
		});
		fertilizer_l.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				mp.stop();
				// TODO Auto-generated method stub
				Intent i=new Intent(MainActivity.this,FertilizerActivity.class);
				startActivity(i);
				overridePendingTransition(R.anim.slide_left_in,0);

			}
		});
       
//		govt_l.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				mp.stop();
//				// TODO Auto-generated method stub
//				Intent i=new Intent(MainActivity.this,ModernTechnology.class);
//				startActivity(i);
//				overridePendingTransition(R.anim.slide_left_in,0);
//
//			}
//		});
		markettext.setTextColor(Color.parseColor("#008000"));
		ghura();
	}
	private void ghura() {
		// TODO Auto-generated method stub
		Handler h= new Handler();
		h.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				
				rongKor();
			}

			
		}, 2000);
	}

	private void rongKor() {
		// TODO Auto-generated method stub
		if(j==0){
			markettext.setTextColor(Color.parseColor("#000000"));
			diseasetext.setTextColor(Color.parseColor("#008000"));
		}
		else if(j==1)
		{
			diseasetext.setTextColor(Color.parseColor("#000000"));
			fertilizertext.setTextColor(Color.parseColor("#008000"));
		}
		else if(j==2)
		{
			fertilizertext.setTextColor(Color.parseColor("#000000"));
			//govttext.setTextColor(Color.parseColor("#008000"));
		}
//		else if(j==3)
//		{
//			
//			govttext.setTextColor(Color.parseColor("#000000"));
//		}
		
		
		j++;
		ghura();
		
	}
	
	public void mute() {
		AudioManager amanager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
        amanager.setStreamMute(AudioManager.STREAM_ALARM, true);
        amanager.setStreamMute(AudioManager.STREAM_MUSIC, true);
        amanager.setStreamMute(AudioManager.STREAM_RING, true);
        amanager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
        btnSound.setImageResource(android.R.drawable.ic_lock_silent_mode);
		
	}
	public void unmute() {

    	AudioManager amanager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
			amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
             amanager.setStreamMute(AudioManager.STREAM_ALARM, false);
             amanager.setStreamMute(AudioManager.STREAM_MUSIC, false);
             amanager.setStreamMute(AudioManager.STREAM_RING, false);
             amanager.setStreamMute(AudioManager.STREAM_SYSTEM, false);
             btnSound.setImageResource(android.R.drawable.ic_lock_silent_mode_off);
			
	}


}
