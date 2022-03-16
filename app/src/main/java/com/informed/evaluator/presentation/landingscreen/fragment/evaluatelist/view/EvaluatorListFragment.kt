package com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.view


import android.os.Bundle


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2

import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.informed.evaluator.R
import com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.newevaluation.adapter.viewpageradapter.EvaluatePagerAdapter


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class EvaluatorListFragment : Fragment() {


    private var view1: View? = null


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
        if (view1 == null) {

            view1 = inflater.inflate(R.layout.fragment_evaluator_list, container, false)

            setTopBar(view1!!)

            setTabsLayout(view1!!)



        }
        return view1
    }

    private fun setTabsLayout(view1: View) {

        val viewPager=view1.findViewById(R.id.view_pager) as ViewPager2
        val pager = EvaluatePagerAdapter(requireActivity())
        viewPager.adapter = pager

        val tabs=view1.findViewById<TabLayout>(R.id.tablayout)

        TabLayoutMediator(tabs, viewPager) { _tab, position ->
            when (position) {
                0 -> {

                    _tab.text="New Evaluation"

                }
                1 -> {

                    _tab.text="Saved Draft"
                }

            }
        }.attach()


    }

    private fun setTopBar(view1: View) {


        val toolbar = view1.findViewById(R.id.my_tool_bar) as MaterialToolbar
        toolbar.title = ""
        val text = toolbar.findViewById(R.id.action_bar_title) as TextView

        text.text = "Evaluate"

    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EvaluatorListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}



