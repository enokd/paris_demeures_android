/* 
 * @author enokd
 */
package com.jdi;

import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CarteActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carte);
        
        /*InputStream is = getResources().openRawResource(R.drawable.badge);
        Bitmap badge = BitmapFactory.decodeStream(is);
        Canvas c = new Canvas(badge);*/
    }
}
