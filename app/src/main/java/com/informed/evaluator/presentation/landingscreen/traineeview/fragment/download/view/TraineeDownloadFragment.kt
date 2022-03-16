package com.informed.evaluator.presentation.landingscreen.traineeview.fragment.download.view

import android.annotation.SuppressLint
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import com.informed.evaluator.R
import com.informed.evaluator.utils.toDateString


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class TraineeDownloadFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_trainee_download, container, false)
        val toolbar = view.findViewById(R.id.my_tool_bar) as MaterialToolbar
        toolbar.setTitle("")
        val dateTextView = view.findViewById(R.id.serch_edit_text_date) as TextInputLayout

        dateTextView.editText?.setOnClickListener {
            val dateRangePicker =
                MaterialDatePicker.Builder.dateRangePicker()
                    .setTitleText("Select dates")
                    .build()
            dateRangePicker.addOnPositiveButtonClickListener {

                dateTextView.editText?.setText("${it.first.toDateString()} - ${it.second.toDateString()}")

            }


            dateRangePicker.show(requireActivity().supportFragmentManager, "tag");
        }


        val text = toolbar.findViewById(R.id.action_bar_title) as TextView
        text.text = "Download Evaluation Data"

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TraineeDownloadFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}