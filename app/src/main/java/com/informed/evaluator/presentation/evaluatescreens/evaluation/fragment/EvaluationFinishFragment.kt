package com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.informed.evaluator.R
import com.informed.evaluator.presentation.evaluatescreens.evaluation.view.EvaluationActivity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class EvaluationFinishFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_evaluation_finish, container, false)
        val backButton = view.findViewById(R.id.btn_back) as MaterialButton
        val nextButton = view.findViewById(R.id.btn_next) as MaterialButton

        backButton.setOnClickListener {
            (activity as EvaluationActivity?)?.backScreen()
        }
        nextButton.setOnClickListener {

            activity?.finish()
        }


        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EvaluationFinishFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }
}