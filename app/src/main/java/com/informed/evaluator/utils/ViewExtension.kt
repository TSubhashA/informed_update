package com.informed.evaluator.utils

import android.content.Context
import android.text.Editable
import android.util.Patterns
import android.widget.Toast
import java.text.DateFormat
import java.util.*

fun Context.showToast(msg : String){
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun Long.toDateString(dateFormat: Int =  DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}

fun String.isValidEmail(): Boolean =
    this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)