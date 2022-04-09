package com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questionopinionscale

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity.CENTER
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.informed.evaluator.R
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.QuestionsItem
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.SaveEvaluateRequest
import com.informed.evaluator.presentation.evaluatescreens.evaluation.view.EvaluationActivity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class QuestionOpinionFragment : Fragment() {

    private var param1: QuestionsItem? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelable(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_question_opinion, container, false)

        val backButton = view.findViewById(R.id.btn_back) as MaterialButton
        val nextButton = view.findViewById(R.id.btn_next) as MaterialButton
        val question_title = view.findViewById(R.id.question_title) as TextView
        val question = view.findViewById(R.id.question) as TextView
        val radio_group = view.findViewById(R.id.radio_group) as RadioGroup

        question_title.setText(param1?.title)
        question.setText(param1?.description)

        val number = view.findViewById<TextView>(R.id.number)
        number?.setText(param1?.position.toString())


        val nu = 10

        for (i in 1..nu) {
            val radio = RadioButton(requireContext())
            radio.setId(i);
            radio.setText(radio.getId().toString())
            radio.background = resources.getDrawable(R.drawable.radio_feedback_selector_grey_green)
            radio.setButtonDrawable(null)
            radio.setTextColor(resources.getColor(R.color.radio_btn_text_color_black_white))
            radio.gravity = CENTER
            radio.textSize = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 14f, resources
                    .displayMetrics
            )

            val param = LinearLayout.LayoutParams(
                0, WRAP_CONTENT, 1f
            )

            val sizeInDP = 50 / nu

            val marginInDp = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, sizeInDP.toFloat(), resources
                    .displayMetrics
            ).toInt()
            if (i == nu)
                param.setMargins(marginInDp, 0, marginInDp, 0)
            else
                param.setMargins(marginInDp, 0, 0, 0)

            radio_group.addView(radio, param)
        }



        backButton.setOnClickListener {
            (activity as EvaluationActivity?)?.backScreen()
        }
        nextButton.setOnClickListener {
            var model: SaveEvaluateRequest? = null
            if (radio_group.isSelected) {
                val selectedID = radio_group.checkedRadioButtonId
                val radio = radio_group.findViewById<RadioButton>(selectedID)
                val text = radio.text


                model = SaveEvaluateRequest()
                model.questionId = param1?.id
                model.numberValue = text
            }

            (activity as EvaluationActivity?)?.changeScreen(saveRequest = model)
        }


        return view

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: QuestionsItem, param2: String) =
            QuestionOpinionFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}