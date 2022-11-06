/*
 * Copyright (c) 2022.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.domain.response

import com.google.gson.annotations.SerializedName
import com.tooflexdev.prenomsafricains.mailchimp.Links
import com.tooflexdev.prenomsafricains.mailchimp.Members


data class MembersResponse (

    @SerializedName("members"     ) var members    : ArrayList<Members> = arrayListOf(),
    @SerializedName("list_id"     ) var listId     : String?            = null,
    @SerializedName("total_items" ) var totalItems : Int?               = null,
    @SerializedName("_links"      ) var Links      : ArrayList<Links>   = arrayListOf()

)