package com.example.luiz.sosquality.presentation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.luiz.sosquality.R;
import com.example.luiz.sosquality.domain.model.UserEntity;
import com.example.luiz.sosquality.presentation.activities.ProfileActivity;
import com.example.luiz.sosquality.presentation.contracts.ContractRegister;
import com.example.luiz.sosquality.presentation.core.BaseFragment;
import com.example.luiz.sosquality.presentation.presenter.RegisterPresenter;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

import static com.example.luiz.sosquality.presentation.fragments.BasicDatesPatientFragment.sexo;
import static com.example.luiz.sosquality.presentation.fragments.BasicDatesPatientFragment.tipoSangre;

/**
 * Created by luiz on 11/14/2016.
 */

public class AlergicFragment extends BaseFragment {

    @BindView(R.id.alergiaET) EditText alergiaET;
    @BindView(R.id.medicamentoET) EditText medicamentoET;

    private ContractRegister.Presenter registerPresenter;

    public AlergicFragment() {
        // Requires empty public constructor
    }

    public static AlergicFragment newInstance() {
        return new AlergicFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        registerPresenter.start();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerPresenter = new RegisterPresenter(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_layout_alergic_information, container, false);
        ButterKnife.bind(this, root);
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.btn_next_disease)
    public void OnCLick(View view) {
        switch (view.getId()) {
            case R.id.btn_next_disease:
                //((RegisterActivity)getActivity()).selectFragment(RegisterActivity.BASE_USER_DISEASES);

                registerPresenter.regitroUsuario(
                        BasicDatesPatientFragment.dni,
                        BasicDatesPatientFragment.nombreUsuario,
                        BasicDatesPatientFragment.peso,
                        BasicDatesPatientFragment.talla,
                        BasicDatesPatientFragment.sexo,
                        BasicDatesPatientFragment.tipoSangre,
                        "Me siento mal, podrias venir"
                );

                if (!Objects.equals(ContactInformationFragment.contacto1, "")
                        && !Objects.equals(ContactInformationFragment.numero1, "")){
                    registerPresenter.registroContacto(
                            BasicDatesPatientFragment.dni,
                            ContactInformationFragment.contacto1,
                            ContactInformationFragment.numero1
                    );
                }
                if (!Objects.equals(ContactInformationFragment.contacto2, "")
                        && !Objects.equals(ContactInformationFragment.numero2, "")){
                    registerPresenter.registroContacto(
                            BasicDatesPatientFragment.dni,
                            ContactInformationFragment.contacto2,
                            ContactInformationFragment.numero2
                    );
                }
                if (!Objects.equals(ContactInformationFragment.contacto3, "")
                        && !Objects.equals(ContactInformationFragment.numero3, "")){
                    registerPresenter.registroContacto(
                            BasicDatesPatientFragment.dni,
                            ContactInformationFragment.contacto3,
                            ContactInformationFragment.numero3
                    );
                }

                if (!Objects.equals(alergiaET.getText().toString(), "") &&
                !Objects.equals(medicamentoET.getText().toString(), "")){
                    registerPresenter.registroAlergia(
                            BasicDatesPatientFragment.dni,
                            alergiaET.getText().toString(),
                            medicamentoET.getText().toString()
                    );
                }

                UserEntity userEntity = new UserEntity(BasicDatesPatientFragment.dni,
                        BasicDatesPatientFragment.nombreUsuario,
                        BasicDatesPatientFragment.peso,
                        BasicDatesPatientFragment.talla,
                        BasicDatesPatientFragment.sexo,
                        BasicDatesPatientFragment.tipoSangre,
                        "Me siento mal, podrias venir");

                Intent intent = new Intent(getContext(), ProfileActivity.class);
                intent.putExtra(ProfileActivity.USER_INTENT, userEntity);
                startActivity(intent);

                break;
        }
    }
}
