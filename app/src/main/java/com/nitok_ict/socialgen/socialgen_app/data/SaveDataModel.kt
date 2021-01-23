package com.nitok_ict.socialgen.socialgen_app.data

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.nitok_ict.socialgen.socialgen_app.AppContext
import java.io.BufferedReader
import java.io.File
import java.lang.Exception

class SaveDataModel {

    companion object{
        private val SaveData = File(AppContext.getApplicationContext().filesDir, "SaveData.json")                       //セーブデータの保存場所
    }

    private lateinit var  _saveData: SaveData
    private var isSaveDataLoaded = false

    fun getUserData(): SaveData {
        if (!isSaveDataLoaded){
            if(!loadSaveData()){
                _saveData = (SaveData(
                    "test",
                    "testID",
                    3737,
                    500,
                    256
                ))
                //throw Exception("SaveDataNotExist")
            }
        }
        return _saveData
    }

    fun setSaveData(saveData: SaveData){
        _saveData = saveData
    }

    private fun loadSaveData(): Boolean {
        if (SaveData.exists()){
            val mapper = jacksonObjectMapper()
            val json = SaveData.bufferedReader().use(BufferedReader::readText)  //Jsonをテキストデータに変換
            _saveData = mapper.readValue(json)
            isSaveDataLoaded = true
            return true
        } else {
            return false
        }
    }

    fun saveUserData(){
        val mapper = jacksonObjectMapper()
        val json = mapper.writeValueAsString(_saveData)
        SaveData.writer().use {
            it.write(json)
        }
    }
}