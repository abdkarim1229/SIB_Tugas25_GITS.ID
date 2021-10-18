package com.example.crud_sederhana.model

import com.google.gson.annotations.SerializedName

data class IDResponse(
    @SerializedName("deviceID") val deviceID: String,
)