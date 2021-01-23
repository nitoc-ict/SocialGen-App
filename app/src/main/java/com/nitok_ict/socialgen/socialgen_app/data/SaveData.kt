package com.nitok_ict.socialgen.socialgen_app.data

import kotlin.math.*

data class SaveData (
    val userName: String,       //ユーザー名
    val userID: String,         //ユーザー識別子
    var money: Long,            //所持金
    var cumulativeScore: Long,  //累計スコア
    var maximumScore: Int       //最大スコア
){
    fun getMoneyAsString() = money.toString()
}