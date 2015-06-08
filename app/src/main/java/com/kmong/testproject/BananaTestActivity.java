package com.kmong.testproject;

import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GON on 2015-05-11.
 */
public class BananaTestActivity extends ActionBarActivity {

    private final static int TYPE_LIST = 1;
    private final static int TYPE_GRID = 2;


    private RecyclerView mBananaRecyclerView;
    private BananaAdapter mBananaAdapter;
    private List<BananaItem> mBananaArray;
    private LinearLayoutManager mLayoutManager;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bananatest);

        init();
        dataInit();

    }


    private void init() {

        mBananaRecyclerView = (RecyclerView) findViewById(R.id.banana_RecyclerView);
    }

    private void dataInit() {

        mBananaArray = new ArrayList<BananaItem>();

        for (int i = 0; i < 30; i++) {

            BananaItem item = new BananaItem();
            item.setIcon(R.drawable.ic_launcher);
            item.setName("바나나 " + i);
            item.setCnt("개수 : " + i);

            mBananaArray.add(item);

        }


        mLayoutManager = new LinearLayoutManager(BananaTestActivity.this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mLayoutManager = new GridLayoutManager(BananaTestActivity.this, 3);
//        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//
//            @Override
//            public int getSpanSize(int position) {
//
//                switch (mBananaAdapter.getItemViewType(position)){
//
//                    case BananaTestActivity.TYPE_LIST :
//                        return 2;
//                    case BananaTestActivity.TYPE_GRID :
//                        return 3;
//                    default:
//                        return -1;
//
//
//                }
//            }
//        });


        mBananaAdapter = new BananaAdapter(mBananaArray);
        mBananaRecyclerView.setAdapter(mBananaAdapter);
        mBananaRecyclerView.setLayoutManager(mLayoutManager);
        mBananaRecyclerView.setItemAnimator(new DefaultItemAnimator());


    }
}
