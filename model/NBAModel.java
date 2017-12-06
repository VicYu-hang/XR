package com.biwe.day08.model;

import com.biwe.day08.bean.Bean;
import com.biwe.day08.net.RetrofitHelper;
import com.biwe.day08.net.ServerApi;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by VicYu on 2017/12/6.
 */

public class NBAModel {

    public void getData(Observer<Bean> observer){

        ServerApi serverApi = RetrofitHelper.getServerApi();
        Observable<Bean> bean = serverApi.getBean();
        bean.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
