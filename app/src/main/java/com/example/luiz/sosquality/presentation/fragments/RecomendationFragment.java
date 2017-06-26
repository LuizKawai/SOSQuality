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
import com.example.luiz.sosquality.domain.model.RecomendationEntity;
import com.example.luiz.sosquality.domain.model.SubcategoryEntity;
import com.example.luiz.sosquality.presentation.adapters.RecomendationAdapter;
import com.example.luiz.sosquality.presentation.contracts.ContractRecomendation;
import com.example.luiz.sosquality.presentation.core.BaseFragment;
import com.example.luiz.sosquality.presentation.presenter.RecomendationPresenter;
import com.example.luiz.sosquality.presentation.presenter.commons.CommunicatePresenterRecomendationItem;
import com.example.luiz.sosquality.utils.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.luiz.sosquality.presentation.activities.RecomendationActivity.SUBCATEGORY_INTENT;
import static com.google.common.base.Preconditions.checkNotNull;

public class RecomendationFragment extends BaseFragment implements ContractRecomendation.View {

    @BindView(R.id.recomendation_list) RecyclerView recomendationList;
    @BindView(R.id.recomendationLL) LinearLayout recomendationLL;
    @BindView(R.id.noRecomendationsMain) TextView noRecomendationsMain;
    @BindView(R.id.noRecomendations) LinearLayout noRecomendations;
    @BindView(R.id.recomendationContainer) RelativeLayout recomendationContainer;
    @BindView(R.id.refresh_layout_recomendation) ScrollChildSwipeRefreshLayout refreshLayoutRecomendation;

    private Unbinder unbinder;
    private ContractRecomendation.Presenter mPresenter;
    private RecomendationAdapter recomendationAdapter;
    private LinearLayoutManager linearLayoutManager;

    private SubcategoryEntity subcategoryEntity;

    public RecomendationFragment() {}

    public static RecomendationFragment newInstance(SubcategoryEntity subcategoryEntity) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(SUBCATEGORY_INTENT, subcategoryEntity);

        RecomendationFragment fragment = new RecomendationFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start(subcategoryEntity.getIdSubcategoria());
        initView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Bundle argument = getArguments();
        subcategoryEntity = (SubcategoryEntity) argument.getSerializable(SUBCATEGORY_INTENT);

        mPresenter = new RecomendationPresenter(this, getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_layout_recomendation, container, false);
        unbinder = ButterKnife.bind(this, root);
        noRecomendations.setVisibility(View.GONE);

        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout_recomendation);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(recomendationList);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.llamadaRecomendaciones(true, subcategoryEntity.getIdSubcategoria());
            }
        });

        return root;
    }

    @Override
    protected void initView() {
        super.initView();

        setHasOptionsMenu(true);
        mPresenter = new RecomendationPresenter(this, getContext());
        recomendationList.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recomendationList.setLayoutManager(linearLayoutManager);
        recomendationAdapter = new RecomendationAdapter(
                getContext(),
                new ArrayList<RecomendationEntity>(),
                (CommunicatePresenterRecomendationItem) mPresenter
        );
        recomendationList.setAdapter(recomendationAdapter);
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
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout_recomendation);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showRecomendations(ArrayList<RecomendationEntity> recomendationEntity) {
        recomendationAdapter = new RecomendationAdapter(getContext(), recomendationEntity, (CommunicatePresenterRecomendationItem) mPresenter);

        if (recomendationList != null){
            recomendationList.setAdapter(recomendationAdapter);
        }
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingNewsError() {
        showMessage("No se pudo cargar las recomendaciones");
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(ContractRecomendation.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
