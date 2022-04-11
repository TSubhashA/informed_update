package com.informed.evaluator.utils


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import com.informed.evaluator.R
import com.informed.evaluator.presentation.login.view.SignInActivity

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
            context.showToast("draft not yet implemented")
        }
        discard.setOnClickListener {
//            context.showToast("Discard")
        moveToEvaluation()
        }
        cancel.setOnClickListener {
            context.showToast("Cancel")
            alertDialog.dismiss()
        }
    }


    private fun moveToEvaluation(){
        val intent = Intent(context, SignInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        (context as Activity).finish()
        context.startActivity(intent)
    }
}