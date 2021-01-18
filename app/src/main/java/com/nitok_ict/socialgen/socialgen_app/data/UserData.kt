package com.nitok_ict.socialgen.socialgen_app.data

import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView

data class UserData (
    val userName: String,       //ユーザー名
    val userID: String,         //ユーザー識別子
    val money: Long,            //所持金
    val cumulativeScore: Long,  //累計スコア
    val maximumScore: Int,      //最大スコア
    val attackPower: Byte,      //攻撃力
    val attackSpeed: Byte,      //攻撃速度
    val hitPoint: Byte,         //体力
    val spread: Byte,           //拡散
    val speed:Byte              //スピード
)