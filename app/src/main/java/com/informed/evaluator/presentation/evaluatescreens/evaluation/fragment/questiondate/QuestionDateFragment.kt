package com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questiondate

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.github.pinball83.maskededittext.MaskedEditText
import com.google.android.material.button.MaterialButton
import com.informed.evaluator.R
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.QuestionsItem
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.SaveEvaluateRequest
import com.informed.evaluator.presentation.evaluatescreens.evaluation.view.EvaluationActivity
import kotlinx.android.synthetic.main.full_quest_list_item.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class QuestionDOBFragment : Fragment() {

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

        val view= inflater.inflate(R.layout.fragment_question_date, container, false)

        val backButton = view.findViewById(R.id.btn_back) as MaterialButton
        val nextButton = view.findViewById(R.id.btn_next) as MaterialButton
        val title = view.findViewById(R.id.question_title) as TextView
        val number = view.findViewById(R.id.number) as TextView
        val question = view.findViewById(R.id.question) as TextView
        val masked_edit_text = view.findViewById(R.id.masked_edit_text) as MaskedEditText

        title.setText(param1?.title)
        number.setText(param1?.position.toString())
        question.setText(param1?.description)




        backButton.setOnClickListener {
            (activity as EvaluationActivity?)?.backScreen()
        }
        nextButton.setOnClickListener {
            val date=masked_edit_text.text

            var model:SaveEvaluateRequest?=null
            model= SaveEvaluateRequest()
            model.questionId=param1?.id
            model.dateValue=date


            (activity as EvaluationActivity?)?.changeScreen(saveRequest = model)
        }

        return view


    }

    companion object {

        @JvmStatic
        fun newInstance(param1: QuestionsItem, param2: String) =
            QuestionDOBFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}