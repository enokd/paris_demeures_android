/* 
 * @author enokd
 */
package com.jdi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class AgenceActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.agence);
	
		ImageButton engage = (ImageButton)  findViewById(R.id.engage);
		engage.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
					launchEng();
				}
		});
		
		ImageButton presta = (ImageButton)  findViewById(R.id.presta);
		presta.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
					launchPresta();
				}
		});
		
		ImageButton histo = (ImageButton)  findViewById(R.id.histo);
		histo.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
					launchHist();
				}
		});
		
		ImageButton collab = (ImageButton)  findViewById(R.id.collabo);
		collab.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
					launchCollab();
				}
		});
	}
	
	void launchPresta(){
		Intent i = new Intent(this, Presta.class);
		startActivity(i);
	}
	
	void launchCollab(){
		Intent i = new Intent(this, Collab.class);
		startActivity(i);
	}
	
	void launchHist(){
		Intent i = new Intent(this, Histo.class);
		startActivity(i);
	}
	
	void launchEng(){
		Intent i = new Intent(this, Engage.class);
		startActivity(i);
	}
}
