package com.example.luiz.sosquality.presentation.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.luiz.sosquality.R;
import com.example.luiz.sosquality.domain.model.UserEntity;
import com.example.luiz.sosquality.presentation.activities.FirstAidActivity;
import com.example.luiz.sosquality.presentation.activities.LandingActivity;
import com.example.luiz.sosquality.presentation.activities.ProfileActivity;
import com.example.luiz.sosquality.presentation.activities.RegisterActivity;
import com.example.luiz.sosquality.presentation.contracts.ContractLanding;
import com.example.luiz.sosquality.presentation.core.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import com.example.luiz.sosquality.presentation.presenter.LandingPresenter;
import com.mobsandgeeks.saripaar.Validator;

import static com.google.common.base.Preconditions.checkNotNull;

public class LandingFragment extends BaseFragment implements ContractLanding.View{

    @BindView(R.id.btn_register) Button btnRegister;
    @BindView(R.id.btn_primeros_auxilios) Button btnPrimerosAuxilios;
    @BindView(R.id.btn_login) Button btnLogin;

    private Unbinder unbinder;

    ProgressDialog progressDialog;
    private Validator validator;
    private ContractLanding.Presenter mPresenter;
    private SearchCodeDialog searchCodeDialog;

    public LandingFragment() {}

    public static LandingFragment newInstance() {
        return new LandingFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new LandingPresenter(this, getContext());

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        initView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_layout_landing, container, false);
        unbinder = ButterKnife.bind(this, root);

        return root;
    }

    @Override
    protected void initView() {
        super.initView();
        setHasOptionsMenu(true);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Buscando");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminateDrawable(getResources().getDrawable(R.drawable.circle_progress));
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active) {
            progressDialog.show();
        } else {
            if (progressDialog != null)
                progressDialog.dismiss();
        }
    }

    public void registerUser() {
        validator.validate();
    }

    @Override
    public void showLoadingError(@NonNull String error) {
        ((LandingActivity) getActivity()).showMessageError(error);
    }

    @Override
    public void userProfile(UserEntity userEntity) {
        Intent intent = new Intent(getContext(), ProfileActivity.class);
        intent.putExtra(ProfileActivity.USER_INTENT, userEntity);
        startActivity(intent);
    }

    @Override
    public void searchUser(@NonNull String code) {
        mPresenter.searchUser(code);
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

    @OnClick({R.id.btn_register, R.id.btn_primeros_auxilios, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                searchCodeDialog = new SearchCodeDialog(getContext(), this, "1");
                searchCodeDialog.show();
                break;
            case R.id.btn_register:
                next(getActivity(), null, RegisterActivity.class, false);
                break;
            case R.id.btn_primeros_auxilios:
                next(getActivity(), null, FirstAidActivity.class, false);
                break;
        }
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setPresenter(ContractLanding.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
