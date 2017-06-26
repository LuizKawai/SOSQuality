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
import com.example.luiz.sosquality.domain.model.ContactEntity;
import com.example.luiz.sosquality.presentation.presenter.commons.CommunicatePresenterContactProfileItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luiz on 12/13/2016.
 */

public class ContactProfileAdapter extends RecyclerView.Adapter<ContactProfileAdapter.ViewHolder> implements ClickGenericListener {

        private Context context;
        private ArrayList<ContactEntity> contactEntities;
        private CommunicatePresenterContactProfileItem communicatePresenterContactProfileItem;

        public ContactProfileAdapter(Context context, ArrayList<ContactEntity> contactEntities, CommunicatePresenterContactProfileItem communicatePresenterContactProfileItem) {
            this.context = context;
            this.contactEntities = contactEntities;
            LayoutInflater inflater = LayoutInflater.from(context);
            this.communicatePresenterContactProfileItem = communicatePresenterContactProfileItem;
        }

    @Override
    public ContactProfileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactProfileAdapter.ViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(ContactProfileAdapter.ViewHolder holder, int position) {
        ContactEntity contactEntity = contactEntities.get(position);

        holder.tvContactProfileName.setText(contactEntity.getNombreContacto());
        holder.tvContactProfileNumber.setText(contactEntity.getNumeroTelef());

        Glide.with(context).load(R.drawable.banner_contacto).into(holder.logoContact);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return contactEntities.size();
    }

    @Override
    public void onClick(int position) {
        ContactEntity contactEntity = contactEntities.get(position);
        communicatePresenterContactProfileItem.clickContactItem(contactEntity);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.logo_contact) ImageView logoContact;
        @BindView(R.id.tv_contact_profile_name) TextView tvContactProfileName;
        @BindView(R.id.tv_contact_profile_number) TextView tvContactProfileNumber;

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
