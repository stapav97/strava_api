package com.example.stravaapi.ui.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.stravaapi.App
import com.example.stravaapi.BuildConfig
import com.example.stravaapi.databinding.ActivityLoginBinding
import com.example.stravaapi.ui.main.MainActivity
import com.example.stravaapi.utils.logTag

class LoginActivity : AppCompatActivity() {

    private val logTag = logTag()

    private lateinit var mBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).component!!.inject(this)
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)  //Disable Dark mode

        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initUI()
    }

    override fun onResume() {
        super.onResume()
        checkIsCodeExist()
    }

    private fun checkIsCodeExist() {
        val uri = intent!!.data
        if (uri != null && uri.toString().startsWith(BuildConfig.REDIRECT_URI)) {
            val code = uri.getQueryParameter("code")
            if (code != null) {
                //TODO getAccessToken
                Log.d(logTag, "code= $code")
                navigateToMainActivity()
            } else if (uri.getQueryParameter("error") != null) {
                val error = uri.getQueryParameter("error")
                Log.d(logTag, "$error")
            }
        }
    }

    private fun getAccessToken(code: String) {

    }

    fun initUI() {
        mBinding.loginButton.setOnClickListener {
            navigateToStravaLogin()
        }
    }

    fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun navigateToStravaLogin() {
        val intentUri = Uri.parse("https://www.strava.com/oauth/mobile/authorize")
            .buildUpon()
            .appendQueryParameter("client_id", BuildConfig.CLIENT_ID)
            .appendQueryParameter("redirect_uri", BuildConfig.REDIRECT_URI)
            .appendQueryParameter("response_type", "code")
            .appendQueryParameter("approval_prompt", "auto")
            .appendQueryParameter("scope", "activity:write,read_all")
            .build()

        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                intentUri
            )
        )
    }
}