package com.nitok_ict.socialgen.socialgen_app.data

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.nitok_ict.socialgen.socialgen_app.AppContext
import java.io.BufferedReader
import java.io.File

class ScoreModel {
    companion object{
        private val ScoreDataFromUnity = File(AppContext.getApplicationContext().filesDir, "score.json")    //ゲームからのデータ
    }
    fun isExists() = ScoreDataFromUnity.exists()

    fun loadScore(): Int {
        val mapper = jacksonObjectMapper()
        val json = ScoreDataFromUnity.bufferedReader().use(BufferedReader::readText)  //Jsonをテキストデータに変換
        return mapper.readValue<Score>(json).score
    }

    fun removeFile(){
        ScoreDataFromUnity.delete()
    }
}

data class Score(val score: Int)