package com.example.testapp.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.testapp.R;
import com.example.testapp.adapters.ViewPagerAdapter;
import com.example.testapp.fragments.ProductsFragment;
import com.example.testapp.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.nav_view)
    BottomNavigationView mBottomNavigationView;

    @BindView(R.id.pagerMain)
    ViewPager mViewPagerMain;

    private ViewPagerAdapter mViewPagerAdapter;
    private MenuItem mPrevMenuItem;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                    //selectTab(new ProductsFragment(), REPLACE_CONTAINER);
                        mViewPagerMain.setCurrentItem(0);
                        return true;
                    case R.id.navigation_dashboard:
                        //selectTab(new ProfileFragment(), REPLACE_CONTAINER);
                        mViewPagerMain.setCurrentItem(1);
                        return true;
                }
                return false;
            };

    private BottomNavigationView.OnNavigationItemReselectedListener mOnNavigationItemReselectedListener
            = item -> {
        showInfo("Tab is now selected!");
        return;
    };
    private final ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (mPrevMenuItem != null) {
                mPrevMenuItem.setChecked(false);
            }
            else
            {
                mBottomNavigationView.getMenu().getItem(0).setChecked(false);
            }
            Log.d("page", "onPageSelected: "+position);
            mBottomNavigationView.getMenu().getItem(position).setChecked(true);
            mPrevMenuItem = mBottomNavigationView.getMenu().getItem(position);
            mViewPagerAdapter.refreshFragment(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void showInfo(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragment(new ProductsFragment());
        mViewPagerAdapter.addFragment(new ProfileFragment());
        mViewPagerMain.setAdapter(mViewPagerAdapter);

        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mBottomNavigationView.setOnNavigationItemReselectedListener(mOnNavigationItemReselectedListener);

        if(savedInstanceState == null){
            //selectTab(new ProductsFragment(), REPLACE_CONTAINER);
            mViewPagerMain.setCurrentItem(0);
        }
        mViewPagerMain.addOnPageChangeListener(mOnPageChangeListener);

    }
}
