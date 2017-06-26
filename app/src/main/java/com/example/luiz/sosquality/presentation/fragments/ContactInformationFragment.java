package com.example.luiz.sosquality.presentation.fragments;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luiz.sosquality.R;
import com.example.luiz.sosquality.presentation.activities.RegisterActivity;
import com.example.luiz.sosquality.presentation.core.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by luiz on 11/14/2016.
 */

public class ContactInformationFragment extends BaseFragment {

    Context context;

    public static final int PICK_CONTACT_FIRST_REQUEST = 1;
    public static final int PICK_CONTACT_SECOND_REQUEST = 2;
    public static final int PICK_CONTACT_THIRD_REQUEST = 3;

    public static String contacto1;
    public static String contacto2;
    public static String contacto3;
    public static String numero1;
    public static String numero2;
    public static String numero3;

    @BindView(R.id.nombre_contacto_uno) TextView nombreContactoUno;
    @BindView(R.id.numero_contacto_uno) TextView numeroContactoUno;
    @BindView(R.id.contacto_uno) LinearLayout contactoUno;
    @BindView(R.id.nombre_oontacto_dos) TextView nombreContactoDos;
    @BindView(R.id.numero_contacto_dos) TextView numeroContactoDos;
    @BindView(R.id.contacto_dos) LinearLayout contactoDos;
    @BindView(R.id.nombre_contacto_tres) TextView nombreContactoTres;
    @BindView(R.id.numero_contacto_tres) TextView numeroContactoTres;
    @BindView(R.id.contacto_tres) LinearLayout contactoTres;
    @BindView(R.id.btn_next_alergic) Button btnNextAlergic;

    private Uri contactUri;

    public ContactInformationFragment() {
        // Requires empty public constructor
    }

    public static ContactInformationFragment newInstance() {
        return new ContactInformationFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_layout_contact_information, container, false);
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

    @OnClick(R.id.btn_next_alergic)
    public void OnCLick(View view) {
        switch (view.getId()) {
            case R.id.btn_next_alergic:
                ((RegisterActivity) getActivity()).selectFragment(RegisterActivity.BASE_USER_ALERGICS);
                break;
        }
    }

    @OnClick({R.id.contacto_uno, R.id.contacto_dos, R.id.contacto_tres})
    public void onClick(View view) {
        Intent getContactoIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        switch (view.getId()) {
            case R.id.contacto_uno:
                startActivityForResult(getContactoIntent, PICK_CONTACT_FIRST_REQUEST);
                break;
            case R.id.contacto_dos:
                startActivityForResult(getContactoIntent, PICK_CONTACT_SECOND_REQUEST);
                break;
            case R.id.contacto_tres:
                startActivityForResult(getContactoIntent, PICK_CONTACT_THIRD_REQUEST);
                break;
        }
    }

    private void renderContact1(Uri uri) {
        nombreContactoUno.setText(getNombre(uri));
        numeroContactoUno.setText(getTelefono(uri));

        contacto1 = nombreContactoUno.getText().toString();
        numero1 = numeroContactoUno.getText().toString();
    }

    private void renderContact2(Uri uri) {
        nombreContactoDos.setText(getNombre(uri));
        numeroContactoDos.setText(getTelefono(uri));

        contacto2 = nombreContactoDos.getText().toString();
        numero2 = numeroContactoDos.getText().toString();
    }

    private void renderContact3(Uri uri) {
        nombreContactoTres.setText(getNombre(uri));
        numeroContactoTres.setText(getTelefono(uri));

        contacto3 = nombreContactoTres.getText().toString();
        numero3 = numeroContactoTres.getText().toString();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_CONTACT_FIRST_REQUEST) {
                contactUri = intent.getData();
                renderContact1(contactUri);
            } else if (requestCode == PICK_CONTACT_SECOND_REQUEST) {
                contactUri = intent.getData();
                renderContact2(contactUri);
            } else if (requestCode == PICK_CONTACT_THIRD_REQUEST){
                contactUri = intent.getData();
                renderContact3(contactUri);
            }

        } else {
            Toast.makeText(getContext(), "Contactos no disponible", Toast.LENGTH_SHORT).show();
        }
    }

    private String getTelefono(Uri uri) {
        String id = null;
        String telefono = null;

        // obtener id de contacto
        Cursor contactCursor = context.getContentResolver().query(
                uri,
                new String[]{ContactsContract.Contacts._ID},
                null,
                null,
                null);

        if (contactCursor.moveToFirst()) {
            id = contactCursor.getString(0);
        }
        contactCursor.close();

        //Sentencia where, se especifica que variables queremos
        String selectionArgs =
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                        ContactsContract.CommonDataKinds.Phone.TYPE+"= " +
                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE;

        // obtener el numero telefonico
        Cursor phoneCursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[] { ContactsContract.CommonDataKinds.Phone.NUMBER },
                selectionArgs,
                new String[] { id },
                null
        );
        if (phoneCursor.moveToFirst()) {
            telefono = phoneCursor.getString(0);
        }
        phoneCursor.close();

        return telefono;
    }

    private String getNombre(Uri uri) {

        String nombre = null;

        ContentResolver contentResolver = context.getContentResolver();

        //Nombre del contacto
        Cursor c = contentResolver.query(
                uri,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME},
                null,
                null,
                null);

        if(c.moveToFirst()){
            nombre = c.getString(0);
        }

        c.close();

        return nombre;
    }
}
