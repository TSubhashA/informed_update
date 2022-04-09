package com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questiontypemobile

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.hbb20.CountryCodePicker
import com.informed.evaluator.R
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.QuestionsItem
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.SaveEvaluateRequest
import com.informed.evaluator.presentation.evaluatescreens.evaluation.view.EvaluationActivity


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class QuestionTypeMobileFragment : Fragment() {

    private var param1: QuestionsItem? = null
    private var param2: String? = null
    private var view1:View? = null
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

        if  (view1==null){
            view1=inflater.inflate(R.layout.fragment_question_type_mobile, container, false)

            val backButton = view1?.findViewById(R.id.btn_back) as MaterialButton
            val nextButton = view1?.findViewById(R.id.btn_next) as MaterialButton
            val codePicker = view1?.findViewById(R.id.code_picker) as CountryCodePicker
            val mobile = view1?.findViewById(R.id.mobile) as EditText

            backButton.setOnClickListener {
                (activity as EvaluationActivity?)?.backScreen()
            }
            nextButton.setOnClickListener {
                var model: SaveEvaluateRequest? = null
                if (mobile.text.length>0) {

                    val mob = mobile.text


                    model = SaveEvaluateRequest()
                    model.questionId = param1?.id
                    model.numberValue = mob
                }

                (activity as EvaluationActivity?)?.changeScreen(saveRequest = model)
            }


        }

        return view1!!

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: QuestionsItem, param2: String) =
            QuestionTypeMobileFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}