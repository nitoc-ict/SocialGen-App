package com.nitok_ict.socialgen.socialgen_app.data

data class StatusDataForUnity(
    val attackPower: Byte,  //攻撃力
    val attackSpeed: Byte,  //攻撃速度
    val hitPoint: Byte,     //体力
    val spread: Byte,       //拡散
    val speed:Byte          //スピード
){
    fun toStatusData() = StatusData(
        Status(attackPower),
        Status(attackSpeed),
        Status(hitPoint),
        Status(spread),
        Status(speed)
    )
}