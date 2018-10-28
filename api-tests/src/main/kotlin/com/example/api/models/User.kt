package com.example.api.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

data class User (var firstName: String,
                 var lastName: String,
                 var username: String,
                 var email: String,
                 var password: String,
                 var id: String)
