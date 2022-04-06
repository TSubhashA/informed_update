package com.informed.evaluator.presentation.evaluatescreens.evaluatereview.domain

import com.informed.evaluator.presentation.evaluatescreens.evaluatereview.model.BeginEvaluateResponse
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.BeginSubmitEvaluateRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface IEvaluateConfirmRepo {

    suspend fun beginEvaluation(
        id: Int,
        request: BeginSubmitEvaluateRequest
    ): Flow<BeginEvaluateResponse?>


}