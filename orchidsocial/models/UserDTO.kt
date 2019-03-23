package com.kecsot.orchidsocial.models

import com.google.gson.annotations.SerializedName

class UserDTO(
        @SerializedName("username")
        var name: String?,

        @SerializedName("id")
        var userid: String?,

        @SerializedName("image")
        var image: ImageUrlDTO?
)