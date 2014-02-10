/* 
 * @author enokd
 */
package com.jdi;

import android.R.anim;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ContactActivity extends Activity {


	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact);
    
		TextView geoloc = (TextView) findViewById(R.id.geoloc);
		geoloc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				launchMap();
			}
		});	
		
		
		TextView appel = (TextView)  findViewById(R.id.contact_appel);
		appel.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				Intent callIntent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:0156434141"));
				startActivity(callIntent);
				}
		});	
		
		TextView mail = (TextView) findViewById(R.id.contact_email);
		mail.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				Intent mailIntent = new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:paris@paris-demeures.com"));
				startActivity(mailIntent);
				}
		});
	};
	

	void launchMap(){
		Intent i = new Intent(this, AgenceMaps.class);
		startActivity(i);
	}
}
