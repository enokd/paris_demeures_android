/* 
 * @author enokd
 */
package com.jdi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class ResultActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		
		 LinearLayout showLayout = (LinearLayout) findViewById(R.id.bdhlayout1);
	     showLayout.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					launchShow();
					
				}
			});
	     
	     LinearLayout showLayout1 = (LinearLayout) findViewById(R.id.bdhlayout2);
	     showLayout1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					launchShow();
					
				}
			});
	     
	     LinearLayout showLayout2 = (LinearLayout) findViewById(R.id.bdhlayout3);
	     showLayout2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					launchShow();
					
				}
			});
	}
	
	public void launchShow(){
    	Intent i = new Intent(this, ShowActivity.class);
    	startActivity(i);
    }

}
