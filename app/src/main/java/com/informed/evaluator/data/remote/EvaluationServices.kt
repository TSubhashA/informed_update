package com.informed.evaluator.data.remote

import com.informed.evaluator.network.NetworkModule
import com.informed.evaluator.presentation.evaluatescreens.evaluatereview.model.BeginEvaluateResponse
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.EvaluationResponse
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.BeginSubmitEvaluateRequest
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.SaveEvaluateRequest
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.SaveEvaluateResponse
import com.informed.evaluator.presentation.evaluatescreens.evaluation.model.SubmitEvaluateResponse
import retrofit2.http.*

interface EvaluationServices {

    @GET("admin/evaluation")
    suspend fun getEvaluations(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): EvaluationResponse?

    @PUT("admin/evaluation/{id}/begin")
    suspend fun beginEvaluation(
        @Path("id") id: Int,
        @Body request: BeginSubmitEvaluateRequest
    ): BeginEvaluateResponse?

    @PUT("admin/evaluation/{id}/submit")
    suspend fun submitEvaluation(
        @Path("id") id: Int,
        @Body request: BeginSubmitEvaluateRequest
    ): SubmitEvaluateResponse?

    @POST("admin/evaluation/{id}/evaluate")
    suspend fun evaluate(
        @Path("id") id: Int,
        @Body request: SaveEvaluateRequest
    ): SaveEvaluateResponse?


    companion object {

        var eval: EvaluationServices? = null

        fun getInstance(): EvaluationServices {

            if (eval == null) {
                val retrofit = NetworkModule.makeApiServicewithAuth()
                eval = retrofit.create(EvaluationServices::class.java)
            }
            return eval!!
        }
    }
}