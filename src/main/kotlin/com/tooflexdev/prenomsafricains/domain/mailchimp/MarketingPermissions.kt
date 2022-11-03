package com.tooflexdev.prenomsafricains.mailchimp

import com.google.gson.annotations.SerializedName


data class MarketingPermissions (

  @SerializedName("marketing_permission_id" ) var marketingPermissionId : String?  = null,
  @SerializedName("text"                    ) var text                  : String?  = null,
  @SerializedName("enabled"                 ) var enabled               : Boolean? = null

)