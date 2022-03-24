package com.informed.evaluator.data.remote

import com.informed.evaluator.network.NetworkModule

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EvaluatorService {

    @GET("admin/attending")
    suspend fun getAttendingList(@Query("page") page:Int,@Query("pageSize")pageSize:Int ): Response<Any>?



    companion object {

        var eval: EvaluatorService? = null

        fun getInstance() : EvaluatorService {

            if (eval == null) {
                val retrofit = NetworkModule.makeApiServicewithAuth()
                eval = retrofit.create(EvaluatorService::class.java)
            }
            return eval!!
        }
    }


}