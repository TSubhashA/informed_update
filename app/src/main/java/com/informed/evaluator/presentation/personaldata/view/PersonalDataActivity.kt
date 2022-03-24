package com.informed.evaluator.presentation.personaldata.view

import android.Manifest
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.google.android.material.appbar.MaterialToolbar
import com.informed.evaluator.R
import com.informed.evaluator.base.BaseActivity
import com.informed.evaluator.preference.ConstantKeys
import com.informed.evaluator.preference.SharedPreference
import com.informed.evaluator.presentation.personaldata.model.ImageUploadResponse
import com.informed.evaluator.presentation.personaldata.model.ProfileEditRequest
import com.informed.evaluator.presentation.personaldata.viewmodel.ProfileVMFactory
import com.informed.evaluator.presentation.personaldata.viewmodel.ProfileViewModel
import com.informed.evaluator.utils.CircularProgres
import com.informed.evaluator.utils.CustomProgressDialogue
import com.informed.evaluator.utils.showToast
import com.informed.trainee.data.model.ResultOf
import kotlinx.android.synthetic.main.activity_personal_data.*
import java.security.Permission
import java.security.Permissions


class PersonalDataActivity : BaseActivity() {

    private val profVM by viewModels<ProfileViewModel> { ProfileVMFactory() }
    private var selectedImageUri: String? = null
    private lateinit var cPD: CustomProgressDialogue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        cPD = CustomProgressDialogue(this)
        setTopBar()
        setData()
        edit_image.setOnClickListener { selectImage() }

        btn_edit.setOnClickListener {
            if (personal_tainee_first_name.editText?.text?.isNotEmpty()!! && personal_tainee_phone.editText?.text?.isNotEmpty()!! && personal_tainee_last_name.editText?.text?.isNotEmpty()!!)
                observeEditing()
        }

        imageObserver()


    }

    private val startActivityForResult = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) {
        Log.e(TAG, "StartAcvity: Received" )

            Log.e(TAG, "startActivity: calling api" )
            profVM.uploadImage(this,it)


    }

    private fun imageObserver() {
        profVM.imageData?.observe(
            this
        ) {
            when (it) {
                is ResultOf.Empty -> {
                    Log.e(TAG, "imageObserver: empty" )
                }
                is ResultOf.Failed -> {
                    Log.e(TAG, "imageObserver: failed" )
                }
                is ResultOf.Failure -> {
                    Log.e(TAG, "imageObserver: failure ${it.message}" )
                }
                is ResultOf.Progress -> {
                    if (it.loading) cPD.show() else cPD.dismiss()
                }
                is ResultOf.Success -> {

                    it.value as ImageUploadResponse
                    Glide.with(this)
                        .load(it.value.data?.url)
                        .placeholder(CircularProgres.imageProgress(this))
                        .into(profile_image)
                    Log.e(TAG, "startActivityresult: ${it}")
                    this.showToast(it.toString())
                    selectedImageUri = it.value.data?.url
                }
            }

        }

    }

    private fun setData() {

        Glide.with(this)
            .load(SharedPreference().getValueString(ConstantKeys.IMAGE_URL))
            .into(profile_image)
        Log.e(TAG, "setData: dataSet")
        personal_tainee_first_name.editText?.setText(SharedPreference().getValueString(ConstantKeys.FIRSTNAME))
        personal_tainee_last_name.editText?.setText(SharedPreference().getValueString(ConstantKeys.LASTNAME))
        personal_tainee_email.editText?.setText(SharedPreference().getValueString(ConstantKeys.EMAIL))
        personal_tainee_phone.editText?.setText(SharedPreference().getValueString(ConstantKeys.MOBILE))

    }


    private fun selectImage() {
        Log.e(TAG, "selectImage: Starting" )
        if (checkPermission())
        startActivityForResult.launch("image/*")
    }

    private fun observeEditing() {
        val phone = personal_tainee_phone.editText?.text.toString()
        val firstName = personal_tainee_first_name.editText?.text.toString()
        val lastName= personal_tainee_last_name.editText?.text.toString()
        val status = "active"
        val image_url =
            if (selectedImageUri != null) selectedImageUri else SharedPreference().getValueString(
                ConstantKeys.IMAGE_URL
            )

        val request = ProfileEditRequest(image_url.toString(), firstName, lastName, phone, status)

        profVM.editProfile(request).observe(this) {
            when (it) {
                is ResultOf.Empty -> Log.e(TAG, "observeEditing: Empty")
                is ResultOf.Failed -> Log.e(TAG, "observeEditing: failed")
                is ResultOf.Failure -> Log.e(TAG, "observeEditing: ${it.message}")
                is ResultOf.Progress -> if (it.loading == true) cPD.show() else cPD.dismiss()
                is ResultOf.Success -> this.showToast("Data Updated Succesfully")
            }
        }


    }


    private fun setTopBar() {
        val toolbar = findViewById(R.id.my_tool_bar) as MaterialToolbar
        toolbar.setTitle("")
        val text = toolbar.findViewById(R.id.action_bar_title) as TextView
        text.text = "Personal Data"
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_back)

    }



    fun checkPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),100)
            return false
        }else
            return true

    }


}