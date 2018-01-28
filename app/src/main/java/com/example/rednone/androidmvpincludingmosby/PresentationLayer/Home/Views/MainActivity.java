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
import com.example.rednone.androidmvpincludingmosby.R;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpActivity<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.homeNavigation)
    BottomNavigationView bottomBar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_posts:
                    selectedFragment = new PostsFragment();
                    break;
                case R.id.navigation_albums:
                    break;
                case R.id.navigation_users:
                    selectedFragment = new UsersFragment();
                    break;
            }
            transaction.replace(R.id.homeConteiner, selectedFragment);
            transaction.commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bottomBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        PostsFragment postFragment = new PostsFragment();
        transaction.replace(R.id.homeConteiner, postFragment);
        transaction.commit();
    }

    @NonNull
    @Override
    public HomePresenter createPresenter() {
        return new HomePresenterImpl();
    }
}
