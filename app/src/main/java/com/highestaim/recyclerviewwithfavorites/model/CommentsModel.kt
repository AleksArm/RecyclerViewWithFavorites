package com.highestaim.recyclerviewwithfavorites.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CommentsModel(

    @SerializedName("postId")
    @Expose
    val postId: Int? = null,

    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("email")
    @Expose
    val email: String? = null,

    @SerializedName("body")
    @Expose
    val body: String? = null
) {
    var isFavorite: Boolean = false
}
