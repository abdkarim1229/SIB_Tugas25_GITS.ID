package com.example.crud_sederhana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crud_sederhana.model.UpdateResponse
import com.example.crud_sederhana.network.ApiService
import kotlinx.android.synthetic.main.activity_update.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        supportActionBar?.hide()
        val intent = intent
        val id = intent.getStringExtra("id")
        val title = intent.getStringExtra("title")
        val body = intent.getStringExtra("body")

        update_id.setText(id)
        update_title.setText(title)
        update_body.setText(body)

        fab_update.setOnClickListener {
            ApiService.endpoint.updateNotes(
                update_id.text.toString(),
                update_title.text.toString(),
                update_body.text.toString(),
                "PUT"
            ).enqueue(object : Callback<UpdateResponse> {
                override fun onResponse(
                    call: Call<UpdateResponse>,
                    response: Response<UpdateResponse>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@UpdateActivity,
                            "Update data is Succes",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@UpdateActivity,
                            "Update data is Failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {
                    Toast.makeText(this@UpdateActivity, "Failed ${t}", Toast.LENGTH_SHORT).show()
                }
            })
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}