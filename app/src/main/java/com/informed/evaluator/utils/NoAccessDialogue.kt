package com.informed.evaluator.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import com.informed.evaluator.R
import com.informed.evaluator.common.Constants.ContextInfo.context

class NoAccessDialogue(context: Context): AlertDialog(context) {

    init{
        val builder: android.app.AlertDialog.Builder =
            android.app.AlertDialog.Builder(context)
        val viewGroup = findViewById<ViewGroup>(android.R.id.content)
        val dialogView: View =
            LayoutInflater.from(context).inflate(R.layout.no_access_dialogue, viewGroup, false)
        builder.setView(dialogView)
        val alertDialog: android.app.AlertDialog = builder.create()
        alertDialog.show()

        val ok=dialogView.findViewById(R.id.button_ok) as Button

       ok.setOnClickListener {

           alertDialog.dismiss()
       }
    }
}