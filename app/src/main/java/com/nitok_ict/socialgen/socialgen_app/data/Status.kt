package com.nitok_ict.socialgen.socialgen_app.data

import kotlin.math.pow

data class Status(var level: Byte) {
    fun toNextLevel(): Int = 10.0.pow(level.toDouble()).toInt()

    fun toNextLevelAsString(): String = 10.0.pow(level.toDouble()).toString()

    fun canLevelUp(money: Long) = (money < toNextLevel() && level < 6)

    fun levelUp(money: Long): Long {
        var _usedMoney: Long = 0
        if (canLevelUp(money)) {
            _usedMoney = toNextLevel().toLong()
            level++
        }
        return _usedMoney
    }

    fun levelAsString(): String{
        if (level == 6.toByte()) return "MAX"
        return level.toInt().toString()
    }
}