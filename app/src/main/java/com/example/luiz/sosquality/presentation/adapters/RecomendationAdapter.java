package com.example.luiz.sosquality.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luiz.sosquality.R;
import com.example.luiz.sosquality.domain.model.RecomendationEntity;
import com.example.luiz.sosquality.presentation.presenter.commons.CommunicatePresenterRecomendationItem;
import com.example.luiz.sosquality.utils.Util_Fonts;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecomendationAdapter extends RecyclerView.Adapter<RecomendationAdapter.ViewHolder> implements ClickGenericListener{

    private Context context;
    private ArrayList<RecomendationEntity> recomendationEntities;
    private CommunicatePresenterRecomendationItem communicatePresenterRecomendationItem;

    public RecomendationAdapter(Context context, ArrayList<RecomendationEntity> recomendationEntities, CommunicatePresenterRecomendationItem communicatePresenterRecomendatioItem) {
        this.context = context;
        this.recomendationEntities = recomendationEntities;
        LayoutInflater inflater = LayoutInflater.from(context);
        this.communicatePresenterRecomendationItem = communicatePresenterRecomendatioItem;
    }

    @Override
    public RecomendationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recomendation_item, parent, false);
        return new RecomendationAdapter.ViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecomendationEntity recomendationEntity = recomendationEntities.get(position);

        holder.tvRecomendationName.setText(recomendationEntity.getParte() + ".  " + recomendationEntity.getDescripcion());

        holder.tvRecomendationName.setTypeface(Util_Fonts.setFontLight(context));

        /*if (recomendationEntity.getURLFoto() != null)
            Glide.with(context).load(recomendationEntity.getURLFoto()).into(holder.logoSubcategory);
        else {
            holder.logoSubcategory.setImageDrawable(null);
        }*/
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return recomendationEntities.size();
    }

    @Override
    public void onClick(int position) {
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.logo_recomendacion) ImageView logoRecomendacion;
        @BindView(R.id.tv_recomendation_name) TextView tvRecomendationName;


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
