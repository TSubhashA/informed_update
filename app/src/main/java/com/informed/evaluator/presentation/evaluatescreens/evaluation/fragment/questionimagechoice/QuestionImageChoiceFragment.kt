package com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questionimagechoice

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.informed.evaluator.R
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questionimagechoice.adapter.EvaluationImageChoiceAdapter
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questionimagechoice.adapter.MyItemCustomAnimation
import com.informed.evaluator.presentation.evaluatescreens.evaluation.view.EvaluationActivity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class QuestionImageChoiceFragment : Fragment(), EvaluationImageChoiceAdapter.CustomListener {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var nextButton: MaterialButton

    private lateinit var adapter: EvaluationImageChoiceAdapter



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
        val view = inflater.inflate(R.layout.fragment_question_image_choice, container, false)

        val backButton = view.findViewById(R.id.btn_back) as MaterialButton
        nextButton = view.findViewById(R.id.btn_next) as MaterialButton

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
//            if (mcqAdapter.extendedCard>-1)
//            mcqAdapter.commentSave(recyclerView.findViewHolderForAdapterPosition(mcqAdapter.extendedCard) as EvaluationOptionMCQAdapter.ViewHolder)
            (activity as EvaluationActivity?)?.changeScreen()
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
        adapter.setData(list as MutableList<String>)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuestionImageChoiceFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun changeBtn(text: String) {

    }
}