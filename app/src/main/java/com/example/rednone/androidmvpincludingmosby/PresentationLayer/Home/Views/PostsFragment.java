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

import com.example.rednone.androidmvpincludingmosby.DataLayer.Models.PostModel;
import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Adapters.PostsAdapter;
import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Interfaces.PostsPresenter;
import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Interfaces.PostsView;
import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Presenters.PostsPresenterImpl;
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
public class PostsFragment extends MvpLceViewStateFragment<SwipeRefreshLayout,List<PostModel>,PostsView,PostsPresenter>
                           implements PostsView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.postsRecyclerView)
    RecyclerView recyclerView;

    PostsAdapter adapter;

    public PostsFragment() {
        // Required empty public constructor
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getLocalizedMessage();
    }

    @Override
    public PostsPresenter createPresenter() {
        return new PostsPresenterImpl();
    }

    @Override
    public List<PostModel> getData() {
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
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        contentView.setOnRefreshListener(this);

        adapter = new PostsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        loadData(false);
    }

    @NonNull
    @Override
    public LceViewState<List<PostModel>, PostsView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Override
    public void onNewViewStateInstance() {
        super.onNewViewStateInstance();
    }

    @Override
    public void setData(List<PostModel> data) {
        adapter.setPosts(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadPosts(pullToRefresh);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        contentView.setRefreshing(pullToRefresh);
    }
}
