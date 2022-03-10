package com.serviceapps.shopping.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * A data model class for Product with required fields.
 */
@Parcelize
data class Campaign(
    val user_id: String = "",
    val user_name: String = "",
    val title: String = "",
    val description: String = "",
    val image: String = "",
    var campaign_id: String = "",
) : Parcelable