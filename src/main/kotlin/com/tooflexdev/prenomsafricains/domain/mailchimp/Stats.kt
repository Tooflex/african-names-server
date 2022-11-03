package com.tooflexdev.prenomsafricains.mailchimp

import com.google.gson.annotations.SerializedName


data class Stats (

  @SerializedName("avg_open_rate"  ) var avgOpenRate   : Int?           = null,
  @SerializedName("avg_click_rate" ) var avgClickRate  : Int?           = null,
  @SerializedName("ecommerce_data" ) var ecommerceData : EcommerceData? = EcommerceData()

)