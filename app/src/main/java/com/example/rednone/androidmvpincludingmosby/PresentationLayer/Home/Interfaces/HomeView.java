package com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Interfaces;

import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by RedNone on 21.01.2018.
 */

public interface HomeView extends MvpView {
    void setActiveFragment(int menuId);
}
