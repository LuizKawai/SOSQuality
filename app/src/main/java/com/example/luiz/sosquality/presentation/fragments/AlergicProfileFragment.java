package com.example.luiz.sosquality.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luiz.sosquality.R;
import com.example.luiz.sosquality.domain.model.AlergyEntity;
import com.example.luiz.sosquality.domain.model.UserEntity;
import com.example.luiz.sosquality.presentation.adapters.AlergicProfileAdapter;
import com.example.luiz.sosquality.presentation.contracts.ContractAlergicProfile;
import com.example.luiz.sosquality.presentation.core.BaseFragment;
import com.example.luiz.sosquality.presentation.presenter.AlergicProfilePresenter;
import com.example.luiz.sosquality.presentation.presenter.commons.CommunicatePresenterAlergicProfileItem;
import com.example.luiz.sosquality.utils.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.luiz.sosquality.presentation.activities.AlergicProfileActivity.ALERGIC_LIST;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by luiz on 12/13/2016.
 */

public class AlergicProfileFragment extends BaseFragment implements ContractAlergicProfile.View {

    @BindView(R.id.alergic_list) RecyclerView alergicList;
    @BindView(R.id.alergicLL) LinearLayout alergicLL;
    @BindView(R.id.noAlergicMain) TextView noAlergicMain;
    @BindView(R.id.noAlergic) LinearLayout noAlergic;
    @BindView(R.id.alergicContainer) RelativeLayout alergicContainer;
    @BindView(R.id.refresh_layout_alergic) ScrollChildSwipeRefreshLayout refreshLayoutAlergic;

    private Unbinder unbinder;
    private ContractAlergicProfile.Presenter mPresenter;
    private AlergicProfileAdapter alergicProfileAdapter;
    private LinearLayoutManager linearLayoutManager;

    private UserEntity userEntity;

    public AlergicProfileFragment() {
    }

    public static AlergicProfileFragment newInstance(UserEntity userEntity) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(ALERGIC_LIST, userEntity);

        AlergicProfileFragment fragment = new AlergicProfileFragment();
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
        userEntity = (UserEntity) argument.getSerializable(ALERGIC_LIST);

        mPresenter = new AlergicProfilePresenter(this, getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_layout_alergic_profile, container, false);
        unbinder = ButterKnife.bind(this, root);
        noAlergic.setVisibility(View.GONE);

        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout_alergic);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(alergicList);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.llamadaAlergias(true, userEntity.getDNIUsuario());
            }
        });

        return root;
    }

    @Override
    protected void initView() {
        super.initView();

        setHasOptionsMenu(true);
        mPresenter = new AlergicProfilePresenter(this, getContext());
        alergicList.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        alergicList.setLayoutManager(linearLayoutManager);
        alergicProfileAdapter = new AlergicProfileAdapter(
                getContext(),
                new ArrayList<AlergyEntity>(),
                (CommunicatePresenterAlergicProfileItem) mPresenter
        );
        alergicList.setAdapter(alergicProfileAdapter);
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
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout_alergic);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showAlergics(ArrayList<AlergyEntity> alergyEntities) {
        alergicProfileAdapter = new AlergicProfileAdapter(getContext(), alergyEntities, (CommunicatePresenterAlergicProfileItem) mPresenter);

        if (alergicList != null) {
            alergicList.setAdapter(alergicProfileAdapter);
        }
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingNewsError() {
        showMessage("No se pudo cargar la lista de alergias");
    }

    @Override
    public void addNewAlergic(AlergyEntity alergyEntity) {

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(ContractAlergicProfile.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
