package com.kecsot.orchidsocial.models

import com.google.gson.annotations.SerializedName
import java.util.*

class PostDTO(
        @SerializedName("id")
        var postId: String? = null,

        @SerializedName("owner")
        var owner: UserDTO? = null,

        @SerializedName("created_at")
        var created: Date? = null,

        @SerializedName("images")
        var images: MutableList<ImageUrlDTO>? = null,

        @SerializedName("comment_count")
        var commentCount: Int? = null
)