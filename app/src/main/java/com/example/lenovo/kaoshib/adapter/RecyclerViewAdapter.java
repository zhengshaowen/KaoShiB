package com.example.lenovo.kaoshib.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.kaoshib.R;
import com.example.lenovo.kaoshib.bean.ProBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2019/9/1.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ProBean.DataBean.DatasBean> list = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(ArrayList<ProBean.DataBean.DatasBean> list) {
        if (list != null) {
            this.list = list;
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = null;
        if (viewType == 1) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_item1, parent, false);
            return new MyHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.layout_item2, parent, false);
            return new MyHolder1(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == 1) {
            MyHolder holder1 = (MyHolder) holder;
            //System.out.println(list.get(position).getTitle());
            holder1.itemTitle.setText(list.get(position).getTitle());
            holder1.itemName.setText(list.get(position).getId() + "");
            Glide.with(context).load(list.get(position).getEnvelopePic()).into(holder1.itemImage);
            holder1.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    myonLongClike.onLongClike(position);
                    return false;
                }
            });
        } else {
            MyHolder1 holder2 = (MyHolder1) holder;
            holder2.item1Title.setText(list.get(position).getTitle());
            holder2.item1Name.setText(list.get(position).getId() + "");
            Glide.with(context).load(list.get(position).getEnvelopePic()).into(holder2.item1Image);
            holder2.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    myonLongClike.onLongClike(position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 3 == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private ImageView itemImage;

        private TextView itemTitle;

        private TextView itemName;

        public MyHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemName = itemView.findViewById(R.id.item_name);
        }
    }

    class MyHolder1 extends RecyclerView.ViewHolder {

        private TextView item1Title;

        private TextView item1Name;

        private ImageView item1Image;

        public MyHolder1(View itemView) {
            super(itemView);
            item1Title = itemView.findViewById(R.id.item1_title);
            item1Name = itemView.findViewById(R.id.item1_name);
            item1Image = itemView.findViewById(R.id.item1_image);
        }
    }

    private MyonLongClike myonLongClike;

    public void setMyonLongClike(MyonLongClike myonLongClike) {
        this.myonLongClike = myonLongClike;
    }

    public interface MyonLongClike{
        void onLongClike(int position);
    }
}
