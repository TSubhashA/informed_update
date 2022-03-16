package com.informed.evaluator.presentation.resetpassword.view

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.informed.evaluator.R
import com.informed.evaluator.presentation.login.view.SignInActivity
import com.informed.evaluator.presentation.resetpassword.model.ResetPassRequest
import com.informed.evaluator.presentation.resetpassword.model.ResetPassRequestDemo
import com.informed.evaluator.presentation.resetpassword.viewmodel.ResetPassViewModel
import com.informed.evaluator.presentation.resetpassword.viewmodel.ResetVMFactory
import com.informed.evaluator.utils.CustomProgressDialogue
import com.informed.evaluator.utils.showToast
import com.informed.trainee.data.model.ResultOf
import com.wajahatkarim3.easyvalidation.core.view_ktx.minLength
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPasswordActivity : AppCompatActivity() {

    private val resetVM by viewModels<ResetPassViewModel> { ResetVMFactory() }
    lateinit var pd: CustomProgressDialogue
    var stringKey: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        pd = CustomProgressDialogue(this)

        val appLinkIntent = intent
        val appLinkAction = appLinkIntent.action
        val appLinkData = appLinkIntent.data

        if (Intent.ACTION_VIEW == appLinkAction) {
            stringKey=appLinkData?.getQueryParameter("key").toString()
        }



        btn_reset_password.setOnClickListener {
            if (isValid())

                observeReset()

//            startActivity(Intent(this,SignInActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }


    }

    fun onSuccess() {
        startActivity(
            Intent(
                this,
                SignInActivity::class.java
            ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }

    fun observeReset() {
        val resetModule = stringKey?.let {
            ResetPassRequest(
                it,
                new_password.editText?.text.toString()
            )
        }

        if (resetModule != null) {
            resetVM.reset(resetModule).observe(this, {

                when (it) {
                    is ResultOf.Progress -> if (it.loading) pd.show() else pd.dismiss()
                    is ResultOf.Success -> {
                        Log.d("Success : ", "${it.value}")
                        showToast("Password reset success")
                        onSuccess()
                    }
                    is ResultOf.Empty -> Log.e(" Empty : ", "${it.message}")
                    is ResultOf.Failure -> {
                        Log.e("Login : ", it.message.toString())
                        showToast(it.message!!)
                    }
                    is ResultOf.Failed -> {
                        showToast(it.toString())
                    }
                }


            })
        }


    }


    fun isValid(): Boolean {

        if (new_password.editText?.nonEmpty() == false) {
            new_password.error = "Password cant be blank"
            return false
        } else new_password.error = null
        if (new_password.editText?.minLength(6) == false) {
            new_password.error = "Password minimum length should be 6"
            return false
        } else new_password.error = null
        if (new_password.editText?.text.toString()
                .trim() != confirm_password.editText?.text.toString().trim()
        ) {
            confirm_password.error = "Password not matching"
            return false
        } else confirm_password.error = null

        return true
    }
}