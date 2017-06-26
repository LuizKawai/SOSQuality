package com.example.luiz.sosquality.presentation.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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
import com.example.luiz.sosquality.domain.model.ContactEntity;
import com.example.luiz.sosquality.domain.model.UserEntity;
import com.example.luiz.sosquality.presentation.activities.MainActivity;
import com.example.luiz.sosquality.presentation.adapters.ContactProfileAdapter;
import com.example.luiz.sosquality.presentation.contracts.ContractContactProfile;
import com.example.luiz.sosquality.presentation.core.BaseFragment;
import com.example.luiz.sosquality.presentation.presenter.ContactProfilePresenter;
import com.example.luiz.sosquality.presentation.presenter.commons.CommunicatePresenterContactProfileItem;
import com.example.luiz.sosquality.utils.ScrollChildSwipeRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.luiz.sosquality.presentation.activities.ContactProfileActivity.CONTACT_LIST;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.security.AccessController.getContext;

/**
 * Created by luiz on 12/13/2016.
 */

public class ContactProfileFragment extends BaseFragment implements ContractContactProfile.View {

    @BindView(R.id.contact_list) RecyclerView contactList;
    @BindView(R.id.contactLL) LinearLayout contactLL;
    @BindView(R.id.noContactsMain) TextView noContactsMain;
    @BindView(R.id.noContacts) LinearLayout noContacts;
    @BindView(R.id.contactContainer) RelativeLayout contactContainer;
    @BindView(R.id.refresh_layout_contact) ScrollChildSwipeRefreshLayout refreshLayoutContact;

    private Unbinder unbinder;
    private ContractContactProfile.Presenter mPresenter;
    private ContactProfileAdapter contactProfileAdapter;
    private LinearLayoutManager linearLayoutManager;

    private UserEntity userEntity;

    public ContactProfileFragment() {
    }

    public static ContactProfileFragment newInstance(UserEntity userEntity) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(CONTACT_LIST, userEntity);

        ContactProfileFragment fragment = new ContactProfileFragment();
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
        userEntity = (UserEntity) argument.getSerializable(CONTACT_LIST);

        mPresenter = new ContactProfilePresenter(this, getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_layout_contact_profile, container, false);
        unbinder = ButterKnife.bind(this, root);
        noContacts.setVisibility(View.GONE);

        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout_contact);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(contactList);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.llamadaContactos(true, userEntity.getDNIUsuario());
            }
        });

        return root;
    }

    @Override
    protected void initView() {
        super.initView();

        setHasOptionsMenu(true);
        mPresenter = new ContactProfilePresenter(this, getContext());
        contactList.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        contactList.setLayoutManager(linearLayoutManager);
        contactProfileAdapter = new ContactProfileAdapter(
                getContext(),
                new ArrayList<ContactEntity>(),
                (CommunicatePresenterContactProfileItem) mPresenter
        );
        contactList.setAdapter(contactProfileAdapter);
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
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout_contact);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showContacts(ArrayList<ContactEntity> contactEntities) {
        contactProfileAdapter = new ContactProfileAdapter(getContext(), contactEntities, (CommunicatePresenterContactProfileItem) mPresenter);

        if (contactList != null) {
            contactList.setAdapter(contactProfileAdapter);
        }
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingNewsError() {
        showMessage("No se pudo cargar la lista de contactos");
    }

    @Override
    public void makeCall(ContactEntity contactEntity) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(contactEntity.getNumeroTelef()));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(ContractContactProfile.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
