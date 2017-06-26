package com.example.luiz.sosquality.presentation.fragments;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luiz.sosquality.R;
import com.example.luiz.sosquality.domain.model.UserEntity;
import com.example.luiz.sosquality.presentation.contracts.ContractProfile;
import com.example.luiz.sosquality.presentation.core.BaseFragment;
import com.example.luiz.sosquality.presentation.presenter.ProfilePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.luiz.sosquality.presentation.activities.ProfileActivity.USER_INTENT;
import static com.google.common.base.Preconditions.checkNotNull;

public class ProfileFragment extends BaseFragment implements ContractProfile.View {

    @BindView(R.id.tv_name) TextView tvName;
    @BindView(R.id.tv_dni) TextView tvDni;
    @BindView(R.id.tv_height) TextView tvHeight;
    @BindView(R.id.tv_weight) TextView tvWeight;
    @BindView(R.id.tv_imc) TextView tvImc;

    private Unbinder unbinder;
    private ContractProfile.Presenter mPresenter;
    private LinearLayoutManager linearLayoutManager;
    private UserDialogShow userDialogShow;

    private UserEntity userEntity;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(UserEntity userEntity) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(USER_INTENT, userEntity);

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start(userEntity.getDNIUsuario());
        initView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Bundle argument = getArguments();
        userEntity = (UserEntity) argument.getSerializable(USER_INTENT);

        mPresenter = new ProfilePresenter(this, getContext());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_layout_user_profile, container, false);
        unbinder = ButterKnife.bind(this, root);

        mPresenter.loadUserInformation(userEntity);

        return root;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    @Override
    protected void initView() {
        super.initView();

        setHasOptionsMenu(true);
        mPresenter = new ProfilePresenter(this, getContext());
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showUserInformation(UserEntity userEntity) {
        tvName.setText(userEntity.getNombreUsuario());
        tvDni.setText(String.valueOf(userEntity.getDNIUsuario()))

        ;
        tvHeight.setText(String.valueOf(userEntity.getTalla()).substring(0,4));
        tvWeight.setText(String.valueOf(userEntity.getPeso()));
        tvImc.setText(String.valueOf((userEntity.getPeso()*10000)/Math.pow(userEntity.getTalla(), 2)).substring(0,5));
    }

    @Override
    public void showContactsForUser(UserEntity userEntity) {

    }

    @Override
    public void showAlergicForUser(UserEntity userEntity) {

    }

    @Override
    public void showMaps() {

    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingNewsError() {
        showMessage("No se pudo la informacion");
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(ContractProfile.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
