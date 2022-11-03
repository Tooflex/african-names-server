package com.tooflexdev.prenomsafricains.mailchimp

import com.google.gson.annotations.SerializedName


data class EcommerceData (

  @SerializedName("total_revenue"    ) var totalRevenue   : Int?    = null,
  @SerializedName("number_of_orders" ) var numberOfOrders : Int?    = null,
  @SerializedName("currency_code"    ) var currencyCode   : String? = null

)