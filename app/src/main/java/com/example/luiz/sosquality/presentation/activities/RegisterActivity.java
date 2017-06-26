package com.example.luiz.sosquality.presentation.activities;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.example.luiz.sosquality.R;
import com.example.luiz.sosquality.presentation.adapters.CustomPagerAdapter;
import com.example.luiz.sosquality.presentation.contracts.ContractRegister;
import com.example.luiz.sosquality.presentation.fragments.AlergicFragment;
import com.example.luiz.sosquality.presentation.fragments.BasicDatesPatientFragment;
import com.example.luiz.sosquality.presentation.fragments.ContactInformationFragment;
import com.example.luiz.sosquality.presentation.fragments.DiseaseFragment;
import com.example.luiz.sosquality.presentation.presenter.RegisterPresenter;
import com.example.luiz.sosquality.utils.NonSwipeableViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class    RegisterActivity extends AppCompatActivity {

    final static public int BASE_CONTACT_INFORMATION = 1;
    final static public int BASE_USER_ALERGICS = 2;

    private TabLayout tabLayout;
    private NonSwipeableViewPager viewPager;
    public Toolbar toolbar;

    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.layout_content_frame);
        // inflate the custom activity layout
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(R.layout.layout_tab, null, false);

        this.registerPresenter = new RegisterPresenter(this);

        // add the custom layout of this activity to frame layout.
        frameLayout.addView(activityView);
        viewPager = (NonSwipeableViewPager) activityView.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) activityView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onSupportNavigateUp() {
        //onAlert
        onBackPressed();
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(BasicDatesPatientFragment.newInstance(),"Información Básica");
        adapter.addFragment(ContactInformationFragment.newInstance(), "Información de Contacto");
        adapter.addFragment(AlergicFragment.newInstance(), "Alergias");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public void selectFragment(int position){
        viewPager.setCurrentItem(position, true);
        // true is to animate the transaction
    }

}
