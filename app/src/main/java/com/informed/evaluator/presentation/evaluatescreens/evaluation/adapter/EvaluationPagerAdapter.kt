package com.informed.evaluator.presentation.evaluatescreens.evaluation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.RowsItem
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.EvaluationFinishFragment
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.evaluatequestion.EvaluatequestionsFragment
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.evaluatethankfeedback.ThankFeedbackEvalFragment
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.evaluatewelcome.EvaluateWelcomeFragment
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questionemail.QuestionEmailFragment
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questionimagechoice.QuestionImageChoiceFragment
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questionnumber.QuestionNumberFragment
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questionstatement.QuestionStatementFragment
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questiontypeshorttext.QuestionShortTextFragment
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questionyesno.QuestionYesNoFragment
import com.informed.evaluator.presentation.evaluatescreens.evaluation.wrapper.QuestionType
import com.informed.evaluator.presentation.evaluatescreens.evaluation.wrapper.Questionsnaire

class EvaluationPagerAdapter(fragmentActivity: FragmentActivity, val data: RowsItem?) :
    FragmentStateAdapter(fragmentActivity) {
    val extraSize=1

    override fun getItemCount(): Int {

        return if (data == null)
            0
        else
            data.questionnaire?.questions?.size!! + extraSize
    }

    override fun createFragment(position: Int): Fragment {

        return if (position<data?.questionnaire?.questions?.size!!) when (
            data.questionnaire.questions[position]?.type.toString()
        ) {
            QuestionType.YESORNO.type-> QuestionYesNoFragment.newInstance(data.questionnaire.questions[position],"Instance")
            QuestionType.MULTIPLECHOICE.type-> EvaluatequestionsFragment.newInstance(data.questionnaire.questions[position],"Instance")
            QuestionType.STATEMENT.type-> QuestionStatementFragment.newInstance(data.questionnaire.questions[position],"Instance")
            QuestionType.EMAIL.type-> QuestionEmailFragment.newInstance(data.questionnaire.questions[position],"Instance")
            QuestionType.SHORTTEXT.type-> QuestionShortTextFragment.newInstance(data.questionnaire.questions[position],"Instance")
            QuestionType.PICTURECHOICE.type-> QuestionImageChoiceFragment.newInstance(data.questionnaire.questions[position],"Instance")
            QuestionType.NUMBER.type-> QuestionNumberFragment.newInstance(data.questionnaire.questions[position],"Instance")
            QuestionType.WELCOME.type-> EvaluateWelcomeFragment.newInstance(data.questionnaire.questions[position],"Instance")
            QuestionType.THANKYOU.type-> ThankFeedbackEvalFragment.newInstance(data.questionnaire.questions[position],"Instance")

            else -> EvaluationFinishFragment.newInstance("Finish", "Instance 2")

        }
        else
            EvaluationFinishFragment.newInstance("Finish", "Instance 2")



//        when (position) {
//            0 -> {
//                return EvaluateWelcomeFragment.newInstance("Welcom", "Instance 1");
//            }
//            1 -> {
//                return EvaluatequestionsFragment.newInstance("SecondFragment", "Instance 2")
//            }
//              2 -> {
//                return QuestionShortTextFragment.newInstance("SecondFragment", "Instance 2")
//            }
//             3 -> {
//                return QuestionLongTextFragment.newInstance("SecondFragment", "Instance 2")
//            }
//            4 -> {
//                return QuestionNumberFragment.newInstance("FirstFragment", "Instance 1");
//            }
//            5 -> {
//                return QuestionEmailFragment.newInstance("SecondFragment", "Instance 2")
//            }
//            6 -> {
//                return QuestionImageChoiceFragment.newInstance("SecondFragment", "Instance 2")
//            }
//             7 -> {
//                return QuestionYesNoFragment.newInstance("SecondFragment", "Instance 2")
//            }
//             8 -> {
//                return RatingPageFragment.newInstance("SecondFragment", "Instance 2")
//            }
//
//            else -> {
//                return EvaluationFinishFragment.newInstance("Final", "Instance 2")
//            }
//        }
    }
}