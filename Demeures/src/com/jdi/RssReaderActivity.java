/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jdi;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;

/**
 * This example demonstrate the creation of a simple RSS feed reader using the XML adapter syntax.
 * The different elements of the feed are extracted using an {@link XmlDocumentProvider} and are
 * binded to the different views. An {@link OnItemClickListener} is also added, which will open a
 * browser on the associated news item page.
 */
public class RssReaderActivity extends ListActivity {
    private static final String FEED_URI = "http://paris-demeures.com/iphone.asp";
    public static final String OFFER_IMAGES = "offer_images";
    public static final String OFFER_DESC = "offer_desc";
    public static final String OFFER_PRIX = "offer_prix";
    public static final String OFFER_SURFACE = "offer_surface";
    public static final String OFFER_CP = "offer_cp";
    public static final String OFFER_VILLE = "offer_ville";
    public static final String OFFER_NB_PIECES = "offer_nb_pieces";
    public static final String OFFER_REF = "offer_ref";
    
    public static final int OFFER_VILLE_POS = 1;
    public static final int OFFER_PRIX_POS = 2;
    public static final int OFFER_NB_PIECES_POS = 3;
    public static final int OFFER_CP_POS = 4;    
    public static final int OFFER_SURFACE_POS = 5;
    public static final int OFFER_DESC_POS = 6;
    public static final int OFFER_REF_POS = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rss_feeds_list);
        setListAdapter(Adapters.loadCursorAdapter(this, R.xml.rss_feed,
                "content://xmldocument/?url=" + Uri.encode(FEED_URI)));
        final ListView listOffre = getListView();
        
        ProgressDialog dialog = ProgressDialog.show(listOffre.getContext(), "", 
                "Chargement des données...", true, true);
        ProgressBar mprogress = new ProgressBar(listOffre.getContext());
        mprogress.setScrollBarStyle(android.R.style.Widget_ProgressBar_Small);
        mprogress.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        listOffre.setOnItemClickListener(new UrlIntentListener());
    }
        /*class ProgressThread extends Thread {
        	Handler progressHandler;
        	
        	public ProgressThread(Handler h) {
        		progressHandler=h;
			}
        	
        	public void run(){
        		while(true){
        			try{
        				Thread.sleep(100);
        			}catch (InterruptedException e) {
						e.printStackTrace();
					}
        			Message m = progressHandler.obtainMessage();
        			m.arg1 = listOffre.getChildCount();
        			progressHandler.sendMessage(m);
        		}
        	};
      
        public final int PROGRESS_DIALOG = 0;
        public final ProgressThread progressThread;
        public final ProgressDialog progressDialog;

        public final Handler handler = new Handler() {
        	public void handleMessage(Message m){
        		int downloaded = m.arg1;
        		progressDialog.setProgress(downloaded);
        		if (downloaded >= 10) {
        			dismissDialog(PROGRESS_DIALOG);
        			progressThread.interrupt();
        		}
        	}
        };
        	protected Dialog onCreateDialog(int id) {
                switch(id) {
                case PROGRESS_DIALOG:
                    progressDialog = new ProgressDialog(RssReaderActivity.this);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setMessage("Loading...");
                    return progressDialog;
                default:
                    return null;
                }
            }
    }*/
}
