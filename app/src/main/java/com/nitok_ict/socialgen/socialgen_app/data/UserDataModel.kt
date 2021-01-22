package com.nitok_ict.socialgen.socialgen_app.data

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.nitok_ict.socialgen.socialgen_app.AppContext
import java.io.BufferedReader
import java.io.File
import java.lang.Exception

class UserDataModel {

    private val saveDataFile = File(AppContext.getApplicationContext().filesDir, "saveData.json")  //セーブデータの保存場所

    private lateinit var  _userData: UserData
    private var isDataLoaded = false

    fun getUserData(): UserData {
        if (!isDataLoaded){
            if(!loadUserData()){
                throw Exception("SaveDataNotExist")
            }
        }
        return _userData
    }

    fun setUserData(userData: UserData){
        _userData = userData
    }

    private fun loadUserData(): Boolean {
        if (saveDataFile.exists()){
            val mapper = jacksonObjectMapper()
            val json = saveDataFile.bufferedReader().use(BufferedReader::readText)  //Jsonをテキストデータに変換
            _userData = mapper.readValue(json)
            isDataLoaded = true
            return true
        } else {
            return false
        }
    }

    fun saveUserData(){
        val mapper = jacksonObjectMapper()
        val json = mapper.writeValueAsString(_userData)
        saveDataFile.writer().use {
            it.write(json)
        }
    }
}