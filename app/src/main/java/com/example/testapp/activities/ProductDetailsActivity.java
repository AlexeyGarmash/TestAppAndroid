package com.example.testapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.testapp.adapters.ViewPagerAdapter;
import com.example.testapp.fragments.CommentsFragment;
import com.example.testapp.fragments.ProductDetailsFragment;
import com.example.testapp.R;
import com.example.testapp.fragments.ProductsFragment;
import com.example.testapp.fragments.ProfileFragment;
import com.example.testapp.mvp.models.Product;
import com.example.testapp.mvp.presenters.FragmentsPresenter;
import com.example.testapp.mvp.views.FragmentsView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailsActivity extends AppCompatActivity {

    public static final String PARCELABLE_PRODUCT = "product";

    private Product mProduct;

    @BindView(R.id.nav_view_details)
    BottomNavigationView mBottomNavigationView;

    @BindView(R.id.pagerProductDetails)
    ViewPager mViewPagerProductDetails;

    private ViewPagerAdapter mViewPagerAdapter;
    private MenuItem mPrevMenuItem;

    private CommentsFragment mCommentsFragment;
    private ProductDetailsFragment mProductDetailsFragment;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_detail:
                mViewPagerProductDetails.setCurrentItem(0);
                //fragmentsPresenter.selectTab(mProductDetailsFragment, REPLACED_CONTAINER);
                return true;
            case R.id.navigation_comments:
                mViewPagerProductDetails.setCurrentItem(1);
                //fragmentsPresenter.selectTab(mCommentsFragment, REPLACED_CONTAINER);
                return true;
        }
        return false;
    };

    private BottomNavigationView.OnNavigationItemReselectedListener onNavigationItemReselectedListener
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        mProduct = getProductIntent();
        if(mProduct == null){
            //showInfo(mProduct.getTitle());
            showInfo("OOPS...");
            return;
        }

        ButterKnife.bind(this);


        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mBottomNavigationView.setOnNavigationItemReselectedListener(onNavigationItemReselectedListener);

        mCommentsFragment = CommentsFragment.newInstance(mProduct);
        mProductDetailsFragment = ProductDetailsFragment.newInstance(mProduct);

        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragment(mProductDetailsFragment);
        mViewPagerAdapter.addFragment(mCommentsFragment);
        mViewPagerProductDetails.setAdapter(mViewPagerAdapter);

        if(savedInstanceState == null) {
            //fragmentsPresenter.selectTab(mProductDetailsFragment, REPLACED_CONTAINER);
            mViewPagerProductDetails.setCurrentItem(0);
        }

        mViewPagerProductDetails.addOnPageChangeListener(mOnPageChangeListener);
    }


    private void showInfo(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private Product getProductIntent(){
        return getIntent().getExtras().getParcelable(PARCELABLE_PRODUCT);
    }
}
