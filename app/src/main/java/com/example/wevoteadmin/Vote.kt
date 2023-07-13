package com.example.wevoteadmin

// same name rakhna data class ke members ka jesse firebase mai store hai

data class Vote(

    val name:String? = null,
    val party:String? = null,
    val profileImage:String? = null,
    val voterImage:String? = null,
    val aadharImage:String? = null

)
