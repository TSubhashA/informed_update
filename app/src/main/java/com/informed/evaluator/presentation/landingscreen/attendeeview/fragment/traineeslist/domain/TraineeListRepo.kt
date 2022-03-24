package com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.domain

import com.informed.evaluator.data.remote.TraineeService
import com.informed.evaluator.presentation.traineedetails.model.TraineeDetailsResp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import kotlinx.coroutines.withContext
import retrofit2.Response

class TraineeListRepo(val train: TraineeService):ITraineeListRepo {

    override suspend fun getTraineeList(page: Int, pageSize: Int, value:String?, year:Int?): Flow<Response<Any>> = flow{
        val list = withContext(Dispatchers.IO){ train.getTraineeList(page,pageSize,
            value,year)}
            emit(list!!)



    }

    override suspend fun getTraineeDetails(id: Int): Flow<TraineeDetailsResp> = flow{
        val detail= withContext(Dispatchers.IO){ train.getTraineeDetail(id)}

             emit(detail!! )
    }

}