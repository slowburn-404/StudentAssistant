package com.boris.studentassistant.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
//data model for users
 data class User (val email: String? = null,
                  val uid: String? = null,
                  val username: String? = null){

}