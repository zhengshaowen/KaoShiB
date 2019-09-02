package com.example.lenovo.kaoshib.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lenovo.kaoshib.R;
import com.example.lenovo.kaoshib.bean.ProBean;

import java.util.ArrayList;

/**
 * Created by lenovo on 2019/9/1.
 */

public class ViewAdapter extends PagerAdapter{

    private ArrayList<ProBean.DataBean.DatasBean> list = new ArrayList<>();

    public ViewAdapter(ArrayList<ProBean.DataBean.DatasBean> list) {
        if (list != null){
            this.list = list;
        }

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.images,null);
        ImageView imagessss = view.findViewById(R.id.view_imagessss);
        Glide.with(container.getContext()).load(list.get(position).getEnvelopePic()).into(imagessss);
        container.addView(view);
        imagessss.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
               myonLongclike.onLongclike(position);
                return false;
            }
        });
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    private MyonLongclike myonLongclike;

    public void setMyonLongclike(MyonLongclike myonLongclike) {
        this.myonLongclike = myonLongclike;
    }

    public interface MyonLongclike{
        void onLongclike(int position);
    }
}
