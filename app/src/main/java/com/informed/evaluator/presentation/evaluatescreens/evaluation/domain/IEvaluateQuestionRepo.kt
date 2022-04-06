package com.informed.evaluator.presentation.evaluatescreens.evaluation.domain

import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.BeginSubmitEvaluateRequest
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.SaveEvaluateRequest
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.SaveEvaluateResponse
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.SubmitEvaluateResponse
import kotlinx.coroutines.flow.Flow

interface IEvaluateQuestionRepo {

    suspend fun saveEvaluate(id:Int, request: SaveEvaluateRequest): Flow<SaveEvaluateResponse>

    suspend fun submitEvaluate(id:Int, request: BeginSubmitEvaluateRequest): Flow<SubmitEvaluateResponse>

}