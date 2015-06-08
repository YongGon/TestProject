package com.kmong.testproject;

import android.graphics.Typeface;
import android.widget.TextView;

import java.util.HashMap;
//import com.google.

/**
 * Created by GON on 2015-03-24.
 */
public class TypefaceUtil {

    // region Static Variables
    private static HashMap<TypefaceId, Typeface> sTypefaceCache ; //Maps.newHashMap();
    // endregion

    public static void apply(TypefaceId id, TextView tv) {
        if (tv == null || tv.isInEditMode()) {
            return;
        }
        tv.setTypeface(getTypeface(id));
    }

    public static Typeface getTypeface(TypefaceId id) {
        Typeface typeface = sTypefaceCache.get(id);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(TestProjectApplication.get().getAssets(), id.getFilePath());
            sTypefaceCache.put(id, typeface);
        }
        return typeface;
    }

    // region Interfaces
    public static interface TypefaceId {
        public Typeface get();

        public String getFilePath();
    }
    // endregion
}
