package com.upday.task.data.model.auth

data class AuthRequest (
   var clientId: String,
    var  clientSecret: String,
    var  code: String,
    var  grantType: String,
    var  tokenRealm: String
)