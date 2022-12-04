package com.tooflexdev.prenomsafricains.domain.response

import com.google.gson.annotations.SerializedName


data class Pageable (

  @SerializedName("sort"       ) var sort       : Sort?    = Sort(),
  @SerializedName("pageNumber" ) var pageNumber : Int?     = null,
  @SerializedName("pageSize"   ) var pageSize   : Int?     = null,
  @SerializedName("offset"     ) var offset     : Int?     = null,
  @SerializedName("paged"      ) var paged      : Boolean? = null,
  @SerializedName("unpaged"    ) var unpaged    : Boolean? = null

)