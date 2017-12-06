package com.biwe.day08.presenter;

import com.biwe.day08.bean.Bean;
import com.biwe.day08.model.NBAModel;
import com.biwe.day08.view.IView;

import rx.Observer;

/**
 * Created by VicYu on 2017/12/6.
 */

public class NBAPresenter {

    private IView iView;
    private  NBAModel nbaModel;

    public NBAPresenter(IView iView) {
        this.iView = iView;
        nbaModel=new NBAModel();
    }

    public void getData(){

        nbaModel.getData(new Observer<Bean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Bean bean) {
                iView.ShowData(bean);
            }
        });
    }
    public void delete(){
        iView=null;
    }
}
