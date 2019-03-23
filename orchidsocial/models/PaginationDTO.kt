package com.kecsot.orchidsocial.models

import com.google.gson.annotations.SerializedName

data class PaginationDTO<T>(

        @SerializedName("current_page")
        val currentPage: Int,

        val data: List<T>,

        @SerializedName("first_page")
        val firstPageIndex: String,

        @SerializedName("last_page")
        val lastPageIndex: Int,

        @SerializedName("next_page")
        val nextPageIndex: String,

        @SerializedName("path")
        val pathUrl: String,

        @SerializedName("per_page")
        val itemsPerPage: Int,

        @SerializedName("prev_page_url")
        val prevPageUrl: String,

        @SerializedName("from")
        val from: Int,

        @SerializedName("to")
        val to: Int,

        @SerializedName("total")
        val totalItemCount: Int
)
