package com.innovation.rain.feature.rica.poa.address.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by AnhVu on 18-Oct-2018.
 */
@Parcelize
data class Address(var address: String = "",
                   var streetAddress: String,
                   var suburb: String,
                   var town: String,
                   var province: String) : Parcelable