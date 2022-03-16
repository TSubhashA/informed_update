package com.informed.evaluator.presentation.evaluatescreens.evaluation.view

enum class QuestionType(val type:String) {

    YESORNO("yes_or_no"),
    MULTIPLECHOICE("multiple_choice"),
    PICTURECHOICE("picture_choice"),
    PHONENUMBER("phone_number"),
    STATEMENT("statement"),
    RATING("rating"),
    OPINIONSCALE("opinion_scale");

    companion object {
        fun from(findType: String) =
            values()
                .first{ it.type == findType }
    }

}