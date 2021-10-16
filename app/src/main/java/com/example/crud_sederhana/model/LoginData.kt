package com.example.crud_sederhana.model

import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("alamat") val alamat: String,
)