package com.nitok_ict.socialgen.socialgen_app.data

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.nitok_ict.socialgen.socialgen_app.AppContext
import java.io.BufferedReader
import java.io.File
import java.lang.Exception

class StatusDataModel {

    companion object{
        private val StatusData = File(AppContext.getApplicationContext().filesDir, "SaveDataUserStatus.json")   //セーブデータ(ユーザーステータス)の保存場所
    }

    private lateinit var  _statusData: StatusData
    private var isStatusDataLoaded = false

    fun getStatusData(): StatusData {
        if (!isStatusDataLoaded){
            loadStatusData()
        }
        return _statusData
    }

    fun setStatusData(statusData: StatusData){
        _statusData = statusData
    }

    private fun loadStatusData() {
        if (StatusData.exists()){
            val mapper = jacksonObjectMapper()
            val json = StatusData.bufferedReader().use(BufferedReader::readText)  //Jsonをテキストデータに変換
            _statusData = mapper.readValue(json)
            isStatusDataLoaded = true
        } else {
            setStatusData(StatusData(
                Status(1),
                Status(1),
                Status(1),
                Status(1),
                Status(1)
            ))
            saveStatusData()
        }
    }

    fun saveStatusData(){
        val mapper = jacksonObjectMapper()
        val json = mapper.writeValueAsString(_statusData)
        StatusData.writer().use {
            it.write(json)
        }
    }
}