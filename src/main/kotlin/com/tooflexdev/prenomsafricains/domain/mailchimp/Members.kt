package com.tooflexdev.prenomsafricains.mailchimp

import com.google.gson.annotations.SerializedName


data class Members (

  @SerializedName("id"                               ) var id                          : String?                         = null,
  @SerializedName("email_address"                    ) var emailAddress                : String?                         = null,
  @SerializedName("unique_email_id"                  ) var uniqueEmailId               : String?                         = null,
  @SerializedName("contact_id"                       ) var contactId                   : String?                         = null,
  @SerializedName("full_name"                        ) var fullName                    : String?                         = null,
  @SerializedName("web_id"                           ) var webId                       : Int?                            = null,
  @SerializedName("email_type"                       ) var emailType                   : String?                         = null,
  @SerializedName("status"                           ) var status                      : String?                         = null,
  @SerializedName("unsubscribe_reason"               ) var unsubscribeReason           : String?                         = null,
  @SerializedName("consents_to_one_to_one_messaging" ) var consentsToOneToOneMessaging : Boolean?                        = null,
  @SerializedName("merge_fields"                     ) var mergeFields                 : MergeFields?                    = MergeFields(),
  @SerializedName("interests"                        ) var interests                   : Interests?                      = Interests(),
  @SerializedName("stats"                            ) var stats                       : Stats?                          = Stats(),
  @SerializedName("ip_signup"                        ) var ipSignup                    : String?                         = null,
  @SerializedName("timestamp_signup"                 ) var timestampSignup             : String?                         = null,
  @SerializedName("ip_opt"                           ) var ipOpt                       : String?                         = null,
  @SerializedName("timestamp_opt"                    ) var timestampOpt                : String?                         = null,
  @SerializedName("member_rating"                    ) var memberRating                : Int?                            = null,
  @SerializedName("last_changed"                     ) var lastChanged                 : String?                         = null,
  @SerializedName("language"                         ) var language                    : String?                         = null,
  @SerializedName("vip"                              ) var vip                         : Boolean?                        = null,
  @SerializedName("email_client"                     ) var emailClient                 : String?                         = null,
  @SerializedName("location"                         ) var location                    : Location?                       = Location(),
  @SerializedName("marketing_permissions"            ) var marketingPermissions        : ArrayList<MarketingPermissions> = arrayListOf(),
  @SerializedName("last_note"                        ) var lastNote                    : LastNote?                       = LastNote(),
  @SerializedName("source"                           ) var source                      : String?                         = null,
  @SerializedName("tags_count"                       ) var tagsCount                   : Int?                            = null,
  @SerializedName("tags"                             ) var tags                        : ArrayList<Tags>                 = arrayListOf(),
  @SerializedName("list_id"                          ) var listId                      : String?                         = null,
  @SerializedName("_links"                           ) var Links                       : ArrayList<Links>                = arrayListOf()

)