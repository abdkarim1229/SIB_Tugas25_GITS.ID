package com.example.crud_sederhana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crud_sederhana.model.InsertResponse
import com.example.crud_sederhana.network.ApiService
import kotlinx.android.synthetic.main.activity_insert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)
        supportActionBar?.hide()

        fab_insert.setOnClickListener {
            ApiService.endpoint.insertNotes(
                insert_title.text.toString(),
                insert_body.text.toString()
            ).enqueue(object : Callback<InsertResponse> {
                override fun onResponse(
                    call: Call<InsertResponse>,
                    response: Response<InsertResponse>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@InsertActivity,
                            "Add data is Success",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@InsertActivity,
                            "Add data is Failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<InsertResponse>, t: Throwable) {
                    Toast.makeText(
                        this@InsertActivity,
                        "Add data is Failes ${t}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
            startActivity(Intent(this, MainActivity::class.java))
        }


    }
}