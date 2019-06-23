package com.example.testapp.activities;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.testapp.fragments.CommentsFragment;
import com.example.testapp.fragments.ProductDetailsFragment;
import com.example.testapp.R;
import com.example.testapp.mvp.models.Product;
import com.example.testapp.mvp.presenters.FragmentsPresenter;
import com.example.testapp.mvp.views.FragmentsView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailsActivity extends MvpAppCompatActivity implements FragmentsView {

    public static final String PARCELABLE_PRODUCT = "product";
    public static final int REPLACED_CONTAINER = R.id.parentContainerDetails;

    final static String TAG_FRAGMENT_COMMENTS = "FRAGMENT_COMMENTS";
    final static String TAG_FRAGMENT_DETAILS = "FRAGMENT_DETAILS";


    private Product mProduct;

    @BindView(R.id.nav_view_details)
    BottomNavigationView mBottomNavigationView;

    private CommentsFragment mCommentsFragment;
    private ProductDetailsFragment mProductDetailsFragment;

    @InjectPresenter
    FragmentsPresenter fragmentsPresenter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_detail:
                fragmentsPresenter.selectTab(mProductDetailsFragment, REPLACED_CONTAINER);
                return true;
            case R.id.navigation_comments:
                fragmentsPresenter.selectTab(mCommentsFragment, REPLACED_CONTAINER);
                return true;
        }
        return false;
    };

    private BottomNavigationView.OnNavigationItemReselectedListener onNavigationItemReselectedListener
            = item -> {
        showInfo("Tab is now selected!");
        return;
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        mProduct = getProductIntent();
        if(mProduct != null){
            showInfo(mProduct.getTitle());
        }
        else {
            showInfo("OOPS...");
            return;
        }

        ButterKnife.bind(this);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mBottomNavigationView.setOnNavigationItemReselectedListener(onNavigationItemReselectedListener);

        mCommentsFragment = CommentsFragment.newInstance(mProduct);
        mProductDetailsFragment = ProductDetailsFragment.newInstance(mProduct);

        if(savedInstanceState == null) {
            fragmentsPresenter.selectTab(mProductDetailsFragment, REPLACED_CONTAINER);
        }
    }


    private void showInfo(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private Product getProductIntent(){
        return getIntent().getExtras().getParcelable(PARCELABLE_PRODUCT);
    }




    @Override
    public void selectTab(Fragment fragment, int replaceContainer) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(replaceContainer, fragment);

        transaction.commit();
    }
}
