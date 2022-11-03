package com.tooflexdev.prenomsafricains.mailchimp

import com.google.gson.annotations.SerializedName


data class Links (

  @SerializedName("rel"          ) var rel          : String? = null,
  @SerializedName("href"         ) var href         : String? = null,
  @SerializedName("method"       ) var method       : String? = null,
  @SerializedName("targetSchema" ) var targetSchema : String? = null,
  @SerializedName("schema"       ) var schema       : String? = null

)