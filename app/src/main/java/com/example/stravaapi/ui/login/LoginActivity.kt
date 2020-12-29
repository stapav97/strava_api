package com.example.stravaapi.ui.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.stravaapi.App
import com.example.stravaapi.BuildConfig
import com.example.stravaapi.databinding.ActivityLoginBinding
import com.example.stravaapi.ui.main.MainActivity
import com.example.stravaapi.utils.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    private val logTag = logTag()

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).component!!.inject(this)

        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)  //Disable Dark mode

        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initUI()

        mViewModel.observeCommands(this, this)
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
                Logger.d(logTag, "code = $code")
                navigateToMainActivity()
            } else if (uri.getQueryParameter("error") != null) {
                val error = uri.getQueryParameter("error")
                Log.d(logTag, "$error")
            }
        }
    }

    private fun getAccessToken(code: String) {

    }

    //==============================================================================================
    // *** UI ***
    //==============================================================================================
    @Inject
    lateinit var mViewModel: LoginActivityViewModel
    private lateinit var mBinding: ActivityLoginBinding

    private fun initUI() {
        mBinding.loginButton.setOnClickListener {
            navigateToStravaLogin()
        }

        observeState()
    }

    //==============================================================================================
    // *** State ***
    //==============================================================================================
    private var mLastConsumedState: LoginActivityViewModel.State? = null

    private fun observeState() {
        mViewModel.state().observe(this) { state ->
            if (shouldUpdateProgress(state)) {
                if (state.isProgress) {
                    mBinding.progressBar.show()
                    window.setFlags(
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    )
                } else {
                    mBinding.progressBar.gone()
                    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                }
            }
            mLastConsumedState = state
        }
    }

    private fun shouldUpdateProgress(state: LoginActivityViewModel.State): Boolean {
        return state.isProgress != mLastConsumedState?.isProgress
    }

    //==============================================================================================
    // *** Commands ***
    //==============================================================================================
    fun showErrorMessage(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
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