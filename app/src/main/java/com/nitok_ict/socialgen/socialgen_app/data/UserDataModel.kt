package com.nitok_ict.socialgen.socialgen_app.data

import android.content.Context
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.BufferedReader
import java.io.File
import java.lang.Exception

class UserDataModel (context: Context){
    val saveDataFile = File(context.filesDir, "saveData.json")  //セーブデータの保存場所

    private lateinit var  _userData: UserData
    private var isDataLoaded = false

    fun getUserData(): UserData {
        if (isDataLoaded){
            return _userData
        } else {
            throw Exception("UserDataNotLoaded.")
        }
    }

    fun postUserData(userData: UserData){
        _userData = userData
    }

    fun loadUserData(): Boolean {
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