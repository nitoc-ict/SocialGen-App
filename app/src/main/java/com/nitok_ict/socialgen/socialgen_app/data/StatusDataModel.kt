package com.nitok_ict.socialgen.socialgen_app.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.nitok_ict.socialgen.socialgen_app.AppContext
import java.io.BufferedReader
import java.io.File

class StatusDataModel {

    private val _statusData = MutableLiveData<StatusData>().apply {
        value = StatusData(
            Status(1),
            Status(1),
            Status(1),
            Status(1),
            Status(1)
        ) }
    val statusData: LiveData<StatusData> = _statusData

    companion object{
        private val StatusDataForUnity = File(AppContext.getApplicationContext().filesDir, "UserStatusData.json")   //ユーザーステータス(Unity用)の保存場所
    }

    init {
        loadStatusData()
        saveStatusData()
    }

    private fun loadStatusData() {
        if (StatusDataForUnity.exists()){
            val mapper = jacksonObjectMapper()
            val json = StatusDataForUnity.bufferedReader().use(BufferedReader::readText)  //Jsonをテキストデータに変換
            val statusDataForUnity = mapper.readValue<StatusDataForUnity>(json)
            _statusData.value = statusDataForUnity.toStatusData()
        }
    }

    fun saveStatusData(){
        val mapper = jacksonObjectMapper()
        val json = mapper.writeValueAsString(_statusData.value!!.toStatusDataForUnity())
        StatusDataForUnity.writer().use {
            it.write(json)
        }
    }
}