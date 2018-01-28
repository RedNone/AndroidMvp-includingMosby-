package com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Presenters;

import android.support.annotation.NonNull;

import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Interfaces.HomePresenter;
import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Interfaces.HomeView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

/**
 * Created by RedNone on 21.01.2018.
 */

public class HomePresenterImpl extends MvpBasePresenter<HomeView> implements HomePresenter {

    @Override
    public void itemSelected(final int itemId) {
        ifViewAttached(new ViewAction<HomeView>() {
            @Override
            public void run(@NonNull HomeView view) {
                view.setActiveFragment(itemId);
            }
        });
    }
}
