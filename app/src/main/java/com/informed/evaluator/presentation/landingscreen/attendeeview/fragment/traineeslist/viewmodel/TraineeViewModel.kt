package com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.viewmodel

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.informed.evaluator.base.BaseViewModel
import com.informed.evaluator.network.NetworkModule
import com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.domain.ITraineeListRepo
import com.informed.evaluator.presentation.landingscreen.attendeeview.fragment.traineeslist.model.TraineeListResp
import com.informed.trainee.data.model.ResultOf
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


class TraineeViewModel(val trainRepo: ITraineeListRepo) : BaseViewModel() {

    private var page: Int = 1
    private var pageSize: Int = 10
    private var search: String? =null
    private var year:Int? =null

    var dataSource: MutableLiveData<ResultOf<*>>? = MutableLiveData()

    fun changePage() {
        Log.e(TAG, "changePage: $page")
        page++
        getTraineeList()
    }

    fun searchTrainee(searchVlue:String?=null, yearVlue:Int?=null){

        search=searchVlue
        year=yearVlue
        if (page!=1)
            page=1
        getTraineeList()

    }

    @SuppressLint("CheckResult")
    fun getTraineeList() = viewModelScope.launch(handlerException) {
        trainRepo.getTraineeList(page, pageSize,search,year)
            .onStart { dataSource?.value = ResultOf.Progress(true) }
            .onCompletion { dataSource?.value = ResultOf.Progress(false) }
            .catch {
                dataSource?.value = ResultOf.Failure(it.cause?.message, it) }
            .collect {
                if (it.isSuccessful) {
                   val moshi= Moshi.Builder()
                       .add(KotlinJsonAdapterFactory())
                       .build()
                    val jsonAdapter: JsonAdapter<TraineeListResp> = moshi.adapter(TraineeListResp::class.java)
                    //creating json object
                    val data=jsonAdapter.fromJsonValue(it.body()) as TraineeListResp
                    if (data.data?.rows?.size == 0) {
                        if (page!=1)
                        page--
                        Log.e(TAG, "changePage: $page")
                        dataSource?.value = ResultOf.Empty("Last page reached")
                    } else
                        dataSource?.value =  ResultOf.Success(it.body()?.let { it1 ->
                            NetworkModule.convertResponse(
                                TraineeListResp::class.java,
                                it1
                            )
                        })
                } else {
                    dataSource?.value = ResultOf.Failed(it.errorBody())
                }
            }
    }

//        liveData {
//
//            trainRepo.getTraineeList(page, pageSize)
//                .onStart { emit(ResultOf.Progress(true)) }
//                .onCompletion {  emit(ResultOf.Progress(false)) }
//                .catch {
//                    emit(ResultOf.Failure(it.cause?.message, it))
//                    Log.e("Failed : ", it.message.toString())
//                }
//                .collect {
//                    if (it.success == true) {
//                        if (it.data.rows==null){
//                            page--
//                        }}
//                    emit(
//                        if (it.success == true) {
//                            if (it.data.rows==null){
//                                ResultOf.Empty("List is Empty")
//                            }else
//                            ResultOf.Success(it)
//
//                        } else {
//                            ResultOf.Failed(it)
//
//                        }
//                    )
//                }
//
//
//    }

    fun getTraineeDetail(id: Int) = liveData {

        emit(ResultOf.Progress(true))

        trainRepo.getTraineeDetails(id)
            .catch {
                emit(ResultOf.Failure(it.cause?.message, it))
                Log.e("Failed : ", it.message.toString())
            }
            .collect {
                emit(
                    if (it.success == true) {

                        ResultOf.Success(it)

                    } else {
                        ResultOf.Failed(it)
                    }
                )
            }

        emit(ResultOf.Progress(false))
    }

}