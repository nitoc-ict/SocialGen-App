package com.nitok_ict.socialgen.socialgen_app.ui.reinforcement

import androidx.lifecycle.*
import com.nitok_ict.socialgen.socialgen_app.data.SaveDataModel
import com.nitok_ict.socialgen.socialgen_app.data.SaveData
import com.nitok_ict.socialgen.socialgen_app.data.StatusData
import com.nitok_ict.socialgen.socialgen_app.data.StatusDataModel

class ReinforcementViewModel : ViewModel() {

    private val saveDataModel = SaveDataModel()
    private val statusDataModel = StatusDataModel()

    private val _saveData = MutableLiveData<SaveData>().apply { value = saveDataModel.getUserData() }
    val saveData: LiveData<SaveData> = _saveData

    val statusData: LiveData<StatusData> = statusDataModel.statusData

    fun levelUpAttackPower(){
        _saveData.value!!.money -= statusData.value!!.attackPower.toNextLevel()
        saveDataModel.saveUserData()
    }

    fun levelUpAttackSpeed(){}

    fun levelUpHitPoint(){}

    fun levelUpSpread(){}

    fun levelUpSpeed(){}
}