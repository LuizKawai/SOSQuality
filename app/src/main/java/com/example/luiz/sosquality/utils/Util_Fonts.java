package com.example.luiz.sosquality.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by luiz on 11/22/2016.
 */

public class Util_Fonts {
    private final static String CANDARA = "fonts/candara.ttf";
    private final static String CANDARA_BOLD = "fonts/candarab.ttf";


    public static Typeface setFontBold(Context paramContext) {
        return Typeface.createFromAsset(paramContext.getAssets(), CANDARA_BOLD);
    }

    public static Typeface setFontRegular(Context paramContext) {
        return Typeface.createFromAsset(paramContext.getAssets(), CANDARA);
    }

    public static Typeface setFontCursivaLight(Context paramContext) {
        return Typeface.createFromAsset(paramContext.getAssets(), "fonts/helveticalightoblique.otf");
    }

    public static Typeface setFontLight(Context paramContext) {
        return Typeface.createFromAsset(paramContext.getAssets(), "fonts/helveticalight.otf");
    }

    public static Typeface setFontNormal(Context paramContext) {
        return Typeface.createFromAsset(paramContext.getAssets(), "fonts/helvetica.otf");
    }
}
