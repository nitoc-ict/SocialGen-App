package com.nitok_ict.socialgen.socialgen_app.ui.reinforcement

import android.app.Application
import android.content.Context
import android.service.autofill.UserData
import androidx.lifecycle.*
import com.nitok_ict.socialgen.socialgen_app.data.UserDataModel

class ReinforcementViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private val userDataModel = UserDataModel(context)

    private val _userData = MutableLiveData<UserData>()
    val userData: LiveData<UserData> = _userData.distinctUntilChanged()

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}