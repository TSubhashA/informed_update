package com.informed.evaluator.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import com.informed.evaluator.R

class CustomDialogue(context: Context): AlertDialog(context) {

    init{
        val builder: android.app.AlertDialog.Builder =
            android.app.AlertDialog.Builder(context)
        val viewGroup = findViewById<ViewGroup>(android.R.id.content)
        val dialogView: View =
            LayoutInflater.from(context).inflate(R.layout.custom_dialogue_box, viewGroup, false)
        builder.setView(dialogView)
        val alertDialog: android.app.AlertDialog = builder.create()
        alertDialog.show()

        val draft=dialogView.findViewById(R.id.draft_cancel) as LinearLayout
        val discard=dialogView.findViewById(R.id.discard) as LinearLayout
        val cancel=dialogView.findViewById(R.id.cancel) as ImageButton

        draft.setOnClickListener {
            context.showToast("draft")
        }
        discard.setOnClickListener {
            context.showToast("Discard")
        }
        cancel.setOnClickListener {
            context.showToast("Cancel")
            alertDialog.dismiss()
        }
    }
}