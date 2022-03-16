package com.informed.evaluator.presentation.evaluatescreens.evaluation.wrapper

import android.content.ContentValues
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import android.util.Log
import com.informed.evaluator.utils.showToast

open class SpeechListnerWrapper : RecognitionListener {
    override fun onReadyForSpeech(p0: Bundle?) {
        Log.e(ContentValues.TAG, "onReadyForSpeech: ready for Speech" )
    }

    override fun onBeginningOfSpeech() {
        Log.e(ContentValues.TAG, "onBeginningOfSpeech: start speeking" )
    }

    override fun onRmsChanged(p0: Float) {

    }

    override fun onBufferReceived(p0: ByteArray?) {

    }

    override fun onEndOfSpeech() {

    }

    override fun onError(p0: Int) {
        when(p0){
            SpeechRecognizer.ERROR_AUDIO->{
                Log.e(ContentValues.TAG, "onError: ERROR_AUDIO" )

            }
            SpeechRecognizer.ERROR_CLIENT->{
                Log.e(ContentValues.TAG, "onError: ERROR_CLIENT" )

//                context.showToast("onError : ERROR_CLIENT")
            }
            SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS->{
                Log.e(ContentValues.TAG, "onError: ERROR_INSUFFICIENT_PERMISSIONS" )
//                context.showToast("onError : ERROR_INSUFFICIENT_PERMISSIONS")
            }
            SpeechRecognizer.ERROR_LANGUAGE_NOT_SUPPORTED->{
                Log.e(ContentValues.TAG, "onError: ERROR_LANGUAGE_NOT_SUPPORTED" )
//                context.showToast("onError : ERROR_LANGUAGE_NOT_SUPPORTED")
            }
            SpeechRecognizer.ERROR_LANGUAGE_UNAVAILABLE->{
                Log.e(ContentValues.TAG, "onError: ERROR_LANGUAGE_UNAVAILABLE" )
//                context.showToast("onError : ERROR_LANGUAGE_UNAVAILABLE")
            }
            SpeechRecognizer.ERROR_NETWORK->{
                Log.e(ContentValues.TAG, "onError: ERROR_NETWORK" )

//                context.showToast("onError : ERROR_NETWORK")
            }
            SpeechRecognizer.ERROR_NETWORK_TIMEOUT->{
                Log.e(ContentValues.TAG, "onError: ERROR_NETWORK_TIMEOUT" )
//                context.showToast("onError : ERROR_NETWORK_TIMEOUT")
            }
            SpeechRecognizer.ERROR_RECOGNIZER_BUSY->{
                Log.e(ContentValues.TAG, "onError: ERROR_RECOGNIZER_BUSY" )
//                context.showToast("onError : ERROR_RECOGNIZER_BUSY")
            }
            SpeechRecognizer.ERROR_SPEECH_TIMEOUT->{
                Log.e(ContentValues.TAG, "onError: ERROR_SPEECH_TIMEOUT" )

//                context.showToast("onError : ERROR_SPEECH_TIMEOUT")
            }
            SpeechRecognizer.ERROR_TOO_MANY_REQUESTS->{
                Log.e(ContentValues.TAG, "onError: ERROR_TOO_MANY_REQUESTS" )
//                context.showToast("onError : ERROR_TOO_MANY_REQUESTS")
            }
            SpeechRecognizer.ERROR_NO_MATCH -> {
                Log.e(ContentValues.TAG, "onError: ERROR_NO_MATCH" )
//                context.showToast("onError : ERROR_NO_MATCH")
            }
            SpeechRecognizer.ERROR_SERVER -> {
                Log.e(ContentValues.TAG, "onError: ERROR_SERVER" )
//                context.showToast("onError : ERROR_SERVER")
            }
            SpeechRecognizer.ERROR_SERVER_DISCONNECTED -> {
                Log.e(ContentValues.TAG, "onError: ERROR_SERVER_DISCONNECTED" )
//                context.showToast("onError : ERROR_SERVER_DISCONNECTED")
            }
        }
    }

    override fun onResults(p0: Bundle?) {

    }

    override fun onPartialResults(p0: Bundle?) {

    }

    override fun onEvent(p0: Int, p1: Bundle?) {

    }
}