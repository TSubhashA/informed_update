package com.informed.evaluator.presentation.evaluatescreens.evaluation.adapter

import com.informed.evaluator.presentation.evaluatescreens.evaluation.wrapper.QuestionType

enum class ContextInfoType(val type:String){
    SHORT_TEXT("shorttext"),
    LONG_TEXT("longtext"),
    DROPDOWN("dropdown");

    companion object {
        fun from(findType: String) =
            QuestionType.values()
                .first{ it.type == findType }
    }
}