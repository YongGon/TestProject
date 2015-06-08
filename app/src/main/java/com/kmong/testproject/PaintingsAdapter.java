package com.kmong.testproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexvasilkov.android.commons.adapters.ItemsAdapter;
import com.alexvasilkov.android.commons.utils.Views;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

public class PaintingsAdapter extends ItemsAdapter<Painting> implements View.OnClickListener {

    private Context mContext;
    private OnPaintingClickListener mCallback;

    public PaintingsAdapter(Context context) {
        super(context);
        mContext = context;
        mCallback = (OnPaintingClickListener)context;
        setItemsList(Arrays.asList(Painting.getAllPaintings(context.getResources())));
    }

    @Override
    protected View createView(Painting item, int pos, ViewGroup parent, LayoutInflater inflater) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder vh = new ViewHolder();
        vh.image = Views.find(view, R.id.list_item_image);
        vh.image.setOnClickListener(this);
        vh.title = Views.find(view, R.id.list_item_title);
        view.setTag(vh);

        return view;
    }

    @Override
    protected void bindView(Painting item, int pos, View convertView) {
        ViewHolder vh = (ViewHolder) convertView.getTag();

        vh.image.setTag(item);
        Picasso.with(convertView.getContext()).load(item.getImageId()).noFade().into(vh.image);
        vh.title.setText(item.getTitle());
    }

    @Override
    public void onClick(View view) {
//        if (view.getContext() instanceof MainActivity) {
//            MainActivity activity = (MainActivity) view.getContext();
//            activity.openDetails(view, (Painting) view.getTag());
            mCallback.OnResult(view, (Painting) view.getTag());
//        }
    }

    private static class ViewHolder {
        ImageView image;
        TextView title;
    }

    public interface OnPaintingClickListener {
        void OnResult(View convertView, Painting painting);
    }

}
