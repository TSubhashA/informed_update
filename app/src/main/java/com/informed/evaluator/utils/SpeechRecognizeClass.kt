package com.informed.evaluator.utils


import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.speech.RecognitionListener

import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.informed.evaluator.common.BaseApp
import com.informed.evaluator.presentation.evaluatescreens.evaluation.wrapper.SpeechListnerWrapper
import java.util.*

class SpeechRecognizeClass(val context: Context) {

    var speech: SpeechRecognizer
    private lateinit var speechIntent: Intent

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            speech = SpeechRecognizer.createOnDeviceSpeechRecognizer(context)
        }else
            speech = SpeechRecognizer.createSpeechRecognizer(context, ComponentName.unflattenFromString("com.google.android.googlequicksearchbox/com.google.android.voicesearch.serviceapi.GoogleRecognitionService"))
    }

    fun getSpeechIntent():Intent{
        speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        speechIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE,
            Locale.getDefault()
        )
        speechIntent.putExtra(
            RecognizerIntent.EXTRA_PROMPT,
            "Speak....")
        return speechIntent
    }

    fun setListener(callback: RecognitionListener){
        speech.setRecognitionListener(callback)
    }

    fun isRecognitionAvailable():Boolean{

        if (SpeechRecognizer.isRecognitionAvailable(BaseApp.getAppContext())){
            return true
        }
        else {
            val appPackageName = "com.google.android.googlequicksearchbox"
            try {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$appPackageName")
                    )
                )
                return true

            } catch (anfe: ActivityNotFoundException) {
                return false
            }
        }

    }

    fun checkPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.RECORD_AUDIO),100)
            return false
        }else
            return true

    }





}