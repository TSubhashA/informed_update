package com.informed.evaluator.preference

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.informed.evaluator.common.BaseApp

class SharedPreference {
    val context=BaseApp.getAppContext()
    private val PREFS_NAME = "informed_pref"
    val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)


    fun setString(KEY_NAME: String, text: String) {
     sharedPref.edit {
         putString(KEY_NAME, text)
     }
    }

    fun setBoolean(KEY_NAME: String, status: Boolean) {
      sharedPref.edit{
          putBoolean(KEY_NAME, status)
      }
    }

    fun getValueString(KEY_NAME: String): String? {
        return sharedPref.getString(KEY_NAME, null)
    }

    fun getValueBoolien(KEY_NAME: String, defaultValue: Boolean): Boolean {
        return sharedPref.getBoolean(KEY_NAME, defaultValue)
    }

    fun clearSharedPreference() {
        sharedPref.edit {
            clear()
        }
    }

    fun removeValue(KEY_NAME: String) {
        sharedPref.edit{
        remove(KEY_NAME)
    }
    }
}
