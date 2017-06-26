package com.example.luiz.sosquality.presentation.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luiz.sosquality.R;
import com.example.luiz.sosquality.presentation.contracts.ContractLanding;
import com.example.luiz.sosquality.utils.Util_Fonts;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

public class SearchCodeDialog extends AlertDialog  implements Validator.ValidationListener {

    private Validator validator;
    private ContractLanding.View mView;
    private String field;

    @NotEmpty(message = "Este campo no puede ser vacío")
    @Length( min=8,max = 8, message = "DNI tiene 8 caracteres ")
    private
    EditText editTextDNI;
    private Button btnSearch;

    protected SearchCodeDialog(Context context, ContractLanding.View landing, String field) {
        super(context);
        this.mView = landing;
        this.field=field;
        initDialog();
    }

    private void initDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_address, null);
        setView(view);

        btnSearch = (Button) view.findViewById(R.id.btn_search);
        editTextDNI = (EditText) view.findViewById(R.id.tv_dni);

        validator = new Validator(this);
        validator.setValidationListener(this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
    }

    @Override
    public void onValidationSucceeded() {
        this.dismiss();
        if(field.equals("1"))
            mView.searchUser(editTextDNI.getText().toString());
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
