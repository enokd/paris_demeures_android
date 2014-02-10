package com.jdi;
/* 
 * @author enokd
 */
import java.text.NumberFormat;
import java.util.Locale;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.TextView;


public class CurrencyBinder extends Adapters.CursorBinder {

        public CurrencyBinder(Context context, Adapters.CursorTransformation transformation) {
            super(context, transformation);
        }

        @Override
        public boolean bind(View view, Cursor cursor, int columnIndex) {
            if (view instanceof TextView) {
                final String text = mTransformation.transform(cursor, columnIndex);
                NumberFormat formatter= NumberFormat.getInstance(Locale.FRANCE);
                String textFormatted = formatter.format((double) Integer.parseInt(text))
                		.concat(formatter.getCurrency().getSymbol());
                ((TextView) view).setText(textFormatted);
                return true;
            }
            return false;
        }


}
