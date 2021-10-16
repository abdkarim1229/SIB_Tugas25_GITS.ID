package com.example.crud_sederhana.model

import com.google.gson.annotations.SerializedName

data class UpdateResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
)