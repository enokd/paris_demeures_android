/* 
 * @author enokd
 */
package com.jdi;

import java.util.StringTokenizer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class ShowActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.show);
		
		String urls = getIntent().getStringExtra(RssReaderActivity.OFFER_IMAGES);
		String prix = getIntent().getStringExtra(RssReaderActivity.OFFER_PRIX);
		String nb_pieces = getIntent().getStringExtra(RssReaderActivity.OFFER_NB_PIECES);
		String surface = getIntent().getStringExtra(RssReaderActivity.OFFER_SURFACE);
		String ville = getIntent().getStringExtra(RssReaderActivity.OFFER_VILLE);
		String cp = getIntent().getStringExtra(RssReaderActivity.OFFER_CP);
		String ref = getIntent().getStringExtra(RssReaderActivity.OFFER_REF);
		String title = prix +"-"+nb_pieces+"pieces-"+surface+"m2-"+ville+"-"+cp+"-"+ref;
    	TextView titleText = (TextView) findViewById(R.id.atitle);
    	titleText.setText(title);		
		CoverFlow coverFlow = (CoverFlow) findViewById(R.id.gallery2);
        coverFlow.setAdapter(new ImageAdapter(this, urls));
                
        coverFlow.setSpacing(-25);
        coverFlow.setSelection(0, true);
        coverFlow.setAnimationDuration(1000);
        	
        TextView showText = (TextView) findViewById(R.id.adesc);
        showText.setText(getIntent().getStringExtra(RssReaderActivity.OFFER_DESC));
        	
        ImageButton callButton = (ImageButton) findViewById(R.id.bappel);
        callButton.setOnClickListener(new OnClickListener() {
			
        	public void onClick(View arg0) {
        		TextView phoneText = (TextView) findViewById(R.id.agencetel); 
				Intent callIntent = new Intent(Intent.ACTION_DIAL,Uri.parse(phoneText.getText().toString()));
				startActivity(callIntent);
			}
		});
        
        ImageButton mailButton = (ImageButton) findViewById(R.id.bmail);
        mailButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				Intent mailIntent = new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:paris@paris-demeures.com"));
				startActivity(mailIntent);
			}
		});
        
        ImageButton mailAmiButton = (ImageButton) findViewById(R.id.benvoi_ami);
        mailAmiButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				Intent mailIntent = new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:"));
				startActivity(mailIntent);
			}
		});
	}
	
	 public class ImageAdapter extends BaseAdapter {
	        int mGalleryItemBackground;
	        private Context mContext;
	        private int mCount;

	        private ImageView[] mImages;
	        
	        public ImageAdapter(Context c, String urls) {
	         mContext = c;
	         StringTokenizer st = new StringTokenizer(urls,",");
	         mCount = st.countTokens();
	         Log.w("ShowActivity", "retrieved bitmaps from url = " + mCount);
	         mImages = new ImageView[mCount];
	         int index = 0;
	         	while (st.hasMoreTokens()) {
	         		ImageView imageView = new ImageView(mContext);
	        		ImageDownloader showDownloader = new ImageDownloader();
	        		String curUrl=st.nextToken();
	   	            Log.w("ShowActivity", "retrieving bitmap from url = " + curUrl);
	        		showDownloader.download(curUrl, imageView);
	        		mImages[index++]=imageView;
	         	}
		         Log.w("ShowActivity", "retrieving bitmaps out = " + st.hasMoreTokens());
	        }
	        
	        public boolean createReflectedImages() {
	        	//The gap we want between the reflection and the original image
	        	final int reflectionGap = 4;
	        	
	             
	        	int index = 0;
	        	while (index < mImages.length) {
	        		ImageView imageView = mImages[index];
	        		Bitmap originalImage = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
	        		int width = originalImage.getWidth();
	        		int height = originalImage.getHeight();
	        		
	        		
	        		//This will not scale but will flip on the Y axis
	        		Matrix matrix = new Matrix();
	        		matrix.preScale(1, -1);
	        		
	        		//Create a Bitmap with the flip matrix applied to it.
	        		//We only want the bottom half of the image
	        		Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0, height/2, width, height/2, matrix, false);
	        		
	        		
	        		//Create a new bitmap with same width but taller to fit reflection
	        		Bitmap bitmapWithReflection = Bitmap.createBitmap(width 
	        				, (height + height/2), Config.ARGB_8888);
	        		
	        		//Create a new Canvas with the bitmap that's big enough for
	        		//the image plus gap plus reflection
	        		Canvas canvas = new Canvas(bitmapWithReflection);
	        		//Draw in the original image
	        		canvas.drawBitmap(originalImage, 0, 0, null);
	        		//Draw in the gap
	        		Paint deafaultPaint = new Paint();
	        		canvas.drawRect(0, height, width, height + reflectionGap, deafaultPaint);
	        		//Draw in the reflection
	        		canvas.drawBitmap(reflectionImage,0, height + reflectionGap, null);
	        		
	        		//Create a shader that is a linear gradient that covers the reflection
	        		Paint paint = new Paint(); 
	        		LinearGradient shader = new LinearGradient(0, originalImage.getHeight(), 0, 
	        				bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff, 0x00ffffff, 
	        				TileMode.CLAMP); 
	        		//Set the paint to use this shader (linear gradient)
	        		paint.setShader(shader); 
	        		//Set the Transfer mode to be porter duff and destination in
	        		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
	        		//	             paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN)); 
	        		//Draw a rectangle using the paint with our linear gradient
	        		canvas.drawRect(0, height, width, 
	        				bitmapWithReflection.getHeight() + reflectionGap, paint); 
	        		
	        		imageView.setImageBitmap(bitmapWithReflection);
	        		imageView.setLayoutParams(new CoverFlow.LayoutParams(120, 180));
	        		imageView.setScaleType(ScaleType.MATRIX);
	        		mImages[index++] = imageView;
	        	}	
	        	return true;
	        }	

	        	public int getCount() {
	        		return mCount;
	        	}	

	        	public Object getItem(int position) {
	        		return position;
	        	}

	        	public long getItemId(int position) {
	        		return position;
	        	}

	        	public View getView(int position, View convertView, ViewGroup parent) {

	        		//Use this code if you want to load from resources
	        		Log.w("ShowActivity", "retrieving bitmap from " + position);
	        		ImageView i = mImages[position];
	        		//i.setImageResource(mImageIds[position]);
	        		i.setLayoutParams(new CoverFlow.LayoutParams(130, 130));
	        		i.setScaleType(ImageView.ScaleType.CENTER_INSIDE); 
	        		//Make sure we set anti-aliasing otherwise we get jaggies
	        		BitmapDrawable drawable = (BitmapDrawable) i.getDrawable();
	        		drawable.setAntiAlias(true);
	        		return i;
	        		
	        		//return mImages[position];
	        	}
	        	/** Returns the size (0.0f to 1.0f) of the views 
	        	 * depending on the 'offset' to the center. */ 
	        	public float getScale(boolean focused, int offset) { 
	        		/* Formula: 1 / (2 ^ offset) */ 
	        		return Math.max(0, 1.0f / (float)Math.pow(2, Math.abs(offset))); 
	        	} 

	 } 
	 
}
