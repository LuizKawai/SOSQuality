package com.example.luiz.sosquality.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.luiz.sosquality.R;
import com.example.luiz.sosquality.domain.model.SubcategoryEntity;
import com.example.luiz.sosquality.presentation.presenter.commons.CommunicatePresenterSubcategoryItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryAdapter.ViewHolder> implements ClickGenericListener {

    private Context context;
    private ArrayList<SubcategoryEntity> subcategoryEntities;
    private CommunicatePresenterSubcategoryItem communicatePresenterSubategoryItem;

    public SubcategoryAdapter(Context context, ArrayList<SubcategoryEntity> subcategoryEntities, CommunicatePresenterSubcategoryItem communicatePresenterSubategoryItem) {
        this.context = context;
        this.subcategoryEntities = subcategoryEntities;
        LayoutInflater inflater = LayoutInflater.from(context);
        this.communicatePresenterSubategoryItem = communicatePresenterSubategoryItem;
    }

    @Override
    public SubcategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.subcategory_item, parent, false);
        return new SubcategoryAdapter.ViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(SubcategoryAdapter.ViewHolder holder, int position) {
        SubcategoryEntity subcategoryEntity = subcategoryEntities.get(position);

        holder.tvSubcategoryName.setText(subcategoryEntity.getNombre());

        if (subcategoryEntity.getURLFoto() != null)
            Glide.with(context).load(subcategoryEntity.getURLFoto()).into(holder.logoSubcategory);
        else {
            holder.logoSubcategory.setImageDrawable(null);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return subcategoryEntities.size();
    }

    @Override
    public void onClick(int position) {
        SubcategoryEntity subcategoryEntity = subcategoryEntities.get(position);
        communicatePresenterSubategoryItem.clickSubcategoryItem(subcategoryEntity);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.logo_subcategoria) ImageView logoSubcategory;
        @BindView(R.id.tv_subcategory_name) TextView tvSubcategoryName;

        ClickGenericListener clickGenericListener;

        ViewHolder(View view, ClickGenericListener clickGenericListener) {
            super(view);
            this.clickGenericListener = clickGenericListener;
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickGenericListener.onClick(getAdapterPosition());
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
