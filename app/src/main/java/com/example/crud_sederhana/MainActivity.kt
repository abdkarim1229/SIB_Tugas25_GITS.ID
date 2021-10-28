package com.example.crud_sederhana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crud_sederhana.model.GetData
import com.example.crud_sederhana.model.GetNameResponse
import com.example.crud_sederhana.model.GetResponse
import com.example.crud_sederhana.network.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

    val adapter = NotesAdapter(arrayListOf())
    var name: TextView? = null
    lateinit var prefsManager: PrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        prefsManager = PrefsManager(this)
        name = findViewById(R.id.tv_name)
        fab_add.setOnClickListener {
            startActivity(Intent(this, InsertActivity::class.java))
        }
        logout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            doLogut()
            Toast.makeText(this, "Logout Success", Toast.LENGTH_SHORT).show()
        }

        fab_timer.setOnClickListener {
            startActivity(Intent(this, TimerActivity::class.java))
        }

        rv_notes.layoutManager = LinearLayoutManager(this)
        rv_notes.adapter = adapter
        getDataNotes()
        getUsername()
    }

    private fun doLogut() {
        prefsManager.logOut()
    }

    private fun getUsername() {
        ApiService.endpoint.getUsername().enqueue(object : Callback<GetNameResponse> {
            override fun onResponse(
                call: Call<GetNameResponse>,
                response: Response<GetNameResponse>
            ) {
                if (response.isSuccessful) {
                    val username: GetNameResponse? = response.body()
                    onresultName(username!!)
                }
            }

            override fun onFailure(call: Call<GetNameResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Gagal Get Username", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun onresultName(response: GetNameResponse) {
        name!!.text = response.data.name
    }


    private fun getDataNotes() {
        ApiService.endpoint.getNotes().enqueue(object : Callback<GetResponse> {
            override fun onResponse(call: Call<GetResponse>, response: Response<GetResponse>) {
                if (response.isSuccessful) {
                    val notes: GetResponse? = response.body()
                    resultNotes(notes!!)
                } else {
                    Toast.makeText(this@MainActivity, "Gagal", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<GetResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Gagal get Data ${t}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun resultNotes(data: GetResponse) {
        val result: List<GetData> = data.dataGet
        adapter.setData(result)
    }
}