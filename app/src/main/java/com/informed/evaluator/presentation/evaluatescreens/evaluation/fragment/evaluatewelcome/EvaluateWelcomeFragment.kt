package com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.evaluatewelcome

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.informed.evaluator.R
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.QuestionsItem
import com.informed.evaluator.presentation.evaluatescreens.evaluation.view.EvaluationActivity
import kotlinx.android.synthetic.main.fragment_evaluate_welcome.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class EvaluateWelcomeFragment : Fragment() {

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
        val view= inflater.inflate(R.layout.fragment_evaluate_welcome, container, false)

        val backButton = view.findViewById(R.id.btn_back) as MaterialButton
       val nextButton = view.findViewById(R.id.btn_next) as MaterialButton
       val title = view.findViewById(R.id.title) as TextView
       val image = view.findViewById(R.id.image) as ImageView
       val description = view.findViewById(R.id.description) as TextView

        title.setText(param1?.title)

        if (param1?.media!=null)
        Glide.with(this)
            .load(param1?.media!![0])
            .into(image)

        description.setText(param1?.description)


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
            EvaluateWelcomeFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}