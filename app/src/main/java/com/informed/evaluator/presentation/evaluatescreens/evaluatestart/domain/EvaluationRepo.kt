package com.informed.evaluator.presentation.evaluatescreens.evaluatestart.domain

import com.informed.evaluator.data.remote.EvaluationServices
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.EvaluationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class EvaluationRepo(val eval:EvaluationServices):IEvaluationRepo {
    override suspend fun getEvaluation(page: Int, pageSize: Int): Flow<EvaluationResponse> = flow {

        val value=eval.getEvaluations(page,pageSize)
        emit(value!!)

    }.flowOn(Dispatchers.IO)


}