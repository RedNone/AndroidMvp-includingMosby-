package com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Interfaces;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

/**
 * Created by RedNone on 27.01.2018.
 */

public interface UsersPresenter extends MvpPresenter<UsersView> {
    void loadUsers(final boolean pullToRefresh);
}
