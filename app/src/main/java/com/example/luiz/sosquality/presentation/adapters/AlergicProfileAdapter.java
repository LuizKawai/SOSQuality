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
import com.example.luiz.sosquality.domain.model.AlergyEntity;
import com.example.luiz.sosquality.presentation.presenter.commons.CommunicatePresenterAlergicProfileItem;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luiz on 12/13/2016.
 */

public class AlergicProfileAdapter extends RecyclerView.Adapter<AlergicProfileAdapter.ViewHolder> implements ClickGenericListener{

    private Context context;
    private ArrayList<AlergyEntity> alergyEntities;
    private CommunicatePresenterAlergicProfileItem communicatePresenterAlergicProfileItem;

    public AlergicProfileAdapter(Context context, ArrayList<AlergyEntity> alergyEntities, CommunicatePresenterAlergicProfileItem communicatePresenterAlergicProfileItem) {
        this.context = context;
        this.alergyEntities = alergyEntities;
        LayoutInflater inflater = LayoutInflater.from(context);
        this.communicatePresenterAlergicProfileItem = communicatePresenterAlergicProfileItem;
    }

    @Override
    public AlergicProfileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.alergic_item, parent, false);
        return new AlergicProfileAdapter.ViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(AlergicProfileAdapter.ViewHolder holder, int position) {
        AlergyEntity alergyEntity = alergyEntities.get(position);

        holder.tvAlergicProfileName.setText(alergyEntity.getNombreAlergia());
        holder.tvAlergicProfileMedicine.setText(alergyEntity.getMedicacion());

        Glide.with(context).load(R.drawable.alergias).into(holder.logoAlergic);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return alergyEntities.size();
    }

    @Override
    public void onClick(int position) {
        AlergyEntity alergyEntity = alergyEntities.get(position);
        communicatePresenterAlergicProfileItem.clickContactItem(alergyEntity);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.logo_alergic) ImageView logoAlergic;
        @BindView(R.id.tv_alergic_profile_name) TextView tvAlergicProfileName;
        @BindView(R.id.tv_alergic_profile_medicine) TextView tvAlergicProfileMedicine;

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
