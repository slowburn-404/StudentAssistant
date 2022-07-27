package com.boris.studentassistant.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
//constructor with blank arguments for adding users to database
 data class User (val email: String? = null,
                  val uid: String? = null,
                  val username: String? = null){

}