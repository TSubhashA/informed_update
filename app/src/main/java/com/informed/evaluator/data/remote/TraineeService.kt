package com.informed.evaluator.data.remote

import com.informed.evaluator.network.NetworkModule
import com.informed.evaluator.presentation.traineedetails.TraineeDetailsResp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TraineeService {

    @GET("admin/trainee/all")
    suspend fun getTraineeList(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("search") value: String?,
        @Query("year") year: Int?
    ): Response<Any>?

    @GET("admin/trainee/{trainee_id}")
    suspend fun getTraineeDetail(@Path("trainee_id") trainee_id: Int): TraineeDetailsResp?


    companion object {

        @Volatile
        var eval: TraineeService? = null

        fun getInstance(): TraineeService {

            if (eval == null) {
                val retrofit = NetworkModule.makeApiServicewithAuth()
                eval = retrofit.create(TraineeService::class.java)
            }
            return eval!!
        }
    }


}