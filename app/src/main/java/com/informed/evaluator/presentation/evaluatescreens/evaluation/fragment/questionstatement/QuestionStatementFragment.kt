package com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questionstatement

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.informed.evaluator.R
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.QuestionsItem
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.evaluatequestion.adapter.EvaluationOptionMCQAdapter
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.evaluatequestion.adapter.MyItemCustomAnimation
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.evaluatequestion.adapter.MyItemTouchHelper
import com.informed.evaluator.presentation.evaluatescreens.evaluation.view.EvaluationActivity


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class QuestionStatementFragment : Fragment() {

    private var param1: QuestionsItem? = null
    private var param2: String? = null
    private lateinit var nextButton: MaterialButton

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

        val view = inflater.inflate(R.layout.fragment_question_statement, container, false)

        val backButton = view.findViewById(R.id.btn_back) as MaterialButton
        nextButton = view.findViewById(R.id.btn_next) as MaterialButton


        val question =view.findViewById<TextView>(R.id.statement_text)

        question.setText(param1?.title)


        backButton.setOnClickListener {

            (activity as EvaluationActivity?)?.backScreen()
        }
        nextButton.setOnClickListener {
//            if (mcqAdapter.extendedCard>-1)
//            mcqAdapter.commentSave(recyclerView.findViewHolderForAdapterPosition(mcqAdapter.extendedCard) as EvaluationOptionMCQAdapter.ViewHolder)
            (activity as EvaluationActivity?)?.changeScreen()
        }

        return view


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuestionStatementFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: QuestionsItem?, param2: String) =
            QuestionStatementFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}