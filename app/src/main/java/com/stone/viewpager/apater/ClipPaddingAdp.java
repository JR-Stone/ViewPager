package com.stone.viewpager.apater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stone.viewpager.R;

import java.util.List;

/**
 * @author 百路
 */

public class ClipPaddingAdp extends PagerAdapter {

    private List<String> mList;
    private Context context;

    public ClipPaddingAdp(Context context, List<String> list) {
        this.context = context;
        mList = list;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_img, container, false);
        ImageView imageView = view.findViewById(R.id.img);
        Glide.with(context).load(mList.get(position)).into(imageView);
        (container).addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
