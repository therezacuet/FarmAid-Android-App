package com.dovana.farmaid;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class DiseasesActivity extends Activity implements OnClickListener,OnItemSelectedListener{
	String part=" ",crop=" ";
	Button take_photo;
	private Spinner spinner1,spinner2;
	MediaPlayer mcrop, mpart;
	TextView tcrop,tpart;
	private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
	public static final int MEDIA_TYPE_IMAGE = 1;
	private Uri fileUri;
	int i=0;
	// directory name to store captured images and videos
	private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diseases);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3983C0")));
		bar.setTitle(Html.fromHtml("<b>রোগ শনাক্তকরণ</b>"));
		
		take_photo=(Button)findViewById(R.id.take_photo);
		take_photo.setOnClickListener(this);

		spinner1 = (Spinner)findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(this);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(this);
        mpart=MediaPlayer.create(this, R.raw.disease_fosoler_part);
        mcrop=MediaPlayer.create(this, R.raw.disease_fosol);
        mcrop.setLooping(false);
        mpart.setLooping(false);
        tcrop=(TextView)findViewById(R.id.tcrop);
        tpart=(TextView)findViewById(R.id.tpart);
        tcrop.setTextColor(Color.RED);
        tcrop.setCompoundDrawablesWithIntrinsicBounds(R.drawable.arrow,0,0,0);
        mcrop.start();
		createCropSet();
		createPartSet();
		if (!isDeviceSupportCamera()) {
			Toast.makeText(getApplicationContext(),"Sorry! Your device doesn't support camera",Toast.LENGTH_LONG).show();
			// will close the app if the device does't have camera
			finish();
		}
		
	}

	private void createPartSet() {
		// TODO Auto-generated method stub
		String[] part = new String[]{"রোগের অংশ চিহ্নিত করুন","পাতা","কান্ড","বীজ/শীষ"};
    	
    	ArrayAdapter<String> partadapter = new ArrayAdapter<String>(DiseasesActivity.this,android.R.layout.simple_spinner_item, part);
    	partadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	partadapter.notifyDataSetChanged();
            spinner2.setAdapter(partadapter);
	}

	private void createCropSet() {
		// TODO Auto-generated method stub
		
			String[] crop = new String[]{"শষ্য নির্বাচন করুন","ধান","পাট","গম","তামাক","আলু"};
	    	
	    	ArrayAdapter<String> cropadapter = new ArrayAdapter<String>(DiseasesActivity.this,android.R.layout.simple_spinner_item, crop);
	    	cropadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    	cropadapter.notifyDataSetChanged();
	            spinner1.setAdapter(cropadapter);
		}
	

	

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	   if(v.getId()==R.id.take_photo){
		   if(!crop.equals("শষ্য নির্বাচন করুন") && !part.equals("রোগের অংশ চিহ্নিত করুন")){
			   captureImage();
			  
		   }
		   else{
			   Toast.makeText(getApplicationContext(), "উপরের তথ্যগুলো পূরণ করা বাধ্যতামূলক", Toast.LENGTH_SHORT).show();
		   }
	   }
	   
	}

	
	@Override
	public void onItemSelected(AdapterView<?> arg0, View a, int arg2,
			long arg3) {
			// TODO Auto-generated method stub
		crop= String.valueOf(spinner1.getSelectedItem());
		part= String.valueOf(spinner2.getSelectedItem());
		if(!crop.equals("শষ্য নির্বাচন করুন") && part.equals("রোগের অংশ চিহ্নিত করুন")){
			mcrop.stop();
			tcrop.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
			tcrop.setTextColor(Color.BLACK);
			tpart.setTextColor(Color.RED);
			tpart.setCompoundDrawablesWithIntrinsicBounds(R.drawable.arrow,0,0,0);
			mpart.start();
		}
		else if(!crop.equals("শষ্য নির্বাচন করুন") && !part.equals("রোগের অংশ চিহ্নিত করুন"))
		{
			mpart.stop();
			tpart.setTextColor(Color.BLACK);
			tpart.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
		}
		
		}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	/******************************************************************************/
	private boolean isDeviceSupportCamera() {
		if (getApplicationContext().getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_CAMERA)) {
			// this device has a camera
			return true;
		} else {// no camera on this device
			return false;
		}
	}
	private void captureImage() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

		intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

		// start the image capture Intent
		startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
	}

	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		// save file url in bundle as it will be null on scren orientation
		// changes
		outState.putParcelable("file_uri", fileUri);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		// get the file url
		fileUri = savedInstanceState.getParcelable("file_uri");
	}


	/**
	 * Receiving activity result method will be called after closing the camera
	 * */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// if the result is capturing Image
		if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				i=100;
				String send=crop+part;
				   if(!crop.equals("শষ্য নির্বাচন করুন")&& !part.equals("রোগের অংশ চিহ্নিত করুন") && i==100)
				   {Intent in=new Intent(DiseasesActivity.this,TakePhoto.class);
				   in.putExtra("s", send);
				   startActivity(in);
				   i=0;}
				   else{
					   Toast.makeText(getApplicationContext(), "উপরের তথ্যগুলো পূরণ করা বাধ্যতামূলক", Toast.LENGTH_SHORT).show();
				   }
				// successfully captured the image
				// display it in image view
			} else if (resultCode == RESULT_CANCELED) {
				// user cancelled Image capture
				Toast.makeText(getApplicationContext(),
						"User cancelled image capture", Toast.LENGTH_SHORT)
						.show();
			} else {
				// failed to capture image
				Toast.makeText(getApplicationContext(),
						"Sorry! Failed to capture image", Toast.LENGTH_SHORT)
						.show();
			}
		} 
	}
	public Uri getOutputMediaFileUri(int type) {
		return Uri.fromFile(getOutputMediaFile(type));
	}

	/*
	 * returning image / video
	 */
	private static File getOutputMediaFile(int type) {

		// External sdcard location
		File mediaStorageDir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				IMAGE_DIRECTORY_NAME);

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
						+ IMAGE_DIRECTORY_NAME + " directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
				Locale.getDefault()).format(new Date(type));
		File mediaFile;
		if (type == MEDIA_TYPE_IMAGE) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "IMG_" + timeStamp + ".jpg");
			
		} else {
			return null;
		}

		return mediaFile;
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mpart.stop();
	}

}
