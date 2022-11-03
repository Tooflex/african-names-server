package com.tooflexdev.prenomsafricains.mailchimp

import com.google.gson.annotations.SerializedName


data class MergeFields (

  @SerializedName("property1" ) var property1 : String? = null,
  @SerializedName("property2" ) var property2 : String? = null

)