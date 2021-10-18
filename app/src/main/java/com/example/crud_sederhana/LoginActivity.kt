package com.example.crud_sederhana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.crud_sederhana.model.GetNameResponse
import com.example.crud_sederhana.model.IDResponse
import com.example.crud_sederhana.model.LoginResponse
import com.example.crud_sederhana.network.ApiEndPoint
import com.example.crud_sederhana.network.ApiService
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.Executor
import javax.security.auth.callback.Callback

class LoginActivity : AppCompatActivity() {

    lateinit var prefsManager: PrefsManager
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: androidx.biometric.BiometricPrompt
    private lateinit var promptInfo: androidx.biometric.BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        prefsManager = PrefsManager(this)
        btn_login.setOnClickListener {
            ApiService.endpoint.login(log_user.text.toString(), log_pass.text.toString())
                .enqueue(object : retrofit2.Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful) {
                            val responseLog: LoginResponse? = response.body()
                            Toast.makeText(this@LoginActivity, "Login Success", Toast.LENGTH_SHORT)
                                .show()
                            startActivity(Intent(applicationContext, MainActivity::class.java))
                            prefsManager.prefsname
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "User Tidak Terdaftar",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "Login Failed $t", Toast.LENGTH_SHORT)
                            .show()
                    }

                })
//            startActivity(Intent(this, MainActivity::class.java))
        }

        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt =
            BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
//                    tv_status.text = "Aunthentication Eror: $errString"
                    Toast.makeText(
                        this@LoginActivity,
                        "Aunthentication Eror : $errString",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    ApiService.endpoint.getID().enqueue(object: retrofit2.Callback<IDResponse>{
                        override fun onResponse(call: Call<IDResponse>, response: Response<IDResponse>) {
                            if (response.body()!!.deviceID == "cfa36508fbb70f0c"){
                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                Toast.makeText(this@LoginActivity, "Auth Login Success", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }

                        override fun onFailure(call: Call<IDResponse>, t: Throwable) {
                            Toast.makeText(this@LoginActivity, "${t}", Toast.LENGTH_SHORT).show()
                        }

                    })

                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
//                    tv_status.text = "Auth Failed...!"
                    Toast.makeText(this@LoginActivity, "Auth Failed", Toast.LENGTH_SHORT).show()
                }
            })
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Authentication")
            .setSubtitle("Login using fingerprint aunthentication")
            .setNegativeButtonText("Use App Password instead")
            .build()

        btn_finger.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
    }
}