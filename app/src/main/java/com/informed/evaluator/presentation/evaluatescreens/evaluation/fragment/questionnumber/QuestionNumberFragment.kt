package com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questionnumber

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
import kotlinx.android.synthetic.main.fragment_evaluate_welcome.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionNumberFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionNumberFragment : Fragment() {
    // TODO: Rename and change types of parameters
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
        val view= inflater.inflate(R.layout.fragment_question_number, container, false)

        val backButton = view.findViewById(R.id.btn_back) as MaterialButton
        val nextButton = view.findViewById(R.id.btn_next) as MaterialButton
        val question_title = view.findViewById(R.id.question_title) as TextView
        val question = view.findViewById(R.id.question) as TextView

        question_title.setText(param1?.title)
        question.setText(param1?.description)

        val number =view.findViewById<TextView>(R.id.number)
        number?.setText(param1?.position.toString())



        backButton.setOnClickListener {
            (activity as EvaluationActivity?)?.backScreen()
        }
        nextButton.setOnClickListener {
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
         * @return A new instance of fragment QuestionNumberFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: QuestionsItem?, param2: String) =
            QuestionNumberFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}