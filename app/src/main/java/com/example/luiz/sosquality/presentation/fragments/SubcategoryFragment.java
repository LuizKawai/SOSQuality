package com.example.luiz.sosquality.presentation.fragments;

import android.content.Intent;
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
import com.example.luiz.sosquality.domain.model.CategoryEntity;
import com.example.luiz.sosquality.domain.model.SubcategoryEntity;
import com.example.luiz.sosquality.presentation.activities.RecomendationActivity;
import com.example.luiz.sosquality.presentation.adapters.SubcategoryAdapter;
import com.example.luiz.sosquality.presentation.contracts.ContractSubcategory;
import com.example.luiz.sosquality.presentation.core.BaseFragment;
import com.example.luiz.sosquality.presentation.presenter.SubcategoryPresenter;
import com.example.luiz.sosquality.presentation.presenter.commons.CommunicatePresenterSubcategoryItem;
import com.example.luiz.sosquality.utils.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.luiz.sosquality.presentation.activities.SubcategoryActivity.EXTRA_NEWS;
import static com.google.common.base.Preconditions.checkNotNull;

public class SubcategoryFragment extends BaseFragment implements ContractSubcategory.View {

    @BindView(R.id.subcategory_list) RecyclerView subcategoryList;
    @BindView(R.id.subcategoryLL) LinearLayout subcategoryLL;
    @BindView(R.id.noSubcategoriesMain) TextView noSubcategoriesMain;
    @BindView(R.id.noSubcategories) LinearLayout noSubcategories;
    @BindView(R.id.subcategoryContainer) RelativeLayout subcategoryContainer;
    @BindView(R.id.refresh_layout_subcategory) ScrollChildSwipeRefreshLayout refreshLayoutSubcategory;

    private Unbinder unbinder;
    private ContractSubcategory.Presenter mPresenter;
    private SubcategoryAdapter subcategoryAdapter;
    private LinearLayoutManager linearLayoutManager;

    private CategoryEntity categoryEntity;

    public SubcategoryFragment() {}

    public static SubcategoryFragment newInstance(CategoryEntity categoryEntity) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(EXTRA_NEWS, categoryEntity);

        SubcategoryFragment fragment = new SubcategoryFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start(categoryEntity.getIdCategoria());
        initView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Bundle argument = getArguments();
        categoryEntity = (CategoryEntity) argument.getSerializable(EXTRA_NEWS);

        mPresenter = new SubcategoryPresenter(this, getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_layout_subcategory, container, false);
        unbinder = ButterKnife.bind(this, root);
        noSubcategories.setVisibility(View.GONE);

        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout_subcategory);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(subcategoryList);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.llamadaSubcategorias(true, categoryEntity.getIdCategoria());
            }
        });

        return root;
    }

    @Override
    protected void initView() {
        super.initView();

        setHasOptionsMenu(true);
        mPresenter = new SubcategoryPresenter(this, getContext());
        subcategoryList.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        subcategoryList.setLayoutManager(linearLayoutManager);
        subcategoryAdapter = new SubcategoryAdapter(
                getContext(),
                new ArrayList<SubcategoryEntity>(),
                (CommunicatePresenterSubcategoryItem) mPresenter
        );
        subcategoryList.setAdapter(subcategoryAdapter);
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
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout_subcategory);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showSubategories(ArrayList<SubcategoryEntity> subcategoryEntity) {
        subcategoryAdapter = new SubcategoryAdapter(getContext(), subcategoryEntity, (CommunicatePresenterSubcategoryItem) mPresenter);

        if (subcategoryList != null){
            subcategoryList.setAdapter(subcategoryAdapter);
        }
    }

    @Override
    public void showRecomendationsForSubcategory(SubcategoryEntity subcategoryEntity) {
        Intent intent = new Intent(getContext(), RecomendationActivity.class);
        intent.putExtra(RecomendationActivity.SUBCATEGORY_INTENT, subcategoryEntity);
        startActivity(intent);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingNewsError() {
        showMessage("No se pudo cargar la lista de subcategorias");
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(ContractSubcategory.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
