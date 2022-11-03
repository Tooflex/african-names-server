package com.tooflexdev.prenomsafricains.mailchimp

import com.google.gson.annotations.SerializedName


data class Location (

  @SerializedName("latitude"     ) var latitude    : Int?    = null,
  @SerializedName("longitude"    ) var longitude   : Int?    = null,
  @SerializedName("gmtoff"       ) var gmtoff      : Int?    = null,
  @SerializedName("dstoff"       ) var dstoff      : Int?    = null,
  @SerializedName("country_code" ) var countryCode : String? = null,
  @SerializedName("timezone"     ) var timezone    : String? = null,
  @SerializedName("region"       ) var region      : String? = null

)