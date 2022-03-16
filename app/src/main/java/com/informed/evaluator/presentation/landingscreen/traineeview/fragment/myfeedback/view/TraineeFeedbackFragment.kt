package com.informed.evaluator.presentation.landingscreen.traineeview.fragment.myfeedback.view

import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.button.MaterialButton
import com.informed.evaluator.R
import com.informed.evaluator.presentation.landingscreen.traineeview.fragment.myfeedback.adapter.ViewPagerAdapter
import com.informed.evaluator.presentation.recentevaluation.view.RecentEvaluationActivity
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class TraineeFeedbackFragment : Fragment() {

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

        val view=inflater.inflate(R.layout.fragment_trainee_feedback, container, false)

        val searchView=view.findViewById(R.id.search_button) as SearchView
        val feedback=view.findViewById(R.id.feeeback_text) as TextView
        val individualFeed=view.findViewById(R.id.btn_individiual_feed) as MaterialButton

        individualFeed.setOnClickListener { startActivity(Intent(context, RecentEvaluationActivity::class.java)) }

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
                        feedback.visibility= View.VISIBLE
                    }
                    searchView.clearFocus();
                    feedback.visibility= View.VISIBLE
                }
                else
                    feedback.visibility= View.GONE
            }
        })

        val dotsIndicator = view.findViewById<WormDotsIndicator>(R.id.dots_indicator)
        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        val adapter = ViewPagerAdapter(requireContext())
        viewPager.adapter = adapter
        dotsIndicator.setViewPager(viewPager)
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TraineeFeedbackFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}