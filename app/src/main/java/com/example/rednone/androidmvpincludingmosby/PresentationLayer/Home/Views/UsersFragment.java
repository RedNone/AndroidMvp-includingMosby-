package com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Views;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rednone.androidmvpincludingmosby.DataLayer.Models.UserModel;
import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Interfaces.UsersPresenter;
import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Interfaces.UsersView;
import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Presenters.UsersPresenterImpl;
import com.example.rednone.androidmvpincludingmosby.R;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.MvpLceViewStateFragment;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.data.RetainingLceViewState;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersFragment extends MvpLceViewStateFragment<SwipeRefreshLayout, List<UserModel>, UsersView, UsersPresenter>
        implements UsersView, SwipeRefreshLayout.OnRefreshListener {


    public UsersFragment() {
        // Required empty public constructor
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getLocalizedMessage();
    }

    @Override
    public UsersPresenter createPresenter() {
        return new UsersPresenterImpl();
    }

    @Override
    public List<UserModel> getData() {
        return null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false);
    }

    @Override
    public void onRefresh() {

    }

    @NonNull
    @Override
    public LceViewState<List<UserModel>, UsersView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Override
    public void setData(List<UserModel> data) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }
}
