package com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.newevaluation.domain

import com.informed.evaluator.data.remote.EvaluatorService
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

import retrofit2.Response


class AttendingListRepo(val eval: EvaluatorService):IAttendingListRepo {

    override suspend fun getAttendingList(page: Int, pageSize: Int): Flow<Response<Any>> = flow{
        val list =  withContext(Dispatchers.IO){eval.getAttendingList(page,pageSize)}
        emit(list!!)

    }
}