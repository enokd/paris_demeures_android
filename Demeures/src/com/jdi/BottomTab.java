/* 
 * @author enokd
 */
package com.jdi;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class BottomTab extends TabActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bottom_tab);

		TabHost myTabs = getTabHost();
		Resources res = getResources();
		TabSpec spec;
		Intent intent;
		
		//accueil
		intent = new Intent().setClass(this, DemeuresActivity.class);
		spec = myTabs.newTabSpec("Accueil").setIndicator("Accueil",
				res.getDrawable(R.drawable.accueil)).setContent(intent);
		myTabs.addTab(spec);

		//favoris
		intent = new Intent().setClass(this, FavorisActivity.class);
		spec = myTabs.newTabSpec("Favoris").setIndicator("Favoris",
				res.getDrawable(R.drawable.favoris)).setContent(intent);
		myTabs.addTab(spec);

		//agence
		intent = new Intent().setClass(this, AgenceActivity.class);
		spec = myTabs.newTabSpec("L'agence").setIndicator("L'agence",
				res.getDrawable(R.drawable.lagence)).setContent(intent);
		myTabs.addTab(spec);

		//contact
		intent = new Intent().setClass(this, ContactActivity.class);
		spec = myTabs.newTabSpec("Contact").setIndicator("Contact",
				res.getDrawable(R.drawable.contact)).setContent(intent);
		myTabs.addTab(spec);
		myTabs.setCurrentTabByTag("Accueil");
		
	}
}
