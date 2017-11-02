package com.dovana.farmaid;

import java.io.FileNotFoundException;

import android.R.color;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class TakePhoto extends Activity{

	 Button btnLoadImage,submit;
	 TextView textSource, textInfo,t3, text;
	 ImageView imageResult, imageDrawingPane;
	 Boolean touched;
	 final int RQS_IMAGE1 = 1;
	 Uri source;
	 Paint myPaint;
	 Bitmap bitmapMaster;
	 Canvas canvasDrawingPane;
	 Canvas canvasMaster;
	 String hexColor, colName;
	 Bitmap bitmapDrawingPane;
	 String croPart;
	 String send_disName=" ";
	 MediaPlayer mp;
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_take_photo);
	  croPart=getIntent().getExtras().getString("s");
	  mp=MediaPlayer.create(this, R.raw.disease_pic_load_result);
     mp.setLooping(false);
     mp.start();
	  btnLoadImage = (Button)findViewById(R.id.loadimage);
	  textSource = (TextView)findViewById(R.id.sourceuri);
	  t3=(TextView)findViewById(R.id.tcolor);
	  text=(TextView)findViewById(R.id.textView2);
	  imageResult = (ImageView)findViewById(R.id.result);
	  submit=(Button) findViewById(R.id.submit);
	  
	  submit.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			process();
		}
	});
	  
	
	  
	  btnLoadImage.setOnClickListener(new OnClickListener(){

	   @Override
	   public void onClick(View arg0) {
		   mp.stop();
	    Intent intent = new Intent(Intent.ACTION_PICK,
	      android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	    startActivityForResult(intent, RQS_IMAGE1);
	   }});
	  
	  imageResult.setOnTouchListener(new OnTouchListener(){

	   @Override
	   public boolean onTouch(View v, MotionEvent event) {
	    
	    int action = event.getAction();
	    int x = (int) event.getX();
	    int y = (int) event.getY();        
	    
	    switch(action){
	    case MotionEvent.ACTION_DOWN:
	        //textSource.setText("ACTION_DOWN- " + x + " : " + y);
	   
	     break;
	    case MotionEvent.ACTION_MOVE:
	     //textSource.setText("ACTION_MOVE- " + x + " : " + y);
	     t3.setBackgroundColor(
	       getProjectedColor((ImageView)v, bitmapMaster, x, y));
	     text.setText("নির্বাচিত রঙঃ");
	    
	     break;
	    case MotionEvent.ACTION_UP:
	     //textSource.setText("ACTION_UP- " + x + " : " + y);
	    	t3.setBackgroundColor(
	       getProjectedColor((ImageView)v, bitmapMaster, x, y));
	    	text.setText("নির্বাচিত রঙঃ ");
	     break;
	    }
	    /*
	     * Return 'true' to indicate that the event have been consumed.* If auto-generated 'false', your code can detect ACTION_DOWN only,* cannot detect ACTION_MOVE and ACTION_UP.
	     */
	    return true;
	   }});
	 }
	 
	 /*
	  * Project position on ImageView to position on Bitmap
	  * return the color on the position 
	  */
	 private int getProjectedColor(ImageView iv, Bitmap bm, int x, int y){
	  if(x<0 || y<0 || x > iv.getWidth() || y > iv.getHeight()){
	   //outside ImageView
	   return color.background_light; 
	  }else{
	   int projectedX = (int)((double)x * ((double)bm.getWidth()/(double)iv.getWidth()));
	   int projectedY = (int)((double)y * ((double)bm.getHeight()/(double)iv.getHeight()));

	   //textSource.setText(x + ":" + y + "/" + iv.getWidth() + " : " + iv.getHeight() + "\n" +projectedX + " : " + projectedY + "/" + bm.getWidth() + " : " + bm.getHeight());
	   
	     return bm.getPixel(projectedX, projectedY);
	  }
	 }

	 @Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  if(resultCode == RESULT_OK){
	   switch (requestCode){
	   case RQS_IMAGE1:
	    source = data.getData();
	    //textSource.setText(source.toString());
	    
	    try {
	     bitmapMaster = BitmapFactory.decodeStream(
	       getContentResolver().openInputStream(source));
	     imageResult.setImageBitmap(bitmapMaster);
	    } catch (FileNotFoundException e) {
	     // TODO Auto-generated catch block
	     e.printStackTrace();
	    }
	    break;
	    }
	  }
	 }


	 @SuppressLint("NewApi")
	public void process(){
		 ColorDrawable buttonColor = (ColorDrawable) t3.getBackground();
	     int colorId = buttonColor.getColor();
	     hexColor = String.format("#%06X", (0xFFFFFF & colorId));
	     //submit.setBackgroundColor(colorId);
	     colorName();
	 }

	 
	 /*-------------------------------------------*/
	 
	 
	 public void colorName()
	 {		
		     String redNum=hexColor.substring(1, 3);
			 String greenNum=hexColor.substring(3, 5);
			 String blueNum=hexColor.substring(5, 7);
			 int redint=Integer.parseInt(redNum,16);
			 int blueint=Integer.parseInt(blueNum,16);
			 int greenint=Integer.parseInt(greenNum,16);
			 double red=redint+1.1;
			 double blue=blueint+1.1;
			 double green=greenint+1.1;
			 
			 if(red<51 && green<51 && blue<51)
			 {colName="Black";}
			 else if(red>200 && blue>200 && green>200)
			 {colName="White";}
			 else if(green>135 &&(green/blue<1.15 && green/blue>0.9) && (green/red<1.15 && green/red>0.99))
			 {colName="WhiteGreen";}
			 else if((red/blue>1.9 || green/blue>1.9) && (red/green<=1.5)&&(red/green>=0.95))
			 {colName="Yellow";}
			 else if((red/blue>1.9 || green/blue>1.9) && (red/green>1.5 && red/green<=2.8))
			 {colName="Orange";}
			 else if((green/blue>1.22 || green/blue<1.45) && (red/green>0.92 && red/green<=1.3))
			 {colName="RedYellow";}
			 else if((red/green>4 && red/blue>=12 && red<169) ||(red/green>3.5 && green/blue>4 && red<=100))
			 {colName="Brown";}
			 else if((red/green>0.7 && red/green<0.9) && (green/blue>2.55 && green/blue<3.2))
			 {colName="Lemon";}
			 else if(red/blue>1.45 && red/green>1.45)
			 {colName="Red";}
			 else if(green/blue>1.45 && green/red>1.45)
			 {colName="Green";} 
			 else if(blue/green>1.45 && blue/red>1.45)
			 {colName="Blue";}
			 else{colName="none";}
			 detectDisease();
	 }

	private void detectDisease() {
		
		// TODO Auto-generated method stub
		if((colName.equalsIgnoreCase("yellow") || colName.equalsIgnoreCase("orange") || colName.equalsIgnoreCase("redyellow")) && croPart.equalsIgnoreCase("ধানপাতা"))
		{
			send_disName="লালচে রেখা রোগ";
		}
		else if((colName.equalsIgnoreCase("yellow") || colName.equalsIgnoreCase("orange") || colName.equalsIgnoreCase("redyellow")) && croPart.equalsIgnoreCase("ধানবীজ/শীষ"))
		{
			send_disName="ব্লাস্ট রোগ";
		}
		else if((colName.equalsIgnoreCase("yellow") || colName.equalsIgnoreCase("lemon") || colName.equalsIgnoreCase("redyellow")) && croPart.equalsIgnoreCase("পাটপাতা"))
		{
			send_disName="ক্লোরসিস";
		}
		else if((colName.equalsIgnoreCase("white") || colName.equalsIgnoreCase("whitegreen")) && croPart.equalsIgnoreCase("পাটপাতা"))
		{
			send_disName="সাদা গুড়া পড়া রোগ";
		}
		
		Intent in=new Intent(TakePhoto.this,DisDetails.class);
		in.putExtra("s",send_disName);
		startActivity(in);
	}
	
	/*-------------------------------------------------------------------*/
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mp.stop();
	}
	
	}
  
