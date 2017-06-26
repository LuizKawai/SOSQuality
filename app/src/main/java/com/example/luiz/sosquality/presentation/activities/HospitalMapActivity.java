package com.example.luiz.sosquality.presentation.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.luiz.sosquality.R;
import com.example.luiz.sosquality.domain.model.HospitalEntity;
import com.example.luiz.sosquality.domain.remote.ServiceGeneratorSimple;
import com.example.luiz.sosquality.domain.remote.request.HostipalRequest;

import com.example.luiz.sosquality.presentation.core.BaseActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalMapActivity extends BaseActivity implements OnMapReadyCallback{

    private MapFragment googleMap;
    private ArrayList<HospitalEntity> hospitalEntities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_map);
        ButterKnife.bind(this);

        try {
            hospitalEntities= new ArrayList<>();
            initHospitals();
            initilizeMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initHospitals(){
        final HostipalRequest mapRequest =
                ServiceGeneratorSimple.createService(HostipalRequest.class);

        Call<ArrayList<HospitalEntity>> call = mapRequest.getHospital();

        call.enqueue(new Callback<ArrayList<HospitalEntity>>() {
            @Override
            public void onResponse(Call<ArrayList<HospitalEntity>> call, Response<ArrayList<HospitalEntity>> response) {
                if (response.isSuccessful()){
                    for (int i=0; i<response.body().size(); i++){
                        hospitalEntities.add(response.body().get(i));
                    }
                } else {
                }
            }
            @Override
            public void onFailure(Call<ArrayList<HospitalEntity>> call, Throwable t) {
            }
        });
    }

    @OnClick(R.id.btn_close)
    public void closeActivity() {
        HospitalMapActivity.this.finish();
    }

    private void initilizeMap() {

        if (googleMap == null) {
            googleMap = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
            googleMap.getMapAsync(this);

            CameraPosition camPos = new CameraPosition.Builder()
                    .target(new LatLng(-12.058471,
                            -77.084949))
                    .zoom(14)
                    .build();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (hospitalEntities != null)
            for (int i = 0; i < hospitalEntities.size(); i++) {
                googleMap.addMarker(new MarkerOptions()
                        .position(
                                new LatLng(hospitalEntities.get(i).getLatitud(),
                                        hospitalEntities.get(i).getLongitud()))
                        .title(hospitalEntities.get(i).getNombreCentAten())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            }

        if (googleMap == null) {
            Toast.makeText(getApplicationContext(), "Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            initilizeMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.btn_close, R.id.btn_car})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                this.finish();
                break;
            case R.id.btn_car:
                break;
        }
    }
}
