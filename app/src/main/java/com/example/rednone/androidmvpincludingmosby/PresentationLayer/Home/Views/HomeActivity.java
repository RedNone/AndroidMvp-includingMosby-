package com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Interfaces.HomePresenter;
import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Interfaces.HomeView;
import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Presenters.HomePresenterImpl;
import com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.ViewStates.HomeViewState;
import com.example.rednone.androidmvpincludingmosby.R;
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends MvpViewStateActivity<HomeView, HomePresenter, HomeViewState> implements HomeView {

    @BindView(R.id.homeNavigation)
    BottomNavigationView bottomBar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            presenter.itemSelected(item.getItemId());
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bottomBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @NonNull
    @Override
    public HomePresenter createPresenter() {
        return new HomePresenterImpl();
    }

    @Override
    public void setActiveFragment(int menuId) {
        viewState.setVisibleMenuItemId(menuId);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment selectedFragment = null;
        switch (menuId) {
            case R.id.navigation_posts:
                selectedFragment = viewState.getPostsFragment();
                break;
            case R.id.navigation_users:
                selectedFragment = viewState.getUsersFragment();
                break;
        }
        transaction.replace(R.id.homeConteiner, selectedFragment);
        transaction.commit();
    }

    @NonNull
    @Override
    public HomeViewState createViewState() {
        return new HomeViewState();
    }

    @Override
    public void onNewViewStateInstance() {
        bottomBar.setSelectedItemId(R.id.navigation_posts);
    }
}
