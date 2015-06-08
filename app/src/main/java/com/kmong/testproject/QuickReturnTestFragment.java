package com.kmong.testproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.etiennelawlor.quickreturn.library.enums.QuickReturnAnimationType;
import com.etiennelawlor.quickreturn.library.enums.QuickReturnViewType;
import com.etiennelawlor.quickreturn.library.listeners.QuickReturnListViewOnScrollListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GON on 2015-03-24.
 */
public class QuickReturnTestFragment extends Fragment implements View.OnClickListener, AbsListView.OnScrollListener {

    private ListView mQuickListView;
    private TextView mQuickHeader, mQuickFooter;
    private QuickReturnAnimationType mQuickReturnAnimationType;
    private int headerHeight;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_quickreturn, container, false);
        headerHeight = getActivity().getResources().getDimensionPixelSize(R.dimen.header_height2);

        mQuickHeader = (TextView)rootView.findViewById(R.id.quick_return_tv);
        mQuickFooter = (TextView)rootView.findViewById(R.id.quick_return_tv2);
        mQuickListView = (ListView)rootView.findViewById(R.id.quickreturn_ListView);

        MultiScrollListener scroll = new MultiScrollListener();

        List<String> stringArray = new ArrayList<String>();

        for (int i = 1; i <= 100; i++ ){

            stringArray.add("test" + i);
        }


        QuickReturnListViewOnScrollListener scrollListener = new QuickReturnListViewOnScrollListener.Builder(QuickReturnViewType.HEADER)
                .header(mQuickHeader)
                .minHeaderTranslation(-headerHeight)
                .isSnappable(false)
                .build();

        QuickReturnListViewOnScrollListener scrollListener2 = new QuickReturnListViewOnScrollListener.Builder(QuickReturnViewType.FOOTER)
                .footer(mQuickFooter)
                .minFooterTranslation(+headerHeight)
                .isSnappable(true)
                .build();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, stringArray);
        mQuickListView.setAdapter(adapter);

        scroll.addScrollListener(scrollListener);
        scroll.addScrollListener(this);
        scroll.addScrollListener(scrollListener2);

        mQuickListView.setOnScrollListener(scroll);
        mQuickHeader.setOnClickListener(this);


        return rootView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.quick_return_tv :
                Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();
                break;

        }

    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        if(scrollState == SCROLL_STATE_IDLE){
            Toast.makeText(getActivity(), "SCROLL_STATE_IDLE", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
