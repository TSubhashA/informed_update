package com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questionimagechoice

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
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.ChoicesItem
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.QuestionsItem
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questionimagechoice.adapter.EvaluationImageChoiceAdapter
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questionimagechoice.adapter.MyItemCustomAnimation
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.SaveEvaluateRequest
import com.informed.evaluator.presentation.evaluatescreens.evaluation.view.EvaluationActivity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class QuestionImageChoiceFragment : Fragment(), EvaluationImageChoiceAdapter.CustomListener {

    private var param1: QuestionsItem? = null
    private var param2: String? = null

    private lateinit var nextButton: MaterialButton

    private lateinit var adapter: EvaluationImageChoiceAdapter



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
        val view = inflater.inflate(R.layout.fragment_question_image_choice, container, false)

        val backButton = view.findViewById(R.id.btn_back) as MaterialButton
        nextButton = view.findViewById(R.id.btn_next) as MaterialButton

        val number =view.findViewById<TextView>(R.id.number)
        number?.setText(param1?.position.toString())

        val recyclerView = view.findViewById(R.id.option_recycler) as RecyclerView

        adapter = EvaluationImageChoiceAdapter(requireContext(), this)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter


        recyclerView.itemAnimator= MyItemCustomAnimation()

//        val dividerItemDecoration = DividerItemDecoration(recyclerView.getContext(),
//            layoutManager.getOrientation())
//        recyclerView.addItemDecoration(dividerItemDecoration)
//        (recyclerView.itemAnimator as SimpleItemAnimator?)!!.supportsChangeAnimations =
//            false
//        setData()

//        val itemTouch = MyItemTouchHelper(requireContext(), adapter)
//        val touchList = ItemTouchHelper(itemTouch)
//        touchList.attachToRecyclerView(recyclerView)

        setData()

        backButton.setOnClickListener {
            (activity as EvaluationActivity?)?.backScreen()
        }
        nextButton.setOnClickListener {
            val selected=adapter.selectedModel
            Log.e(ContentValues.TAG, "onCreateView: $selected", )
            var model: SaveEvaluateRequest?=null
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

        return view
    }

    private fun setData() {
        val list =

            listOf(
            "MelBorne",
            "MelBorne",
            "MelBorne",
            "MelBorne"
        )
        adapter.setData(param1?.choices as MutableList<ChoicesItem?>)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: QuestionsItem?, param2: String) =
            QuestionImageChoiceFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun changeBtn(text: String) {

    }
}