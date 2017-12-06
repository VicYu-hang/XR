package com.biwe.day08.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.biwe.day08.R;
import com.biwe.day08.adapter.Adapter;
import com.biwe.day08.bean.Bean;
import com.biwe.day08.net.Api;
import com.biwe.day08.presenter.NBAPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IView {

    private XRecyclerView mXrecy;
    private NBAPresenter presenter;
    private int count=1;
//    private List<String>list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        presenter = new NBAPresenter(this);
        presenter.getData(count);
        FreshOrLoding();
//        list = new ArrayList<>();
//        list.add("nba/?key=71e58b5b2f930eaf1f937407acde08fe&num=1");
//        list.add("nba/?key=71e58b5b2f930eaf1f937407acde08fe&num=2");
//        list.add("nba/?key=71e58b5b2f930eaf1f937407acde08fe&num=3");

    }

    /**
     * 刷新和加载
     */
    private void FreshOrLoding() {

        mXrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                count++;
                presenter.getData(count);
                mXrecy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                    count++;
//                    presenter.getData(list.size());
                    mXrecy.loadMoreComplete();
            }
        });
    }

    @Override
    public void ShowData(Bean bean,int count) {

        mXrecy.setLayoutManager(new LinearLayoutManager(this));
        mXrecy.setAdapter(new Adapter(this,bean));

    }

    private void initView() {
        mXrecy = (XRecyclerView) findViewById(R.id.xrecy);
    }
}
