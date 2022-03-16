package com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.ratingpage

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.informed.evaluator.R
import com.informed.evaluator.presentation.evaluatescreens.evaluation.view.EvaluationActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class RatingPageFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var view1:View? = null
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

        if  (view1==null){
            view1=inflater.inflate(R.layout.fragment_rating_page, container, false)

            val backButton = view1?.findViewById(R.id.btn_back) as MaterialButton
            val nextButton = view1?.findViewById(R.id.btn_next) as MaterialButton

            backButton.setOnClickListener {
                (activity as EvaluationActivity?)?.backScreen()
            }
            nextButton.setOnClickListener {
                (activity as EvaluationActivity?)?.changeScreen()
            }


        }

        return view1!!
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RatingPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}