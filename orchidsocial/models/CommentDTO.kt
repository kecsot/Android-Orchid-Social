package com.kecsot.orchidsocial.models

import java.util.*


data class CommentDTO(
        var postId: String,
        var owner: UserDTO?,
        var created: Date?,
        var message: String
)