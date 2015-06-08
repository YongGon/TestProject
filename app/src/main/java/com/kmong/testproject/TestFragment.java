package com.kmong.testproject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexvasilkov.android.commons.texts.SpannableBuilder;
import com.alexvasilkov.android.commons.utils.Views;
import com.alexvasilkov.foldablelayout.UnfoldableView;
import com.alexvasilkov.foldablelayout.shading.GlanceFoldShading;
import com.squareup.picasso.Picasso;

/**
 * Created by GON on 2015-02-16.
 */
public class TestFragment extends Fragment implements MainActivity.OnFoldsendDataListener, AdapterView.OnItemClickListener {

    private ListView mListView;
    private View mListTouchInterceptor;
    protected View mDetailsLayout;
    private UnfoldableView mUnfoldableView;
    private BroadcastReceiver mReceiver;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_foldabletest, container, false);

        mListView = Views.find(v, R.id.list_view);
        mListView.setAdapter(new PaintingsAdapter(getActivity()));
        mListView.setOnItemClickListener(this);

        mListTouchInterceptor = Views.find(v, R.id.touch_interceptor_view);
        mListTouchInterceptor.setClickable(false);

        mDetailsLayout = Views.find(v, R.id.details_layout);
        mDetailsLayout.setVisibility(View.INVISIBLE);

        mUnfoldableView = Views.find(v, R.id.unfoldable_view);

        Bitmap glance = BitmapFactory.decodeResource(getResources(), R.drawable.unfold_glance);
        mUnfoldableView.setFoldShading(new GlanceFoldShading(getActivity(), glance));

        mUnfoldableView.setOnFoldingListener(new UnfoldableView.SimpleFoldingListener() {
            @Override
            public void onUnfolding(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(true);
                mDetailsLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onUnfolded(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(false);
            }

            @Override
            public void onFoldingBack(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(true);
            }

            @Override
            public void onFoldedBack(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(false);
                mDetailsLayout.setVisibility(View.INVISIBLE);
            }
        });

        return v;

    }


    @Override
    public void onResume() {
        super.onResume();
        receiverInit();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        View v = view;
        Painting painting = (Painting)view.getTag();
        openDetails(v, painting);
    }

    @Override
    public void OnFoldDataResult(View convertView, Painting painting) {
//        Toast.makeText(getActivity(), "Callback Call!!!! ", Toast.LENGTH_SHORT).show();
        Log.e("OnFoldDataResult", "TEST OnFoldDataResult Call !!!");
    }

    private void receiverInit(){

        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                View view = ((MainActivity)getActivity()).getmSaveConvertView();
                Painting painting = ((MainActivity)getActivity()).getmSavePainting();
                Toast.makeText(getActivity(), "Receive", Toast.LENGTH_SHORT ).show();

                openDetails(view, painting);

            }
        };

        IntentFilter filter = new IntentFilter("test");
        getActivity().registerReceiver(mReceiver, filter);

    }

//    @Override
//    public void OnResult(View convertView, Painting painting) {
//        openDetails(convertView, painting);
//    }


    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(mReceiver);

    }

    public void openDetails(View coverView, Painting painting) {
        ImageView image = Views.find(mDetailsLayout, R.id.details_image);
        TextView title = Views.find(mDetailsLayout, R.id.details_title);
        TextView description;
        description = Views.find(mDetailsLayout, R.id.details_text);

        Picasso.with(getActivity()).load(painting.getImageId()).into(image);
        title.setText(painting.getTitle());

        SpannableBuilder builder = new SpannableBuilder(getActivity());
        builder
                .createStyle().setFont(Typeface.DEFAULT_BOLD).apply()
                .append(R.string.year).append(": ")
                .clearStyle()
                .append(painting.getYear()).append("\n")
                .createStyle().setFont(Typeface.DEFAULT_BOLD).apply()
                .append(R.string.location).append(": ")
                .clearStyle()
                .append(painting.getLocation());
        description.setText(builder.build());

        mUnfoldableView.unfold(coverView, mDetailsLayout);
    }



}
