package com.indushealthplus.android.indusbuddy.managers;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by amolr on 7/3/18.
 */

public class FontManager {

      public static final String ROOT = "fonts/",
            FONTAWESOME = ROOT + "fontawesome-webfont.ttf",
            ROBOTO_LIGHT = ROOT + "Roboto-Light.ttf";

    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }
}
