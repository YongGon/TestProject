package com.kmong.testproject;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by GON on 2015-05-11.
 */
public class CustomLayoutManager extends GridLayoutManager {


    public CustomLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }


}
