package com.example.luiz.sosquality.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.luiz.sosquality.R;
import com.example.luiz.sosquality.presentation.activities.RegisterActivity;
import com.example.luiz.sosquality.presentation.core.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

/**
 * Created by luiz on 11/14/2016.
 */

public class BasicDatesPatientFragment extends BaseFragment {

    public static String sexo = "";
    public static String tipoSangre = "";
    public static String nombreUsuario = "";
    public static int dni;
    public static float peso;
    public static float talla;

    @BindView(R.id.campo_nombre) EditText campoNombre;
    @BindView(R.id.campo_dni) EditText campoDni;
    @BindView(R.id.campo_peso) EditText campoPeso;
    @BindView(R.id.campo_talla) EditText campoTalla;
    @BindView(R.id.sexoM) RadioButton sexoM;
    @BindView(R.id.sexoF) RadioButton sexoF;
    @BindView(R.id.radiogroup_sex) RadioGroup radiogroupSex;
    @BindView(R.id.btn_next_contact) Button btnNextContact;
    @BindView(R.id.spinner) Spinner tipoSangreSpinner;

    public BasicDatesPatientFragment() {
        // Requires empty public constructor
    }

    public static BasicDatesPatientFragment newInstance() {
        return new BasicDatesPatientFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_layout_basic_dates_patient_information, container, false);
        ButterKnife.bind(this, root);

        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadSpinnerIdTypes();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.btn_next_contact)
    public void OnCLick(View view) {
        switch (view.getId()) {
            case R.id.btn_next_contact:
                if (!comprobarCampos()){
                    asignarCampos();
                    ((RegisterActivity) getActivity()).selectFragment(RegisterActivity.BASE_CONTACT_INFORMATION);
                }
                break;
        }
    }

    RadioButton rb;
    @OnClick({R.id.sexoM, R.id.sexoF})
    public void onClick(View view) {
        int rbid = radiogroupSex.getCheckedRadioButtonId();
        rb = (RadioButton) view.findViewById(rbid);

        sexo = rb.getText().toString();
    }

    private void loadSpinnerIdTypes() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.blood_type_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoSangreSpinner.setAdapter(adapter);
    }

    @OnItemSelected(R.id.spinner)
    public void spinnerItemSelected(Spinner spinner, int position) {
        tipoSangre = spinner.getItemAtPosition(position).toString();
    }

    private void asignarCampos() {
        nombreUsuario = campoNombre.getText().toString();
        dni = Integer.parseInt(campoDni.getText().toString());
        peso = Float.parseFloat(campoPeso.getText().toString());
        talla = Float.parseFloat(campoTalla.getText().toString());
    }
    private boolean comprobarCampos(){

        boolean valor = false;

        if (campoNombre.getText().toString().equals("") || campoNombre.getText().toString().equals(null)) {
            campoNombre.setError("Campo Obligatorio");
            valor = true;
        }
        if (campoDni.getText().toString().equals("") || campoDni.getText().toString().equals(null)) {
            campoNombre.setError("Campo Obligatorio");
            valor = true;
        }
        if (campoTalla.getText().toString().equals("") || campoTalla.getText().toString().equals(null)) {
            campoNombre.setError("Campo Obligatorio");
            valor = true;
        }
        if (campoPeso.getText().toString().equals("") || campoPeso.getText().toString().equals(null)) {
            campoNombre.setError("Campo Obligatorio");
            valor = true;
        }
        if (sexo.equals("") || sexo.equals(null)) {
            Toast.makeText(getContext(), "Seleccionar un Sexo", Toast.LENGTH_SHORT).show();
            valor = true;
        }

        return valor;
    }
}
