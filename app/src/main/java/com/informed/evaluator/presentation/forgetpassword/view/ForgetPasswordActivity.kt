package com.informed.evaluator.presentation.forgetpassword.view

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import com.informed.evaluator.R
import com.informed.evaluator.presentation.forgetpassword.model.ForgetPassRequest
import com.informed.evaluator.presentation.forgetpassword.model.ForgetPassResponse
import com.informed.evaluator.presentation.forgetpassword.viewmodel.ForgetVMFactory
import com.informed.evaluator.presentation.forgetpassword.viewmodel.ForgetViewModel
import com.informed.evaluator.presentation.login.view.SignInActivity
import com.informed.evaluator.presentation.resetpassword.view.ResetPasswordActivity
import com.informed.evaluator.utils.CustomProgressDialogue
import com.informed.evaluator.utils.isValidEmail
import com.informed.evaluator.utils.showToast
import com.informed.trainee.data.model.ErrorResponse
import com.informed.trainee.data.model.ResultOf
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import kotlinx.android.synthetic.main.activity_forget_password.*

class ForgetPasswordActivity : AppCompatActivity() {

    private val forgetVM by viewModels<ForgetViewModel> { ForgetVMFactory() }
    lateinit var pd: CustomProgressDialogue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        pd= CustomProgressDialogue(this)

        btn_continue.setOnClickListener {
           if (isValidEmail())

            observeForget()

//            onSuccess()
        }

        btn_gotit.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }
    }

    fun onSuccess() {

        email_update.text =" We have sent you an email to ${register_email.editText?.text.toString()} with a reset password link"

        forget_email.visibility = GONE
        forget_done.visibility = VISIBLE
    }

    fun observeForget() {
        val forgReq = ForgetPassRequest(
            register_email.editText?.text.toString()
        )

        forgetVM.forget(forgReq).observe(this, {
            when (it) {
                is ResultOf.Progress -> if (it.loading) pd.show() else pd.dismiss()
                is ResultOf.Success -> {
                    onSuccess()
                    Log.d("Success : ", "${it.value}")
                    val res=it.value as ForgetPassResponse
                    showToast(res.data)
                }
                is ResultOf.Empty -> Log.e(" Empty : ", "${it.message}")
                is ResultOf.Failure -> {
                    Log.e("Login : ", it.message.toString())
                    showToast(it.message!!)
                }
                is ResultOf.Failed -> {
                    Log.d("Success : ", "${it.value}")
                    val res=it.value as ErrorResponse
                    showToast(res.error.message)

                }
            }
        })
    }

    fun isValidEmail(): Boolean {

        if (register_email.editText?.nonEmpty() == false) {
            register_email.error = "Email ID cant be blank"
            return false
        } else register_email.error = null
        if (register_email.editText?.text.toString().isValidEmail() == false) {
            register_email.error = "Email ID is Not Valid"
            return false
        }

return true
    }



}
