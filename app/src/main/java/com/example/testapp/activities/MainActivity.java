package com.example.testapp.activities;

import android.os.Bundle;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.testapp.fragments.ProductsFragment;
import com.example.testapp.R;
import com.example.testapp.fragments.ProfileFragment;
import com.example.testapp.mvp.views.FragmentsView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpAppCompatActivity implements FragmentsView {

    private static final int REPLACE_CONTAINER = R.id.parentContainer;

    @BindView(R.id.nav_view)
    BottomNavigationView mBottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                    selectTab(new ProductsFragment(), REPLACE_CONTAINER);
                        return true;
                    case R.id.navigation_dashboard:
                        selectTab(new ProfileFragment(), REPLACE_CONTAINER);
                        return true;
                }
                return false;
            };

    private BottomNavigationView.OnNavigationItemReselectedListener mOnNavigationItemReselectedListener
            = item -> {
        showInfo("Tab is now selected!");
        return;
    };

    private void showInfo(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mBottomNavigationView.setOnNavigationItemReselectedListener(mOnNavigationItemReselectedListener);
        if(savedInstanceState == null){
            selectTab(new ProductsFragment(), REPLACE_CONTAINER);
        }
    }

    @Override
    public void selectTab(Fragment fragment, int replaceContainer) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(replaceContainer, fragment);

        transaction.commit();
    }
}
