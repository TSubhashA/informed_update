package com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.evaluatethankfeedback

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.informed.evaluator.R
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.QuestionsItem
import com.informed.evaluator.presentation.evaluatescreens.evaluation.view.EvaluationActivity


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ThankFeedbackEvalFragment : Fragment() {

    private var param1: QuestionsItem? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelable(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_thank_feedback_eval, container, false)

        val backButton = view.findViewById(R.id.btn_back) as MaterialButton
        val nextButton = view.findViewById(R.id.btn_next) as MaterialButton
        val feedback = view.findViewById(R.id.feeeback_text) as TextView

        feedback.setText(param1?.title)

        backButton.setOnClickListener {
            (activity as EvaluationActivity?)?.backScreen()
        }
        nextButton.setOnClickListener {
            (activity as EvaluationActivity?)?.changeScreen()
        }

        return view

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: QuestionsItem?, param2: String) =
            ThankFeedbackEvalFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}