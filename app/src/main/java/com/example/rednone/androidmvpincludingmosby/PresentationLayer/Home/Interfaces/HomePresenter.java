package com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Interfaces;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

/**
 * Created by RedNone on 21.01.2018.
 */

public interface HomePresenter extends MvpPresenter<HomeView> {
    void itemSelected(int itemId);
}
