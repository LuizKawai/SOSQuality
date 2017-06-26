package com.example.luiz.sosquality.presentation.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.luiz.sosquality.R;
import com.example.luiz.sosquality.domain.model.CategoryEntity;
import com.example.luiz.sosquality.presentation.presenter.commons.CommunicatePresenterCategoryItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> implements ClickGenericListener {

    private Context context;
    private ArrayList<CategoryEntity> categoryEntities;
    private CommunicatePresenterCategoryItem communicatePresenterCategoryItem;

    public CategoryAdapter(Context context, ArrayList<CategoryEntity> categoryEntities,
                           CommunicatePresenterCategoryItem communicatePresenterCategoryItem) {
        this.context = context;
        this.categoryEntities = categoryEntities;
        LayoutInflater inflater = LayoutInflater.from(context);
        this.communicatePresenterCategoryItem = communicatePresenterCategoryItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CategoryEntity categoryEntity = categoryEntities.get(position);

        holder.tvCategoryName.setText(categoryEntity.getNombre());

        if (categoryEntity.getURLFoto() != null)
            Glide.with(context).load(categoryEntity.getURLFoto()).into(holder.logoCategory);
        else{
            holder.logoCategory.setImageDrawable(null);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return categoryEntities.size();
    }

    @Override
    public void onClick(int position) {
        CategoryEntity categoryEntity = categoryEntities.get(position);
        communicatePresenterCategoryItem.clickCategoryItem(categoryEntity);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.logo_categoria) ImageView logoCategory;
        @BindView(R.id.tv_category_name) TextView tvCategoryName;

        ClickGenericListener clickGenericListener;

        public ViewHolder(View view, ClickGenericListener clickGenericListener) {
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