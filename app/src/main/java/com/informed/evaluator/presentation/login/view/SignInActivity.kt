package com.informed.evaluator.presentation.login.view


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.informed.evaluator.R
import com.informed.evaluator.preference.ConstantKeys
import com.informed.evaluator.preference.SharedPreference
import com.informed.evaluator.presentation.forgetpassword.view.ForgetPasswordActivity
import com.informed.evaluator.presentation.landingscreen.attendeeview.view.AttendeeLandingActivity
import com.informed.evaluator.presentation.landingscreen.traineeview.view.TraineeLandingActivity
import com.informed.evaluator.presentation.login.model.SignInRequest
import com.informed.evaluator.presentation.login.viewmodel.SignInVMFactory
import com.informed.evaluator.presentation.login.viewmodel.SignInViewModel
import com.informed.evaluator.utils.CustomProgressDialogue
import com.informed.evaluator.utils.NoAccessDialogue
import com.informed.evaluator.utils.isValidEmail
import com.informed.evaluator.utils.showToast
import com.informed.trainee.data.model.Error2
import com.informed.trainee.data.model.ResultOf
import com.wajahatkarim3.easyvalidation.core.view_ktx.minLength
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import kotlinx.android.synthetic.main.activity_signin.*
import java.util.concurrent.Executor


class SignInActivity : AppCompatActivity() {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private val signVM by viewModels<SignInViewModel> { SignInVMFactory() }
    lateinit var prog: CustomProgressDialogue

    override fun onCreate(savedInstanceState: Bundle?) {
//        Log.e(TAG, "onCreate: ${SharedPreference().getValueString(ConstantKeys.EMAIL)}" )
        if (SharedPreference().getValueString(ConstantKeys.EMAIL) != null)
            onLoginSucces()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signin)
        prog = CustomProgressDialogue(this)

        biometricPromt()

//        login_username.editText?.setText("karan@yopmail.com")
//        login_password.editText?.setText("Test@123")

        if (SharedPreference().getValueBoolien(ConstantKeys.IS_FINGER_ENABLE, false))
            biometric_finger.visibility = View.VISIBLE
        else
            biometric_finger.visibility = View.GONE


        btn_biometric_touch.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }

        btn_signin.setOnClickListener {

            if (isValidUser()) {
//                observeUserRole()
                observeSignIn()

            }
//            onLoginSucces()
//            onLoginSucces()+

        }


        forget_pass_btn.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ForgetPasswordActivity::class.java
                )
            )
        }
    }

    private fun biometricPromt() {
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    showToast(
                        "Authentication error: $errString"
                    )

                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    showToast(
                        "Authentication succeeded!"
                    )

                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    showToast("Authentication failed")
                }
            })


        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .build()
    }


    private fun onLoginSucces() {
        if (SharedPreference().getValueBoolien(ConstantKeys.IS_ATTENDEE, false)) {
            startActivity(
                Intent(
                    this,
                    AttendeeLandingActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
            finish()
        } else if (SharedPreference().getValueString(ConstantKeys.USER_ROLE) == "trainee") {
            startActivity(
                Intent(
                    this,
                    TraineeLandingActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
            finish()
        } else {
            NoAccessDialogue(this)
        }
    }

    private fun observeSignIn() {
        val signModule = SignInRequest(
            login_username.editText?.text.toString(),
            login_password.editText?.text.toString()
        )

        signVM.getSignInResult(signModule).observe(this) {

            when (it) {
                is ResultOf.Progress -> {
                    if (it.loading) prog.show() else prog.dismiss()
                    Log.d("Success : ", "${it}")
                }
                is ResultOf.Success -> {
                    onLoginSucces()
                    Log.d("Success : ", "${it.value}")

                }
                is ResultOf.Empty -> Log.e(" Empty : ", "${it.message}")
                is ResultOf.Failure -> {
                    Log.e("Login : ", it.message.toString())
                    showToast(it.message!!)
                }
                is ResultOf.Failed -> {
                    it.value as Error2
                    showToast(it.value.message)
                }
            }

        }


    }


    fun observeUserRole() {
        signVM.getUserRole("3").observe(this) {

            when (it) {
//                is ResultOf.Progress -> if(it.loading) pd.show() else pd.dismiss()
                is ResultOf.Success -> {
                    showToast("Succcess")
                    Log.d("Success : ", "${it.value}")

                }
                is ResultOf.Empty -> Log.e(" Empty : ", "${it.message}")
                is ResultOf.Failure -> {
                    Log.e("Login : ", it.message.toString())
                    showToast(it.message!!)
                }
                is ResultOf.Failed -> {
                }//TOD
                is ResultOf.Progress -> {
                } //TOD
            }


        }


    }


    private fun isValidUser(): Boolean {

        if (login_username.editText?.nonEmpty() == false) {
            login_username.error = "Email ID cant be blank"
            return false
        } else login_username.error = null
        if (!login_username.editText?.text.toString().isValidEmail()) {
            login_username.error = "Email ID is Not Valid"
            return false
        } else login_username.error = null
        if (login_password.editText?.nonEmpty() == false) {
            login_password.error = "Password cant be blank"
            return false
        } else login_password.error = null

        if (login_password.editText?.minLength(6) == false) {
            login_password.error = "Password minimum length should be 8"
            return false
        } else login_password.error = null

        return true

    }

}