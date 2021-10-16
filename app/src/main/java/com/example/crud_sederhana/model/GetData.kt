package com.example.crud_sederhana.model

import com.google.gson.annotations.SerializedName

data class GetData(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String,
    @SerializedName("updated_at") val updated_at: String
)