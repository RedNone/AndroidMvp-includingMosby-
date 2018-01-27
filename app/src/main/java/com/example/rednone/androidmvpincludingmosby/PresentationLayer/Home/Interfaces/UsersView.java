package com.example.rednone.androidmvpincludingmosby.PresentationLayer.Home.Interfaces;

import com.example.rednone.androidmvpincludingmosby.DataLayer.Models.UserModel;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

import java.util.List;

/**
 * Created by RedNone on 27.01.2018.
 */

public interface UsersView extends MvpLceView<List<UserModel>> {
}
