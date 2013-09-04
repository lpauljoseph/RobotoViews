package com.pjcorp.robotoview.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.pjcorp.robotoview.utils.TypefaceManager;

/**
 * Re write DigitalClock class integrating Roboto font
 */
public class DigitalClock extends android.widget.DigitalClock {

    public DigitalClock(Context context) {
        super(context);
    }

    public DigitalClock(Context context, AttributeSet attrs) {
        super(context, attrs);
        onInitTypeface(context, attrs, 0);
    }

    protected void onInitTypeface(Context context, AttributeSet attrs, int defStyle){
        if(attrs != null){
            Typeface typeface = TypefaceManager.getTypeface(context, attrs, defStyle);
            if(typeface != null){
                setTypeface(typeface, 0);
            }
        }
    }
}
