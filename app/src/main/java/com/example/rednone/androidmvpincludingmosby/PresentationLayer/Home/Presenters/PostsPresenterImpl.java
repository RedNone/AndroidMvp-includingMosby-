package com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Presenters;

import android.support.annotation.NonNull;

import com.example.rednone.androidmvpincludingmosby.DataLayer.Managers.ApiManager;
import com.example.rednone.androidmvpincludingmosby.DataLayer.Models.PostModel;
import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Interfaces.PostsPresenter;
import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Interfaces.PostsView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RedNone on 21.01.2018.
 */

public class PostsPresenterImpl extends MvpBasePresenter<PostsView> implements PostsPresenter {


    @Override
    public void loadPosts(final boolean pullToRefresh) {
        ApiManager.getInstance().getPosts(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, final Response<List<PostModel>> response) {
                ifViewAttached(new ViewAction<PostsView>() {
                    @Override
                    public void run(@NonNull PostsView view) {
                        view.setData(response.body());
                        view.showContent();
                    }
                });
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, final Throwable t) {
                ifViewAttached(new ViewAction<PostsView>() {
                    @Override
                    public void run(@NonNull PostsView view) {
                        view.showError(t, pullToRefresh);
                    }
                });
            }
        });
    }


}
