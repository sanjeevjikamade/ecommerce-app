// Generated by view binder compiler. Do not edit!
package com.serviceapps.shopping.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputLayout;
import com.serviceapps.shopping.R;
import com.serviceapps.shopping.utils.MSPButton;
import com.serviceapps.shopping.utils.MSPEditText;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAddCampaignBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MSPButton btnSubmit;

  @NonNull
  public final MSPEditText etCampaignDescription;

  @NonNull
  public final MSPEditText etCampaignTitle;

  @NonNull
  public final FrameLayout flCampaignImage;

  @NonNull
  public final ImageView ivAddUpdateCampaign;

  @NonNull
  public final ImageView ivCampaignImage;

  @NonNull
  public final TextInputLayout tilCampaignDescription;

  @NonNull
  public final TextInputLayout tilCampaignTitle;

  @NonNull
  public final Toolbar toolbarAddCampaignActivity;

  @NonNull
  public final TextView tvTitle;

  private ActivityAddCampaignBinding(@NonNull ConstraintLayout rootView,
      @NonNull MSPButton btnSubmit, @NonNull MSPEditText etCampaignDescription,
      @NonNull MSPEditText etCampaignTitle, @NonNull FrameLayout flCampaignImage,
      @NonNull ImageView ivAddUpdateCampaign, @NonNull ImageView ivCampaignImage,
      @NonNull TextInputLayout tilCampaignDescription, @NonNull TextInputLayout tilCampaignTitle,
      @NonNull Toolbar toolbarAddCampaignActivity, @NonNull TextView tvTitle) {
    this.rootView = rootView;
    this.btnSubmit = btnSubmit;
    this.etCampaignDescription = etCampaignDescription;
    this.etCampaignTitle = etCampaignTitle;
    this.flCampaignImage = flCampaignImage;
    this.ivAddUpdateCampaign = ivAddUpdateCampaign;
    this.ivCampaignImage = ivCampaignImage;
    this.tilCampaignDescription = tilCampaignDescription;
    this.tilCampaignTitle = tilCampaignTitle;
    this.toolbarAddCampaignActivity = toolbarAddCampaignActivity;
    this.tvTitle = tvTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAddCampaignBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAddCampaignBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_add_campaign, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAddCampaignBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_submit;
      MSPButton btnSubmit = ViewBindings.findChildViewById(rootView, id);
      if (btnSubmit == null) {
        break missingId;
      }

      id = R.id.et_campaign_description;
      MSPEditText etCampaignDescription = ViewBindings.findChildViewById(rootView, id);
      if (etCampaignDescription == null) {
        break missingId;
      }

      id = R.id.et_campaign_title;
      MSPEditText etCampaignTitle = ViewBindings.findChildViewById(rootView, id);
      if (etCampaignTitle == null) {
        break missingId;
      }

      id = R.id.fl_campaign_image;
      FrameLayout flCampaignImage = ViewBindings.findChildViewById(rootView, id);
      if (flCampaignImage == null) {
        break missingId;
      }

      id = R.id.iv_add_update_campaign;
      ImageView ivAddUpdateCampaign = ViewBindings.findChildViewById(rootView, id);
      if (ivAddUpdateCampaign == null) {
        break missingId;
      }

      id = R.id.iv_campaign_image;
      ImageView ivCampaignImage = ViewBindings.findChildViewById(rootView, id);
      if (ivCampaignImage == null) {
        break missingId;
      }

      id = R.id.til_campaign_description;
      TextInputLayout tilCampaignDescription = ViewBindings.findChildViewById(rootView, id);
      if (tilCampaignDescription == null) {
        break missingId;
      }

      id = R.id.til_campaign_title;
      TextInputLayout tilCampaignTitle = ViewBindings.findChildViewById(rootView, id);
      if (tilCampaignTitle == null) {
        break missingId;
      }

      id = R.id.toolbar_add_campaign_activity;
      Toolbar toolbarAddCampaignActivity = ViewBindings.findChildViewById(rootView, id);
      if (toolbarAddCampaignActivity == null) {
        break missingId;
      }

      id = R.id.tv_title;
      TextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      return new ActivityAddCampaignBinding((ConstraintLayout) rootView, btnSubmit,
          etCampaignDescription, etCampaignTitle, flCampaignImage, ivAddUpdateCampaign,
          ivCampaignImage, tilCampaignDescription, tilCampaignTitle, toolbarAddCampaignActivity,
          tvTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}