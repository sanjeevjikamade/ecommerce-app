package com.serviceapps.shopping.ui.fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.serviceapps.shopping.R;
import com.serviceapps.shopping.ui.adapters.SellerOrderPagerAdapter;


public class SellerOrdersBaseFragment extends BaseFragment {

    View myFragment;

    ViewPager viewPager;
    TabLayout tabLayout;


    public SellerOrdersBaseFragment() {
        // Required empty public constructor
    }

    public static SellerOrdersBaseFragment getInstance()    {
        return new SellerOrdersBaseFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.seller_orders_base_layout, container, false);

        viewPager = myFragment.findViewById(R.id.viewPager);
        tabLayout = myFragment.findViewById(R.id.tabLayout);

        return myFragment;
    }

    //Call onActivity Create method


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setUpViewPager(ViewPager viewPager) {
        SellerOrderPagerAdapter adapter = new SellerOrderPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new OrdersFragment(), "Customer Orders");
        adapter.addFragment(new SellerOrdersFragment(), "Your Orders");

        viewPager.setAdapter(adapter);
    }
}
