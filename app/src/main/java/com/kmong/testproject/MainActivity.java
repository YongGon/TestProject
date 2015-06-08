package com.kmong.testproject;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.alexvasilkov.android.commons.texts.SpannableBuilder;
import com.alexvasilkov.android.commons.utils.Views;
import com.alexvasilkov.foldablelayout.UnfoldableView;
import com.squareup.picasso.Picasso;

import java.util.List;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialSectionListener;


public class MainActivity extends MaterialNavigationDrawer implements PaintingsAdapter.OnPaintingClickListener {


    private View mDetailsLayout;
    private UnfoldableView mUnfoldableView;
    private View mlayout, mSaveConvertView;
    private Painting mSavePainting;
    private OnFoldsendDataListener mCallback;

    @Override
    public void init(Bundle savedInstanceState) {


        View v = LayoutInflater.from(this).inflate(R.layout.test, null);
        setDrawerHeaderCustom(v);



//        MaterialDialog builder = new MaterialDialog.Builder(this)
//                .title("TEST")
//                .content("내용")
//                .positiveText("확인")
//                .negativeText("취소")
//                .autoDismiss(true)
//                .show();

        mlayout = getLayoutInflater().inflate(R.layout.activity_foldabletest, null);
        mDetailsLayout = Views.find(mlayout, R.id.details_layout);
        mDetailsLayout.setVisibility(View.INVISIBLE);

        mUnfoldableView = Views.find(mlayout, R.id.unfoldable_view);
        // create sections


        this.addSection(newSection("Section 1", new TestFragment()));
        this.addSection(newSection("Section 2", new QuickReturnTestFragment()));
        this.addSection(newSection("Section 3", new Intent(this, TestActivity.class)));
        this.addSection(newSection("Section 4", R.drawable.ic_launcher, new TestFragment()).setSectionColor(Color.parseColor("#9c27b0")));
        this.addSection(newSection("앱설치여부 테스트", R.drawable.ic_launcher, appInstallTest).setSectionColor(Color.parseColor("#9c27b0")));
        this.addSection(newSection("Section", R.drawable.ic_launcher, new TestFragment()).setSectionColor(Color.parseColor("#03a9f4")));
        this.addSection(newSection("바나나곳간테스트", R.drawable.ic_launcher, new Intent(MainActivity.this, BananaTestActivity.class)).setSectionColor(Color.parseColor("#03a9f4")));

        // create bottom section
        this.addBottomSection(newSection("Bottom Section", R.drawable.ic_launcher, new Intent(this, MainActivity.class)));
    }


    private MaterialSectionListener appInstallTest = new MaterialSectionListener() {
        @Override
        public void onClick(MaterialSection materialSection) {

            Intent startLink = getPackageManager().getLaunchIntentForPackage("com.kmong.kmong");

            final String appPackageName = "com.kmong.kmong"; // getPackageName() from Context or Activity object


if(getPackageManager().getLaunchIntentForPackage("com.kmong.kmong") == null){

    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.kmong.kmong")));
}

else {
    ComponentName componentName = new ComponentName("com.kmong.kmong", "com.kmong.Activity.CatListActivity");

    Intent i = new Intent("ACTION_KMONG_CATEGORY");

    i.addCategory(Intent.CATEGORY_LAUNCHER);
    i.setComponent(componentName);
    i.putExtra("PLZM_MOBILE", "01034110912");
    i.putExtra("PLZM_USERID", "72738");
    i.putExtra("PLZM_CATEGORY", "0009");

    startActivity(i);
}

        }
    };



    @Override
    public void OnResult(View convertView, Painting painting) {
//        openDetails(convertView, painting);
        Toast.makeText(MainActivity.this, "OnResult Call!!!", Toast.LENGTH_SHORT).show();
        mSaveConvertView = convertView;
        mSavePainting = painting;
        Intent i = new Intent("test");
        i.putExtra("test", "ok");
        sendBroadcast(i);
    }

    @Override
    public void onBackPressed() {
        if (mUnfoldableView != null && (mUnfoldableView.isUnfolded() || mUnfoldableView.isUnfolding())) {
            mUnfoldableView.foldBack();
        } else {
            super.onBackPressed();
        }
    }

    public View getmSaveConvertView() {
        return mSaveConvertView;
    }

    public Painting getmSavePainting() {
        return mSavePainting;
    }

    public void openDetails(View coverView, Painting painting) {
        ImageView image = Views.find(mDetailsLayout, R.id.details_image);
        TextView title = Views.find(mDetailsLayout, R.id.details_title);
        TextView description;
        description = Views.find(mDetailsLayout, R.id.details_text);

        Picasso.with(this).load(painting.getImageId()).into(image);
        title.setText(painting.getTitle());

        SpannableBuilder builder = new SpannableBuilder(this);
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

    public interface OnFoldsendDataListener{
        void OnFoldDataResult(View convertView, Painting painting);
    }

}
