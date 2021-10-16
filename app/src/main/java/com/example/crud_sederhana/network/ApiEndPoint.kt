package com.example.crud_sederhana.network

import com.example.crud_sederhana.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiEndPoint {

    @GET("notebooks")
    fun getNotes(): Call<GetResponse>

    @GET("users/2")
    fun getUsername(): Call<GetNameResponse>

    @FormUrlEncoded
    @POST("notebooks")
    fun insertNotes(
        @Field("title") title: String,
        @Field("body") body: String
    ): Call<InsertResponse>

    @FormUrlEncoded
    @POST("notebooks/{id}")
    fun updateNotes(
        @Path("id") id: String,
        @Field("title") title: String,
        @Field("body") body: String,
        @Query("_method") _method: String
    ): Call<UpdateResponse>

    @DELETE("notebooks/{id}")
    fun deleteNotes(
        @Path("id") id: String
    ): Call<DeleteResponse>

    @FormUrlEncoded
    @POST("auth/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>
}