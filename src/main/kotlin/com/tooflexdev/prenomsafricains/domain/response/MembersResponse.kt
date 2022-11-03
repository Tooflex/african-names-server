package com.tooflexdev.prenomsafricains.mailchimp

import com.google.gson.annotations.SerializedName


data class MembersResponse (

  @SerializedName("members"     ) var members    : ArrayList<Members> = arrayListOf(),
  @SerializedName("list_id"     ) var listId     : String?            = null,
  @SerializedName("total_items" ) var totalItems : Int?               = null,
  @SerializedName("_links"      ) var Links      : ArrayList<Links>   = arrayListOf()

)