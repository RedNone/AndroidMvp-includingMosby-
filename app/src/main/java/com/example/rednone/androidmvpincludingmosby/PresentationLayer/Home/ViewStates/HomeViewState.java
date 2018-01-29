package com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.ViewStates;

import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Interfaces.HomeView;
import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState;

/**
 * Created by RedNone on 29.01.2018.
 */

public class HomeViewState implements ViewState<HomeView> {

    int visibleMenuItemId;

    @Override
    public void apply(HomeView view, boolean retained) {
        view.setActiveFragment(visibleMenuItemId);
    }

    public void setVisibleMenuItemId(int visibleMenuItemId) {
        this.visibleMenuItemId = visibleMenuItemId;
    }
}
