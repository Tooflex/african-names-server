package com.tooflexdev.prenomsafricains.mailchimp

import com.google.gson.annotations.SerializedName


data class Tags (

  @SerializedName("id"   ) var id   : Int?    = null,
  @SerializedName("name" ) var name : String? = null

)