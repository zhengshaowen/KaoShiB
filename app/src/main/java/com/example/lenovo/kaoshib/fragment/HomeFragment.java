package com.example.lenovo.kaoshib.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lenovo.kaoshib.R;
import com.example.lenovo.kaoshib.ViewpageActivity;
import com.example.lenovo.kaoshib.adapter.RecyclerViewAdapter;
import com.example.lenovo.kaoshib.bean.ProBean;
import com.example.lenovo.kaoshib.bean.PsBean;
import com.example.lenovo.kaoshib.presenter.MainPresenter;
import com.example.lenovo.kaoshib.view.MainView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements MainView{

    private ArrayList<ProBean.DataBean.DatasBean> list;
    private MainPresenter presenter;
    private int page = 1;
    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerview;
    private SmartRefreshLayout smart;
    private int index = 0;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new MainPresenter(this);
        initView(getView());
        presenter.requestServer(page);
    }

    private void initView(View view) {
        recyclerview = view.findViewById(R.id.recyclerview);
        smart = view.findViewById(R.id.smart);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        recyclerview.setAdapter(adapter);

        smart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                smart.finishLoadMore(true);
                presenter.requestServer(page++);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smart.finishRefresh(true);
                list.clear();
                presenter.requestServer(page);
            }
        });

        adapter.setMyonLongClike(new RecyclerViewAdapter.MyonLongClike() {
            @Override
            public void onLongClike(int position) {
                index = position;
            }
        });
        registerForContextMenu(recyclerview);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(1,1,1,"发送广播");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String s = item.getTitle().toString();
        switch (item.getItemId()){
            case 1:
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                EventBus.getDefault().postSticky(new PsBean(list,index));
                startActivity(new Intent(getContext(), ViewpageActivity.class));
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onSuccess(List<ProBean.DataBean.DatasBean> datas) {
        this.list.addAll(datas);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String str) {

    }
}
