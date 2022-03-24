package com.informed.evaluator.presentation.evaluatescreens.evaluation.wrapper

import androidx.fragment.app.Fragment
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.QuestionsItem
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.EvaluationFinishFragment
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.evaluatequestion.EvaluatequestionsFragment
import com.informed.evaluator.presentation.evaluatescreens.evaluation.fragment.questionyesno.QuestionYesNoFragment


enum class QuestionType(val type:String) {

    YESORNO("yes_or_no"),
    MULTIPLECHOICE("multiple_choice"),
    PICTURECHOICE("picture_choice"),
    PHONENUMBER("phone_number"),
    STATEMENT("statement"),
    RATING("rating"),
    SHORTTEXT("short_title"),
    OPINIONSCALE("opinion_scale");

    companion object {
        fun from(findType: String) =
            values()
                .first{ it.type == findType }
    }

}


sealed class Questionsnaire{

    abstract fun getQuestionType(): Fragment

    class GetFragment(val type: String, val get: QuestionsItem?): Questionsnaire() {
        override fun getQuestionType(): Fragment {
            return when(type)
            {
                QuestionType.YESORNO.type->QuestionYesNoFragment.newInstance(get,"Instance")
                QuestionType.MULTIPLECHOICE.type-> EvaluatequestionsFragment.newInstance(get,"Instance")
//                QuestionType.MULTIPLECHOICE.type-> EvaluatequestionsFragment.newInstance(get,"Instance")

                else -> {
                    EvaluationFinishFragment.newInstance("Final", "Instance 2")
                }
            }
        }

    }


}
