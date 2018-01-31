package com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.ViewStates;

import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Interfaces.HomeView;
import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Views.PostsFragment;
import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Views.UsersFragment;
import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState;

/**
 * Created by RedNone on 29.01.2018.
 */

public class HomeViewState implements ViewState<HomeView> {

    int visibleMenuItemId;

    PostsFragment postsFragment = new PostsFragment();
    UsersFragment usersFragment = new UsersFragment();

    @Override
    public void apply(HomeView view, boolean retained) {
        view.setActiveFragment(visibleMenuItemId);
    }

    public void setVisibleMenuItemId(int visibleMenuItemId) {
        this.visibleMenuItemId = visibleMenuItemId;
    }

    public PostsFragment getPostsFragment() {
        return postsFragment;
    }

    public UsersFragment getUsersFragment() {
        return usersFragment;
    }
}
