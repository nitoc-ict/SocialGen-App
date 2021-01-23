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

    private val _statusData = MutableLiveData<StatusData>().apply { value = statusDataModel.getStatusData() }
    val statusData: LiveData<StatusData> = _statusData

    fun levelUpAttackPower(){
        _saveData.value!!.money -= _statusData.value!!.attackPower.levelUp(_saveData.value!!.money)
        saveDataModel.setSaveData(_saveData.value!!)
        saveDataModel.saveUserData()
        statusDataModel.setStatusData(_statusData.value!!)
        statusDataModel.saveStatusData()
    }

    fun levelUpAttackSpeed(){}

    fun levelUpHitPoint(){}

    fun levelUpSpread(){}

    fun levelUpSpeed(){}
}