package com.biwe.day08.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.biwe.day08.Main2Activity;
import com.biwe.day08.R;
import com.biwe.day08.adapter.Adapter;
import com.biwe.day08.bean.Bean;
import com.biwe.day08.net.Api;
import com.biwe.day08.presenter.NBAPresenter;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IView {

    private XRecyclerView mXrecy;
    private NBAPresenter presenter;
    private Adapter adapter;
    private List<Bean.NewslistBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        presenter = new NBAPresenter(this);
        presenter.getData();
        FreshOrLoding();
    }

    /**
     * 刷新和加载
     */
    private void FreshOrLoding() {

        mXrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                presenter.getData();
                mXrecy.refreshComplete();
            }

            @Override
            public void onLoadMore() {

                presenter.getData();
                adapter.notifyDataSetChanged();
                mXrecy.loadMoreComplete();

            }
        });
    }


    private void initView() {
        mXrecy = (XRecyclerView) findViewById(R.id.xrecy);
    }

    @Override
    public void ShowData(Bean bean) {
        mXrecy.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, bean);
        mXrecy.setAdapter(adapter);
        mXrecy.setPullRefreshEnabled(true);
        mXrecy.setLoadingMoreEnabled(true);
        mXrecy.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);


        adapter.setOnItemClickListener(new Adapter.onItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });
    }
}
