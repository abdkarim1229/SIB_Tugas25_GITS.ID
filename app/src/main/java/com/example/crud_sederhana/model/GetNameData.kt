package com.example.crud_sederhana.model

import com.google.gson.annotations.SerializedName

data class GetNameData(
    @SerializedName("name") val name: String
)