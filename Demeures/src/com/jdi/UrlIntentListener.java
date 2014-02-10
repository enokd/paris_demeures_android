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


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * A listener which expects a URL as a tag of the view it is associated with. It then opens the URL
 * in the browser application.
 */
public class UrlIntentListener implements OnItemClickListener {

	public UrlIntentListener() {
		
	}
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final String urls = view.getTag().toString();
        final Context context = parent.getContext();
        final Intent intent = new Intent(context, ShowActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(RssReaderActivity.OFFER_IMAGES, urls);
        ViewGroup offerView = (ViewGroup) view;        
        TextView prixText = (TextView) offerView.getChildAt(RssReaderActivity.OFFER_PRIX_POS);
        TextView villeText = (TextView) offerView.getChildAt(RssReaderActivity.OFFER_VILLE_POS);
        TextView cpText = (TextView) offerView.getChildAt(RssReaderActivity.OFFER_CP_POS);
        TextView surfaceText = (TextView) offerView.getChildAt(RssReaderActivity.OFFER_SURFACE_POS);
        TextView nbPiecesText = (TextView) offerView.getChildAt(RssReaderActivity.OFFER_NB_PIECES_POS);
        TextView refText = (TextView) offerView.getChildAt(RssReaderActivity.OFFER_REF_POS);


        /*String offerTitle = prixText.getText().toString().concat("-").concat(villeText.getText().toString());
        prixText.setText(offerTitle);*/
        TextView descText = (TextView) offerView.getChildAt(RssReaderActivity.OFFER_DESC_POS);      
        intent.putExtra(RssReaderActivity.OFFER_DESC, descText.getText());
        intent.putExtra(RssReaderActivity.OFFER_PRIX, prixText.getText());
        intent.putExtra(RssReaderActivity.OFFER_VILLE, villeText.getText());
        intent.putExtra(RssReaderActivity.OFFER_CP, cpText.getText());
        intent.putExtra(RssReaderActivity.OFFER_SURFACE, surfaceText.getText());
        intent.putExtra(RssReaderActivity.OFFER_NB_PIECES, nbPiecesText.getText());
        intent.putExtra(RssReaderActivity.OFFER_REF, refText.getText());

        ProgressDialog dialog = ProgressDialog.show(context, "", 
                "Chargement des données...", true, true);
        context.startActivity(intent);
    }	
}	
	