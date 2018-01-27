package com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Presenters;

import android.support.annotation.NonNull;

import com.example.rednone.androidmvpincludingmosby.DataLayer.Managers.ApiManager;
import com.example.rednone.androidmvpincludingmosby.DataLayer.Models.UserModel;
import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Interfaces.UsersPresenter;
import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Interfaces.UsersView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RedNone on 27.01.2018.
 */

public class UsersPresenterImpl extends MvpBasePresenter<UsersView> implements UsersPresenter {

    @Override
    public void loadUsers(final boolean pullToRefresh) {
        ApiManager.getInstance().getUsers(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, final Response<List<UserModel>> response) {
                ifViewAttached(new ViewAction<UsersView>() {
                    @Override
                    public void run(@NonNull UsersView view) {
                        view.setData(response.body());
                        view.showContent();
                    }
                });
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, final Throwable t) {
                ifViewAttached(new ViewAction<UsersView>() {
                    @Override
                    public void run(@NonNull UsersView view) {
                        view.showError(t, pullToRefresh);
                    }
                });
            }
        });
    }
}
