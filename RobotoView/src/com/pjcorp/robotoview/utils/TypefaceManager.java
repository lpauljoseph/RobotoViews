package com.pjcorp.robotoview.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.SparseArray;

/**
 * Manager to create the typeface matching the couple style and family font
 */
public class TypefaceManager {

    private static final SparseArray<Typeface> TYPEFACE_SPARSE_ARRAY = new SparseArray<Typeface>();
    private static final int [] ATTRIBUTE_FONT_FAMILY = new int[]{android.R.attr.fontFamily};
    private static final int [] ATTRIBUTE_TEXT_STYLE = new int[]{android.R.attr.textStyle};
	private static final int [] ATTRIBUTE_TEXT_VIEW_STYLE = new int[]{android.R.attr.textViewStyle};

    /**
     * Getter for Typeface
     * @return
     */
    public static Typeface getTypeface(Context context, AttributeSet attrs, int defStyle)throws IllegalArgumentException {
        Typeface typeface = null;
		String fontFamilyString = null;

        TypedArray value = context.obtainStyledAttributes(attrs, ATTRIBUTE_FONT_FAMILY, defStyle, 0);
		if(value != null){
			fontFamilyString = value.getString(0);
			if(fontFamilyString == null){
				fontFamilyString = obtainFontFamilyFromTextViewStyle(context, attrs);
			}
			value.recycle();
		}
        MyTypeface myTypeface = MyTypeface.getTypeface(fontFamilyString);
        if(myTypeface != null){
            value = context.obtainStyledAttributes(attrs, ATTRIBUTE_TEXT_STYLE, defStyle, 0);
			int textStyle = 0;
			if(value != null){
				textStyle = value.getInt(0, 0);
				value.recycle();
			}
            myTypeface = MyTypeface.getTypeface(myTypeface, textStyle);
            typeface = TYPEFACE_SPARSE_ARRAY.get(myTypeface.mId);
            if (typeface == null) {
                typeface = createTypeface(context, myTypeface.mId);
                TYPEFACE_SPARSE_ARRAY.put(myTypeface.mId, typeface);
            }
        }
        return typeface;
    }

	private static String obtainFontFamilyFromTextViewStyle(Context context, AttributeSet attrs){
		TypedArray valueTextViewStyle = context.obtainStyledAttributes(attrs, ATTRIBUTE_TEXT_VIEW_STYLE);

		if(valueTextViewStyle != null){
			int styleId = valueTextViewStyle.getResourceId(0, -1);
			if(styleId != -1){
				TypedArray value = context.obtainStyledAttributes(styleId, ATTRIBUTE_FONT_FAMILY);
				if(value != null){
					return value.getString(0);
				}
			}
		}
		return null;
	}

    private static Typeface createTypeface(Context context, int typefaceValue){
        Typeface typeface = null;
        MyTypeface myTypeface = MyTypeface.getTypeface(typefaceValue);
        if(myTypeface != null){
            typeface = Typeface.createFromAsset(context.getAssets(), myTypeface.mPath);
        }
        return typeface;
    }

    public enum MyTypeface{
        REGULAR(0, "fonts/Roboto-Regular.ttf"),
        BOLD(1, "fonts/Roboto-Bold.ttf"),
        ITALIC(2, "fonts/Roboto-Italic.ttf"),
        BOLD_ITALIC(3, "fonts/Roboto-BoldItalic.ttf"),
        LIGHT(4, "fonts/Roboto-Light.ttf"),
        LIGHT_ITALIC(6, "fonts/Roboto-LightItalic.ttf"),
        MEDIUM(8, "fonts/Roboto-Medium.ttf"),
        MEDIUM_ITALIC(10, "fonts/Roboto-MediumItalic.ttf"),
        THIN(12, "fonts/Roboto-Thin.ttf"),
        THIN_ITALIC(14, "fonts/Roboto-ThinItalic.ttf"),
        BLACK(16, "fonts/Roboto-Black.ttf"),
        BLACK_ITALIC(18, "fonts/Roboto-BlackItalic.ttf"),
        CONDENSED(20, "fonts/Roboto-Condensed.ttf"),
        CONDENSED_BOLD(21, "fonts/Roboto-BoldCondensed.ttf"),
        CONDENSED_ITALIC(22, "fonts/Roboto-CondensedItalic.ttf"),
        CONDENSED_BOLD_ITALIC(23, "fonts/Roboto-BoldCondensedItalic.ttf");

        public int mId;
        public String mPath;

        MyTypeface(int id, String typefacePath) {
            mId = id;
            mPath = typefacePath;
        }

        public static MyTypeface getTypeface(int id){
            for(MyTypeface myTypeface : values()){
                if(myTypeface.mId == id){
                    return myTypeface;
                }
            }
            return null;
        }

        public static MyTypeface getTypeface(String typefaceString){
            if(typefaceString != null){
                for(MyTypeface myTypeface : values()){
                    if(myTypeface.name().toLowerCase().equals(typefaceString)){
                        return myTypeface;
                    }
                }
            }
            return REGULAR;
        }

        private static MyTypeface getTypeface(MyTypeface myTypeface, int textStyle){
            if(textStyle > 0){
                MyTypeface typeface = getTypeface(myTypeface.mId|textStyle);
                if(typeface != null){
                    return typeface;
                }
            }
            return myTypeface;
        }
    }
}
