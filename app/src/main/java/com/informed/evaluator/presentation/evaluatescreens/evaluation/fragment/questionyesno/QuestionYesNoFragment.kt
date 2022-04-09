package com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questionyesno

import android.content.ContentValues
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.informed.evaluator.R
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.QuestionsItem
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.evaluatequestion.adapter.EvaluationOptionMCQAdapter
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.evaluatequestion.adapter.MyItemCustomAnimation
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questionyesno.adapter.QuestionYesnNoAdapter
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.SaveEvaluateRequest
import com.informed.evaluator.presentation.evaluatescreens.evaluation.view.EvaluationActivity


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class QuestionYesNoFragment : Fragment(), QuestionYesnNoAdapter.CustomListener {

    private var param1: QuestionsItem? = null
    private var param2: String? = null
    private var view1:View? = null

    private lateinit var nextButton: MaterialButton

    private lateinit var adapter: QuestionYesnNoAdapter

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
            view1=inflater.inflate(R.layout.fragment_question_yes_no, container, false)

            val backButton = view1?.findViewById(R.id.btn_back) as MaterialButton
            nextButton = view1?.findViewById(R.id.btn_next) as MaterialButton

            val recyclerView = view1?.findViewById(R.id.recycler_view) as RecyclerView

            val question_title =view1?.findViewById<TextView>(R.id.question_title)
            val question =view1?.findViewById<TextView>(R.id.question)

            val number =view1?.findViewById<TextView>(R.id.number)
            number?.setText(param1?.position.toString())

            question_title?.setText(param1?.title)
            question?.setText(param1?.description)

            adapter = QuestionYesnNoAdapter(requireContext(),this,param1)
            val layoutManager = LinearLayoutManager(requireContext())
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter

            recyclerView.itemAnimator= MyItemCustomAnimation()

            setData()

            backButton.setOnClickListener {
                (activity as EvaluationActivity?)?.backScreen()
            }
            nextButton.setOnClickListener {
                val selected=adapter.selectedModel
                Log.e(ContentValues.TAG, "onCreateView: $selected", )
                var model:SaveEvaluateRequest?=null
                if (selected!=null)
                {
                    model= SaveEvaluateRequest()
                    model.questionId=selected.questionId
                    model.choiceId=selected.id
                    if (adapter.commentSaved.length>0)
                        model.comment=adapter.commentSaved
                }
                (activity as EvaluationActivity?)?.changeScreen(saveRequest = model)
            }


        }

        return view1!!
    }

    private fun setData() {
        val list= param1?.choices
        adapter.setData(list )
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: QuestionsItem?, param2: String) =
            QuestionYesNoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun changeBtn(text: String) {
        nextButton.text = text

        if (param1?.isRequired==true)
            nextButton.isEnabled=false

        if (text=="Next")
            nextButton.isEnabled=true
    }
}