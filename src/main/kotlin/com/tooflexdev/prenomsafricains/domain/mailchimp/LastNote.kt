package com.tooflexdev.prenomsafricains.mailchimp

import com.google.gson.annotations.SerializedName


data class LastNote (

  @SerializedName("note_id"    ) var noteId    : Int?    = null,
  @SerializedName("created_at" ) var createdAt : String? = null,
  @SerializedName("created_by" ) var createdBy : String? = null,
  @SerializedName("note"       ) var note      : String? = null

)