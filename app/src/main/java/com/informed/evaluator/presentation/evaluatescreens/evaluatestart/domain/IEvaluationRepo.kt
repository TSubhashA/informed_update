package com.informed.evaluator.presentation.evaluatescreens.evaluatestart.domain

import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.EvaluationResponse
import kotlinx.coroutines.flow.Flow


interface IEvaluationRepo {

    suspend fun getEvaluation(page:Int, pageSize:Int): Flow<EvaluationResponse>
}