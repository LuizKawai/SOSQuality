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
import com.example.luiz.sosquality.presentation.activities.SubcategoryActivity;
import com.example.luiz.sosquality.presentation.adapters.CategoryAdapter;
import com.example.luiz.sosquality.presentation.contracts.ContractFirtsAid;
import com.example.luiz.sosquality.presentation.core.BaseFragment;
import com.example.luiz.sosquality.presentation.presenter.FirstAidPresenter;
import com.example.luiz.sosquality.presentation.presenter.commons.CommunicatePresenterCategoryItem;
import com.example.luiz.sosquality.utils.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.google.common.base.Preconditions.checkNotNull;

public class FirstAidFragment extends BaseFragment implements ContractFirtsAid.View {

    @BindView(R.id.category_list) RecyclerView categoryList;
    @BindView(R.id.categoryLL) LinearLayout categoryLL;
    @BindView(R.id.noNewsMain) TextView noNewsMain;
    @BindView(R.id.noNews) LinearLayout noNews;
    @BindView(R.id.categoryContainer) RelativeLayout categoryContainer;
    @BindView(R.id.refresh_category_layout) ScrollChildSwipeRefreshLayout refreshLayout;

    private Unbinder unbinder;
    private ContractFirtsAid.Presenter mPresenter;
    private CategoryAdapter categoryAdapter;
    private LinearLayoutManager linearLayoutManager;

    public FirstAidFragment() {
        // Requires empty public constructor
    }

    public static FirstAidFragment newInstance() {
        return new FirstAidFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        initView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new FirstAidPresenter(this, getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_layout_first_aid, container, false);
        unbinder = ButterKnife.bind(this, root);
        noNews.setVisibility(View.GONE);

        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_category_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(categoryList);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.llamadaCategorias(true);
            }
        });

        return root;
    }

    @Override
    protected void initView() {
        super.initView();

        setHasOptionsMenu(true);
        mPresenter = new FirstAidPresenter(this, getContext());
        categoryList.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryList.setLayoutManager(linearLayoutManager);

        categoryAdapter = new CategoryAdapter(getContext(),
                new ArrayList<CategoryEntity>(),
                (CommunicatePresenterCategoryItem) mPresenter
        );
        categoryList.setAdapter(categoryAdapter);
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
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_category_layout);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showCategories(ArrayList<CategoryEntity> categoryEntity) {
        categoryAdapter = new CategoryAdapter(getContext(), categoryEntity, (CommunicatePresenterCategoryItem) mPresenter);

        if (categoryList != null){
            categoryList.setAdapter(categoryAdapter);
        }
    }

    //para ver las subcategorias XD
    @Override
    public void showSubcategoryForCategory(CategoryEntity categoryEntity) {
        Intent intent = new Intent(getContext(), SubcategoryActivity.class);
        intent.putExtra(SubcategoryActivity.EXTRA_NEWS, categoryEntity);
        startActivity(intent);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingNewsError() {
        showMessage("No se pudo cargar la lista de categorias");
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(ContractFirtsAid.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
