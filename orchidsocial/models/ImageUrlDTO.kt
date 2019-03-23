package com.kecsot.orchidsocial.models

import com.google.gson.annotations.SerializedName

/**
 * Created by kecsotamas on 2018. 03. 20..
 */
class ImageUrlDTO(
        @SerializedName("small")
        var small: String,

        @SerializedName("medium")
        var normal: String,

        @SerializedName("large")
        var large: String
)