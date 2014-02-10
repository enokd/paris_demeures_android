/* 
 * @author enokd
 */
package com.jdi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AccueilActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rmulti);
		
		 Button searchButton = (Button) findViewById(R.id.bsearch);
	        searchButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					launchResultActivity();
					
				}
			});
	}
	/* Show activity */
	public void launchResultActivity(){
    	Intent i = new Intent(this, RssReaderActivity.class);
    	startActivity(i);
    }

}
