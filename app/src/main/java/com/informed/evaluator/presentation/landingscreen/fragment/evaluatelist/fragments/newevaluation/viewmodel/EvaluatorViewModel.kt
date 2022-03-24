package com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.newevaluation.viewmodel


import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.informed.evaluator.base.BaseViewModel
import com.informed.evaluator.network.NetworkModule
import com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.newevaluation.domain.IAttendingListRepo
import com.informed.evaluator.presentation.landingscreen.fragment.evaluatelist.fragments.newevaluation.model.AttendingListResp
import com.informed.trainee.data.model.ResultOf
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


class EvaluatorViewModel(val attendRepo: IAttendingListRepo): BaseViewModel() {

    val pageSize:Int=10
    var page=1
    var dataSource: MutableLiveData<ResultOf<*>>? = MutableLiveData()

    fun changePage() {
        Log.e(ContentValues.TAG, "changePage: $page")
        page++
        getAllAttending()
    }

    fun getAllAttending()=  viewModelScope.launch(handlerException) {
        attendRepo.getAttendingList(page,pageSize)
            .onStart { dataSource?.value = ResultOf.Progress(true) }
            .onCompletion { dataSource?.value = ResultOf.Progress(false) }
            .catch {
                dataSource?.value = ResultOf.Failure(it.cause?.message, it) }
            .collect {
                if (it.isSuccessful) {
                    val moshi= Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                    val jsonAdapter: JsonAdapter<AttendingListResp> = moshi.adapter(AttendingListResp::class.java)
                    //creating json object
                    val data=jsonAdapter.fromJsonValue(it.body()) as AttendingListResp
                    if (data.data?.rows?.size == 0) {
                        if (page!=1)
                            page--
                        Log.e(ContentValues.TAG, "changePage: $page")
                        dataSource?.value = ResultOf.Empty("Last page reached")
                    } else
                        dataSource?.value =  ResultOf.Success(it.body()?.let { it1 ->
                            NetworkModule.convertResponse(
                                AttendingListResp::class.java,
                                it1
                            )
                        })
                } else {
                    dataSource?.value = ResultOf.Failed(it.errorBody())
                }
            }
    }

}