package com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.domain

import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface IAttendingListRepo {

    suspend fun getAttendingList(page:Int, pageSize:Int) : Flow<Response<Any>>

}