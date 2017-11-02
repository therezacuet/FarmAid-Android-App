package com.dovana.farmaid;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("NewApi")
public class DisList extends Activity implements OnClickListener{
TextView t1,t2,t3;
String getkey="";
String[] dhanpat;
Button back;
MediaPlayer mp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dislist);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3983C0")));
		bar.setTitle(Html.fromHtml("<b>বিস্তারিত</b>"));
		
		mp=MediaPlayer.create(this, R.raw.disease_nomuna_pic);
	    mp.setLooping(false);
	    mp.start();
		t1=(TextView)findViewById(R.id.t1);
		t2=(TextView)findViewById(R.id.t2);
		t3=(TextView)findViewById(R.id.t3);
		t1.setOnClickListener(this);
		t2.setOnClickListener(this);
		t3.setOnClickListener(this);
		getkey=getIntent().getExtras().getString("s");
		back=(Button)findViewById(R.id.back);
		back.setOnClickListener(this);
		chobi_Vor();
	}

	

	private void chobi_Vor() {
		// TODO Auto-generated method stub
		
		if(getkey.equalsIgnoreCase("ধানপাতা"))
		{
			t1.setBackgroundResource(R.drawable.bacterialblight);
			t2.setBackgroundResource(R.drawable.yellowdwarf);
			t3.setBackgroundResource(R.drawable.leafstreak);
			dhanpat = new String[]{"ব্যাক্টেরিয়াজনিত পাতা পড়া","হলদে বামন রোগ","লালচে রেখা রোগ"};
			
		}
		else if(getkey.equalsIgnoreCase("পাটপাতা"))
		{
			t1.setBackgroundResource(R.drawable.chlorosis);
			t2.setBackgroundResource(R.drawable.powderymildew);
			t3.setBackgroundResource(R.drawable.paterbicharog);
			dhanpat = new String[]{"ক্লোরসিস","সাদা গুড়া পড়া রোগ","পাটের বিছা রোগ"};
			
		}
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mp.stop();
		String sendKey = " ";
		if(v.getId()==R.id.t1)
		{
			sendKey=dhanpat[0];
			Intent in=new Intent(DisList.this,DisDetails.class);
			in.putExtra("s",sendKey);
			startActivity(in);
		}
		else if(v.getId()==R.id.t2)
		{
			sendKey=dhanpat[1];
			Intent in=new Intent(DisList.this,DisDetails.class);
			in.putExtra("s",sendKey);
			startActivity(in);
		}
		else if(v.getId()==R.id.t3)
		{
			sendKey=dhanpat[2];
			Intent in=new Intent(DisList.this,DisDetails.class);
			in.putExtra("s",sendKey);
			startActivity(in);
		}
		else if(v.getId()==R.id.back)
		{
			Intent in=new Intent(DisList.this,DiseasesActivity.class);
			startActivity(in);
		}
		
		
	}

}
