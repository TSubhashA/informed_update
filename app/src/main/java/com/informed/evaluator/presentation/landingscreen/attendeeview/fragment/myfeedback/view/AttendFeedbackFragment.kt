package com.informed.evaluator.presentation.landingscreen.fragment.myfeedback.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.informed.evaluator.R
import com.informed.evaluator.presentation.landingscreen.fragment.myfeedback.adapter.LectureFormListAdapter
import com.informed.evaluator.presentation.recentevaluation.view.RecentEvaluationActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyFeedbackFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyFeedbackFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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

        val view=inflater.inflate(R.layout.fragment_my_feedback, container, false)
        val toolbar=view.findViewById(R.id.my_tool_bar) as MaterialToolbar
        toolbar.setTitle("")
        val text=toolbar.findViewById(R.id.action_bar_title) as TextView
        text.text="My Feedback"

        val searchView=view.findViewById(R.id.search_button) as SearchView
        val feedback=view.findViewById(R.id.feeeback_text) as TextView
        val individualFeed=view.findViewById(R.id.btn_individiual_feed) as MaterialButton

        individualFeed.setOnClickListener { startActivity(Intent(context,RecentEvaluationActivity::class.java)) }

searchView.setOnQueryTextFocusChangeListener(object: View.OnFocusChangeListener{
    override fun onFocusChange(p0: View?, b: Boolean) {
        if(!b)
        {
            searchView.onActionViewExpanded()
            if(searchView.getQuery().toString().length < 1)
            {
//                ; //close the search editor and make search icon again
//                OR
                searchView.onActionViewCollapsed();
                feedback.visibility= VISIBLE
            }

            searchView.clearFocus();
            feedback.visibility= VISIBLE
        }
        else
            feedback.visibility= GONE
    }
})




        val recyclerView=view.findViewById(R.id.lecture_form_list) as RecyclerView

        val adapter = LectureFormListAdapter(requireContext())
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter



        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyFeedbackFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyFeedbackFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}