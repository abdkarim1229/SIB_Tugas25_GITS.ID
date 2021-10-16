package com.example.crud_sederhana.model

import com.google.gson.annotations.SerializedName

data class GetNameResponse(
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: GetNameData,
)