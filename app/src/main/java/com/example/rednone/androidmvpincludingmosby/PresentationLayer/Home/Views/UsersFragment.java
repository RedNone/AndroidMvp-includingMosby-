package com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Views;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rednone.androidmvpincludingmosby.DataLayer.Models.UserModel;
import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Adapters.UsersAdapter;
import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Interfaces.UsersPresenter;
import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Interfaces.UsersView;
import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Presenters.UsersPresenterImpl;
import com.example.rednone.androidmvpincludingmosby.R;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.MvpLceViewStateFragment;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.data.RetainingLceViewState;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersFragment extends MvpLceViewStateFragment<SwipeRefreshLayout, List<UserModel>, UsersView, UsersPresenter>
        implements UsersView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.usersRecyclerView)
    RecyclerView recyclerView;

    UsersAdapter adapter;

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
        return adapter == null ? null : adapter.getData();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        contentView.setOnRefreshListener(this);

        adapter = new UsersAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        loadData(false);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @NonNull
    @Override
    public LceViewState<List<UserModel>, UsersView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Override
    public void setData(List<UserModel> data) {
        adapter.setUsers(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadUsers(pullToRefresh);
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }
}
