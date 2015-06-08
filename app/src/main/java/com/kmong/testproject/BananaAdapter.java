package com.kmong.testproject;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by GON on 2015-05-11.
 */
public class BananaAdapter extends RecyclerView.Adapter<BananaAdapter.ViewHolder> {

    private List<BananaItem> mBananaTempArray;

    public BananaAdapter(List<BananaItem> bananaList) {

        mBananaTempArray = bananaList;

    }

    /**
     * 레이아웃을 만들어서 Holer에 저장
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.e("viewType", "@@@@@@@@@@@@ " + viewType);

        switch (viewType) {

            case 2:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banana_row, parent, false));
            case 1:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banana_list_row, parent, false));
            default:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banana_row, parent, false));

        }
    }


    /**
     * listView getView 를 대체
     * 넘겨 받은 데이터를 화면에 출력하는 역할
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {

            case 2:
                holder.img.setImageResource(mBananaTempArray.get(position).getIcon());
                holder.nameText.setText(mBananaTempArray.get(position).getName());
                holder.cntText.setText(mBananaTempArray.get(position).getCnt());
                break;
            case 1:
                holder.text1.setText("test1");
                holder.text2.setText("test2");
                holder.text3.setText("test3");
                holder.text4.setText("test4");
                holder.text5.setText("test5");
                break;

        }
    }


    @Override
    public int getItemViewType(int position) {

        if (position >= 10) {

            return 1;
        } else {

            return 2;
        }

    }

    @Override
    public int getItemCount() {
        return mBananaTempArray.size();
    }


    /**
     * ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView img;
        public TextView nameText;
        public TextView cntText;
        public TextView text1;
        public TextView text2;
        public TextView text3;
        public TextView text4;
        public TextView text5;


        public ViewHolder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.banana_ImageView);
            nameText = (TextView) itemView.findViewById(R.id.banana_name_TextView);
            cntText = (TextView) itemView.findViewById(R.id.banana_cnt_TextView);
            text1 = (TextView) itemView.findViewById(R.id.TextView_1);
            text2 = (TextView) itemView.findViewById(R.id.TextView_2);
            text3 = (TextView) itemView.findViewById(R.id.TextView_3);
            text4 = (TextView) itemView.findViewById(R.id.TextView_4);
            text5 = (TextView) itemView.findViewById(R.id.TextView_5);
        }
    }


}
