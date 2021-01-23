package com.nitok_ict.socialgen.socialgen_app.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _saveData = MutableLiveData<SaveData>().apply { value = loadSaveData() }
    val saveData : LiveData<SaveData> = _saveData
    private var isSaveDataLoaded = false

    fun getUserData(): SaveData {
        if (!isSaveDataLoaded){
            if(!loadSaveData()){
                _saveData.value = (SaveData(
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
        _saveData.value = saveData
    }

    fun setMoney(money: Long){
        _saveData.value!!.money = money
    }

    private fun loadSaveData(): Boolean {
        if (SaveData.exists()){
            val mapper = jacksonObjectMapper()
            val json = SaveData.bufferedReader().use(BufferedReader::readText)  //Jsonをテキストデータに変換
            _saveData.value = mapper.readValue(json)
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