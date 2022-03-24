package com.informed.evaluator.data.remote

import com.informed.evaluator.network.NetworkModule
import com.informed.evaluator.presentation.evaluatescreens.evaluatestart.model.EvaluationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EvaluationServices {

    @GET("admin/evaluation")
    suspend fun getEvaluations(@Query("page") page:Int, @Query("pageSize")pageSize:Int ): EvaluationResponse?

    companion object {

        var eval: EvaluationServices? = null

        fun getInstance() : EvaluationServices {

            if (eval == null) {
                val retrofit = NetworkModule.makeApiServicewithAuth()
                eval = retrofit.create(EvaluationServices::class.java)
            }
            return eval!!
        }
    }
}