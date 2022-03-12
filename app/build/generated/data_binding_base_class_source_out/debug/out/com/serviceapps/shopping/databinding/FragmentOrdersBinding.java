// Generated by view binder compiler. Do not edit!
package com.serviceapps.shopping.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.serviceapps.shopping.R;
import com.serviceapps.shopping.utils.MSPTextView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentOrdersBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final RecyclerView rvMyOrderItems;

  @NonNull
  public final MSPTextView tvNoOrdersFound;

  private FragmentOrdersBinding(@NonNull ConstraintLayout rootView,
      @NonNull RecyclerView rvMyOrderItems, @NonNull MSPTextView tvNoOrdersFound) {
    this.rootView = rootView;
    this.rvMyOrderItems = rvMyOrderItems;
    this.tvNoOrdersFound = tvNoOrdersFound;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentOrdersBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentOrdersBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_orders, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentOrdersBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.rv_my_order_items;
      RecyclerView rvMyOrderItems = ViewBindings.findChildViewById(rootView, id);
      if (rvMyOrderItems == null) {
        break missingId;
      }

      id = R.id.tv_no_orders_found;
      MSPTextView tvNoOrdersFound = ViewBindings.findChildViewById(rootView, id);
      if (tvNoOrdersFound == null) {
        break missingId;
      }

      return new FragmentOrdersBinding((ConstraintLayout) rootView, rvMyOrderItems,
          tvNoOrdersFound);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}