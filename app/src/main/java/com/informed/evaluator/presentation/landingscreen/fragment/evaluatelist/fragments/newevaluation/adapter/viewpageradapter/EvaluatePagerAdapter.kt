package com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.newevaluation.adapter.viewpageradapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.newevaluation.view.NewEvaluateFragment
import com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.saveddraft.view.SavedDraftFragment

class EvaluatePagerAdapter(fa:FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when(position)
        {
            0->{
                return NewEvaluateFragment.newInstance("inital","initial")
            }

            else->{
                return SavedDraftFragment.newInstance("saved","Draft")
            }

        }
    }
}