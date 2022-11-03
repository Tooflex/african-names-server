package com.tooflexdev.prenomsafricains.mailchimp

import com.google.gson.annotations.SerializedName


data class Interests (

  @SerializedName("property1" ) var property1 : Boolean? = null,
  @SerializedName("property2" ) var property2 : Boolean? = null

)