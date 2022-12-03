package com.tooflexdev.prenomsafricains.domain.response

import com.google.gson.annotations.SerializedName


data class Sort (

  @SerializedName("unsorted" ) var unsorted : Boolean? = null,
  @SerializedName("sorted"   ) var sorted   : Boolean? = null,
  @SerializedName("empty"    ) var empty    : Boolean? = null

)