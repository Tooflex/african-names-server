package com.tooflexdev.prenomsafricains.domain.response

import com.google.gson.annotations.SerializedName


data class FirstnameTranslationResponse (

  @SerializedName("content"          ) var content          : ArrayList<Content> = arrayListOf(),
  @SerializedName("pageable"         ) var pageable         : Pageable?          = Pageable(),
  @SerializedName("totalElements"    ) var totalElements    : Int?               = null,
  @SerializedName("totalPages"       ) var totalPages       : Int?               = null,
  @SerializedName("last"             ) var last             : Boolean?           = null,
  @SerializedName("numberOfElements" ) var numberOfElements : Int?               = null,
  @SerializedName("number"           ) var number           : Int?               = null,
  @SerializedName("size"             ) var size             : Int?               = null,
  @SerializedName("sort"             ) var sort             : Sort?              = Sort(),
  @SerializedName("first"            ) var first            : Boolean?           = null,
  @SerializedName("empty"            ) var empty            : Boolean?           = null

)