package com.informed.evaluator.presentation.evaluatescreens.evaluatereview.domain

import com.informed.evaluator.data.remote.EvaluationServices
import com.informed.evaluator.presentation.evaluatescreens.evaluatereview.model.BeginEvaluateResponse
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.BeginSubmitEvaluateRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class EvaluateConfirmRepo(private val eval: EvaluationServices) : IEvaluateConfirmRepo {
    override suspend fun beginEvaluation(
        id: Int,
        request: BeginSubmitEvaluateRequest
    ): Flow<BeginEvaluateResponse?> = flow {

        val value = eval.beginEvaluation(id, request)

        emit(value)
    }.flowOn(Dispatchers.IO)


}