package com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.evaluatequestion

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import com.google.android.material.button.MaterialButton
import com.informed.evaluator.R
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.evaluatequestion.adapter.EvaluationOptionMCQAdapter
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.evaluatequestion.adapter.MyItemCustomAnimation
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.evaluatequestion.adapter.MyItemTouchHelper
import com.informed.evaluator.presentation.evaluatescreens.evaluation.view.EvaluationActivity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class EvaluatequestionsFragment : Fragment(), EvaluationOptionMCQAdapter.CustomListener {


    private var param1: String? = null
    private var param2: String? = null

    private lateinit var nextButton: MaterialButton

    private lateinit var mcqAdapter: EvaluationOptionMCQAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("ResourceAsColor", "ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_evaluatequestions_mcq, container, false)

        val backButton = view.findViewById(R.id.btn_back) as MaterialButton
        nextButton = view.findViewById(R.id.btn_next) as MaterialButton

        val recyclerView = view.findViewById(R.id.option_recycler) as RecyclerView

        mcqAdapter = EvaluationOptionMCQAdapter(requireContext(), this)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mcqAdapter

        recyclerView.itemAnimator= MyItemCustomAnimation()

//        val dividerItemDecoration = DividerItemDecoration(recyclerView.getContext(),
//            layoutManager.getOrientation())
//        recyclerView.addItemDecoration(dividerItemDecoration)
//        (recyclerView.itemAnimator as SimpleItemAnimator?)!!.supportsChangeAnimations =
//            false
        setData()

        val itemTouch = MyItemTouchHelper(requireContext(), mcqAdapter)
        val touchList = ItemTouchHelper(itemTouch)
        touchList.attachToRecyclerView(recyclerView)


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
        val list = listOf(
            "Unaware/None",
            "Aware of some combinations, no plan to address",
            "Aware of all combinations, has pre- OR post-op plan",
            "Aware of all combinations, has pre- AND post-op plan"
        )
        mcqAdapter.setData(list as MutableList<String>)
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EvaluatequestionsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun changeBtn(text: String) {
        nextButton.text = text
    }


}