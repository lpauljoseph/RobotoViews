package com.pjcorp.robotoview.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.pjcorp.robotoview.utils.TypefaceManager;

/**
 * Re write CheckBox class integrating Roboto font
 */
public class CheckBox extends android.widget.CheckBox {

    public CheckBox(Context context) {
        super(context);
    }

    public CheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        onInitTypeface(context, attrs, 0);
    }

    public CheckBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        onInitTypeface(context, attrs, defStyle);
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
