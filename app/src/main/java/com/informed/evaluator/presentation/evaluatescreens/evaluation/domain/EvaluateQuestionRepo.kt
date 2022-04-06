package com.informed.evaluator.presentation.evaluatescreens.evaluation.domain

import com.informed.evaluator.data.remote.EvaluationServices
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.BeginSubmitEvaluateRequest
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.SaveEvaluateRequest
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.SaveEvaluateResponse
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.SubmitEvaluateResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class EvaluateQuestionRepo(private val eval: EvaluationServices) : IEvaluateQuestionRepo {

    override suspend fun saveEvaluate(
        id: Int,
        request: SaveEvaluateRequest
    ): Flow<SaveEvaluateResponse> = flow {

        val value = eval.evaluate(id, request)
        emit(value!!)
    }.flowOn(Dispatchers.IO)

    override suspend fun submitEvaluate(
        id: Int,
        request: BeginSubmitEvaluateRequest
    ): Flow<SubmitEvaluateResponse> = flow {
        val value = eval.submitEvaluation(id,request)
        emit(value!!)
    }.flowOn(Dispatchers.IO)
}