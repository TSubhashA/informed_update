package com.informed.evaluator.presentation.evaluatescreens.evaluation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.evaluatequestion.EvaluatequestionsFragment
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.EvaluationFinishFragment
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.evaluatewelcome.EvaluateWelcomeFragment
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questionemail.QuestionEmailFragment
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questionimagechoice.QuestionImageChoiceFragment
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questionlongtext.QuestionLongTextFragment
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questionnumber.QuestionNumberFragment
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questiontypemobile.QuestionTypeMobileFragment
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questiontypeshorttext.QuestionShortTextFragment
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questionyesno.QuestionYesNoFragment
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.ratingpage.RatingPageFragment

class EvaluationPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 10
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return EvaluateWelcomeFragment.newInstance("Welcom", "Instance 1");
            }
            1 -> {
                return EvaluatequestionsFragment.newInstance("SecondFragment", "Instance 2")
            }
              2 -> {
                return QuestionShortTextFragment.newInstance("SecondFragment", "Instance 2")
            }
             3 -> {
                return QuestionLongTextFragment.newInstance("SecondFragment", "Instance 2")
            }
            4 -> {
                return QuestionNumberFragment.newInstance("FirstFragment", "Instance 1");
            }
            5 -> {
                return QuestionEmailFragment.newInstance("SecondFragment", "Instance 2")
            }
            6 -> {
                return QuestionImageChoiceFragment.newInstance("SecondFragment", "Instance 2")
            }
             7 -> {
                return QuestionYesNoFragment.newInstance("SecondFragment", "Instance 2")
            }
             8 -> {
                return RatingPageFragment.newInstance("SecondFragment", "Instance 2")
            }

            else -> {
                return EvaluationFinishFragment.newInstance("Final", "Instance 2")
            }
        }
    }
}