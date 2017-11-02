package com.dovana.farmaid;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisDetails extends Activity implements OnClickListener{
TextView text;
String getkey,send_key;
SQLiteDatabase db;
String table="dislisttable";
Button back,call,sample;
MediaPlayer mp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dis_details);
		text=(TextView)findViewById(R.id.tv1);
		getkey=getIntent().getExtras().getString("s");
		mp=MediaPlayer.create(this, R.raw.disease_result_show);
	    mp.setLooping(false);
	    mp.start();
		back=(Button)findViewById(R.id.back);
		call=(Button)findViewById(R.id.call);
		sample=(Button)findViewById(R.id.sample);
		back.setOnClickListener(this);
		sample.setOnClickListener(this);
		call.setOnClickListener(this);
		createTable();
		addstat();
		showit();
	}

	private void addstat() {
		// TODO Auto-generated method stub
		db=openOrCreateDatabase("MYDB", MODE_PRIVATE, null);
		db.execSQL("DELETE FROM dislisttable");
		String stemrotp="INSERT INTO "+table+" VALUES('ধান','কান্ড','কান্ডপঁচা রোগ','এটি একটি ছত্রাকজনিত রোগ। পানির উপরিতলের কান্ড বরাবর এই রোগ দেখা যায়','নিয়মিত জমি শুকানো','ইমেজ নট ফাউন্ড');";
		db.execSQL(stemrotp);
		String blast="INSERT INTO "+table+" VALUES('ধান','বীজ/শীষ','ব্লাস্ট রোগ','এটি একটি ছত্রাকজনিত রোগ। এ রোগ কান্ডের গিঁটে আক্রমণ করে। এ রোগে কান্ড ধূসর হয়ে যায় এবং পড়ে কান্ডের  গিঁট পঁচে গিয়ে কালচে হয় এবং সহজেই ভেঙ্গে যায়।  অধিক মাত্রায় নাইট্রোজেন সার এবং বাতাসের আর্দ্রতা এ রোগের প্রকোপ বাড়ায়। এছাড়া রাতে ঠান্ডা, দিনে গরম ও সকালে শিশির পড়া এ রোগের প্রকোপ বাড়ায়। ','রোগমুক্ত জমি থেকে বীজ সংগ্রহ করতে হবে। সুষম মাত্রায় ইউরিয়া ব্যবহার করতে হবে। জমিতে সবসময় পানি রাখতে হবে। ','ইমেজ নট ফাউন্ড');";
		db.execSQL(blast);
		String leafstreak="INSERT INTO "+table+" VALUES('ধান','পাতা','লালচে রেখা রোগ','প্রথমে পাতার শিরাসমূহের মধ্যবর্তী স্থানে সরু ও হালকা দাগ পড়ে। সূর্যের দিকে ধরলে এ দাগের মধ্য দিয়ে আলো প্রবেশ করে এবং পরিস্কার দেখা যায়। দাগগুলো বড় হয়ে লালচে রং ধারণ করে এবং পাতার পার্শ্ববর্তী বৃহৎ শিরার দিকে ছড়াতে থাকে। আক্রমণ প্রবণ জাতে ধানের পাতা পুরোটাই লালচে রঙের হয়ে মরে যেতে পারে। রোগ বিস্তারের অনুকুল অবস্থায় সারা মাঠ হলদে কমলা রঙের হয়ে যায়। এ ব্যাকটেরিয়া গাছে ক্ষত বা পাতার কোষের স্বাভাবিক ছিদ্র পথে প্রবেশ করে। বৃষ্টি এবং বাতাস এ রোগ বিস্তারে সাহায্য করে।','আক্রান্ত জমি থেকে বীজ সংগ্রহ না করা। এক লিটার পানিতে ৩ গ্রাম ব্যাভিষ্টিন গুলে তাতে বীজ এক রাত রেখে শোধন করা। নাড়া শুকিয়ে জমিতেই পুড়িয়ে ফেলা।','ইমেজ নট ফাউন্ড');";
		db.execSQL(leafstreak);
		String bakanae="INSERT INTO "+table+" VALUES('ধান','বীজ/শীষ','গোঁড়াপঁচা রোগ','এটি মূলত বীজ বাহিত রোগ।চারাগুলো সাধারণ চারার দ্বিগুন হয়। পাতা হলদে সবুজ হয়।','আক্রান্ত গাছ পুড়িয়ে ফেলতে হবে। বীজতলা আর্দ্র রাখতে হবে। ব্যাভিস্টিন বা নোইন নামক ছত্রাকনাশক ব্যবহার করা যেতে পারে।','ইমেজ নট ফাউন্ড');";
		db.execSQL(bakanae);
		String bacterialblight="INSERT INTO "+table+" VALUES('ধান','পাতা','ব্যাক্টেরিয়াজনিত পাতা পড়া','ব্যাক্টেরিয়া জনিত রোগ','জমি শুকিয়ে নাড়া পুড়িয়ে ফেলা। আক্রান্ত ক্ষেতে নাইট্রোজেন সার ব্যবহার না করা','ইমেজ নট ফাউন্ড');";
		db.execSQL(bacterialblight);
		String tungro="INSERT INTO "+table+" VALUES('ধান','পাতা','টুংরো রোগ','সবুজ ঘাস ফড়িং-এর মাধ্যমে ছড়ায়। আক্রান্ত পাতা হলুদ হয়ে যায়। ধানের বৃদ্ধি হ্রাস পায়।','অনুমোদিত কীটনাশক দিয়ে বা আলোর ফাঁদ ব্যবহার করে সবুজ ফড়িং নিয়ন্ত্রণ করতে হবে।','ইমেজ নট ফাউন্ড');";
		db.execSQL(tungro);
		String yellowdwarf="INSERT INTO "+table+" VALUES('ধান','পাতা','হলদে বামন রোগ','সবুজ ঘাস ফড়িং-এর মাধ্যমে ছড়ায়। আক্রান্ত পাতা হলদে সবুজ হয়ে যায়। ধানের বৃদ্ধি মারাত্নক হ্রাস পায়। পাতা নরম হয়ে যায়।','অনুমোদিত কীটনাশক দিয়ে বা আলোর ফাঁদ ব্যবহার করে সবুজ ফড়িং নিয়ন্ত্রণ করতে হবে।','ইমেজ নট ফাউন্ড');";
		db.execSQL(yellowdwarf);
		String stemrotj="INSERT INTO "+table+" VALUES('পাট','কান্ড','কান্ডপঁচা রোগ','এটি একটি ছত্রাকজনিত রোগ। অঙ্কুরোদগম থেকে বৃদ্ধির শেষ সময় পর্যন্ত পাটগাছের যেকোন অবস্থায় এই রোগ দেখা যায়।','জমি জীবানুমুক্ত করতে হবে। ভিটাভেক্স নামক ছত্রাক নাশক ব্যবহার করা যেতে পারে।','ইমেজ নট ফাউন্ড');";
		db.execSQL(stemrotj);
		String chlorosis="INSERT INTO "+table+" VALUES('পাট','পাতা','ক্লোরসিস','এটি একটি ভাইরাসজনিত রোগ। চারা অবস্থায়ই গাছের পাতায় হলদে সবুজ রং-এর ছোপ দেখা যায়','এ রোগের চারা তুলে ফেলে দিলে এই রোগ আর ছড়াতে পাড়ে না। ডায়াজিনন ব্যবহার করে রোগের প্রকোপ কমানো যায়।','ইমেজ নট ফাউন্ড');";
		db.execSQL(chlorosis);
		String pmildow="INSERT INTO "+table+" VALUES('পাট','পাতা','সাদা গুড়া পড়া রোগ','এটি মূলত  বীজ, বায়ু ও মাটিবাহিত রোগ।  এই রোগে পাতা সাদা হয়ে যায়। আর্দ্র আবহাওয়ায় এই রোগ বেশি ছড়ায়','প্রতি লিটার পানিতে ৩.২ গ্রাম থিওভেট মিশিয়ে স্প্রে করা যেতে পারে','ইমেজ নট ফাউন্ড');";
		db.execSQL(pmildow);
		String patbicha="INSERT INTO "+table+" VALUES('পাট','পাতা','পাটের বিছা রোগ','এই রোগের পাটের পাতায় বিছার আক্রমণ হয়।  এই রোগে পাতা সাদা হয়ে যায়। আর্দ্র আবহাওয়ায় এই রোগ বেশি ছড়ায়','বিছা নিধনের জন্য কীটনাশক ব্যবহার  করা যেতে পারে','ইমেজ নট ফাউন্ড');";
		db.execSQL(patbicha);
		db.close();
	}

	private void createTable(){
		// TODO Auto-generated method stub
		db=openOrCreateDatabase("MYDB", MODE_PRIVATE, null);
		String create="CREATE TABLE IF NOT EXISTS "+table+" (crops VARCHAR, parts VARCHAR, dis_name VARCHAR, description VARCHAR, solution VARCHAR, image VARCHAR);";
		db.execSQL(create);
		db.close();
	}
	private void showit() {
		// TODO Auto-generated method stub
		db = openOrCreateDatabase("MYDB", MODE_PRIVATE, null);
		String retrieve = "SELECT * FROM "+table+";";
		Cursor cursor = db.rawQuery(retrieve, null);
		if(cursor.getCount()<1)
		{
			Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_LONG).show();
		} else
		{
			cursor.moveToFirst();
			do
			{
				String disease=cursor.getString(cursor.getColumnIndex("dis_name"));
				String desc = cursor.getString(cursor.getColumnIndex("description"));
				String sol=cursor.getString(cursor.getColumnIndex("solution"));
				String crop = cursor.getString(cursor.getColumnIndex("crops"));
				String part=cursor.getString(cursor.getColumnIndex("parts"));
				
				if(getkey.equals(disease)){
				text.setText("রোগের নামঃ "+disease+"\n\n"+"বিবরণঃ "+desc+"\n\nসমাধানঃ "+sol);
				send_key=crop+part;
				}
			} while(cursor.moveToNext());
			db.close();
		}
	}

	
	@Override
	public void onClick(View a) {
		// TODO Auto-generated method stub
		mp.stop();
		if(a.getId()==R.id.back)
		{
			Intent in=new Intent(DisDetails.this,TakePhoto.class);
			in.putExtra("s",send_key);
			startActivity(in);
		}
		else if(a.getId()==R.id.call)
		{
			Toast.makeText(getApplicationContext(), "কৃষি অফিসারকে কল করা হচ্ছে", Toast.LENGTH_LONG).show();
			Intent in=new Intent(Intent.ACTION_CALL,Uri.parse("tel:16123"));
			startActivity(in);
		}
		else if(a.getId()==R.id.sample)
		{
			Intent in=new Intent(DisDetails.this,DisList.class);
			in.putExtra("s",send_key);
			startActivity(in);
		}
	}

}
