package com.example.luiz.sosquality.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.luiz.sosquality.R;
import com.example.luiz.sosquality.domain.model.ContactEntity;
import com.example.luiz.sosquality.domain.model.UserEntity;
import com.example.luiz.sosquality.domain.remote.ServiceGeneratorSimple;
import com.example.luiz.sosquality.domain.remote.request.ContactRequest;
import com.example.luiz.sosquality.presentation.core.BaseActivity;
import com.example.luiz.sosquality.presentation.fragments.ProfileFragment;
import com.example.luiz.sosquality.utils.ActivityUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends BaseActivity {

    public static final String USER_INTENT = "USER_LOGIN";

    DrawerLayout mDrawer;
    NavigationView navigationView;

    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private UserEntity userEntity;
    private ArrayList<ContactEntity> contactEntities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        /**
         *Setup the DrawerLayout and NavigationView
         */
        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.layout_content_frame);
        mDrawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigation);

        userEntity = (UserEntity) getIntent().getSerializableExtra(USER_INTENT);
        listaContactos(String.valueOf(userEntity.getDNIUsuario()));

        /**
         * Lets inflate the very first fragment
         */
        ProfileFragment profileFragment =
                (ProfileFragment) getSupportFragmentManager().findFragmentById(R.id.layout_content_frame);

        if (profileFragment == null) {
            profileFragment = ProfileFragment.newInstance(userEntity);
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), profileFragment, R.id.layout_content_frame);
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Perfil");
        setupDrawerContent(navigationView);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                    /* host Activity */
                mDrawer,                    /* DrawerLayout object */
                toolbar,
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        );
        mDrawerToggle.syncState();
        mDrawer.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingActionMessage();
            }
        });
    }

    public void floatingActionMessage(){
        for (int i=0; i<contactEntities.size(); i++){
            sendMessage(contactEntities.get(i));
        }
    }

    private void sendMessage(ContactEntity contactEntity){
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(contactEntity.getNumeroTelef(), null, "Llamame, necesito ayuda", null, null);

            Toast.makeText(getApplicationContext(), "Mensajes Enviados", Toast.LENGTH_LONG).show();
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error al enviar Mensajes", Toast.LENGTH_LONG).show();
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    menuItem.setChecked(false);
                    menuItem.setCheckable(false);

                    switch (menuItem.getItemId()) {
                        case R.id.nav_contact:
                            Intent intent_contact_profile = new Intent(getBaseContext(), ContactProfileActivity.class);
                            intent_contact_profile.putExtra(ContactProfileActivity.CONTACT_LIST, userEntity);
                            startActivity(intent_contact_profile);
                            break;
                        case R.id.nav_alergic:
                            Intent intent_alergic_profile = new Intent(getBaseContext(), AlergicProfileActivity.class);
                            intent_alergic_profile.putExtra(AlergicProfileActivity.ALERGIC_LIST, userEntity);
                            startActivity(intent_alergic_profile);
                            break;
                        case R.id.nav_alarm:
                            /*Intent intent_map= new Intent(getBaseContext(), MapActivity.class);
                            startActivity(intent_map);*/
                            break;
                        case R.id.nav_map:
                            Intent intentMap= new Intent(getBaseContext(), HospitalMapActivity.class);
                            startActivity(intentMap);
                            break;
                        default:
                            break;
                    }
                    // Close the navigation drawer when an item is selected.
                    menuItem.setChecked(false);
                    // mDrawerLayout.closeDrawers();
                    return true;
                }
            }
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_log_out:
                Intent intent= new Intent(ProfileActivity.this,LandingActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (this.mDrawer.isDrawerOpen(GravityCompat.START)) {
            this.mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.log_out, menu);
        return true;
    }

    private void listaContactos(String userID){
        final ContactRequest newContactRequest =
                ServiceGeneratorSimple.createService(ContactRequest.class);

        Call<ArrayList<ContactEntity>> call = newContactRequest.getContacts(String.valueOf(userID));

        call.enqueue(new Callback<ArrayList<ContactEntity>>() {
            @Override
            public void onResponse(Call<ArrayList<ContactEntity>> call, Response<ArrayList<ContactEntity>> response) {
                if (response.isSuccessful()){
                    contactEntities.add(response.body().get(0));
                    contactEntities.add(response.body().get(1));
                    contactEntities.add(response.body().get(2));
                } else {
                }
            }
            @Override
            public void onFailure(Call<ArrayList<ContactEntity>> call, Throwable t) {
            }
        });
    }
}