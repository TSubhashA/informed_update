package com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.domain

import com.informed.evaluator.presentation.traineedetails.model.TraineeDetailsResp
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ITraineeListRepo {


    suspend fun getTraineeList(page:Int, pageSize:Int,value:String?, year:Int?) : Flow<Response<Any>>

    suspend fun getTraineeDetails(id:Int) : Flow<TraineeDetailsResp>

}