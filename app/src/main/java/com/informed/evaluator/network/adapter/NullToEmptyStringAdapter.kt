package com.informed.evaluator.network.adapter

import androidx.annotation.Nullable
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.JsonReader
import com.squareup.moshi.ToJson

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class NullToEmptyString

class NullToEmptyStringAdapter {

    @ToJson
    fun toJson(@NullToEmptyString value: String?): String? {

        return value
    }

    @FromJson
    @NullToEmptyString
    fun fromJson(@Nullable reader: String?): String {

//        val result = if (reader.peek() === JsonReader.Token.NULL) {
//            reader.nextNull()
//        } else {
//            reader.nextString()
//        }

        return reader ?: ""
    }
}