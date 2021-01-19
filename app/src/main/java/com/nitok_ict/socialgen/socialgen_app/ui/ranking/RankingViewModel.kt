package com.nitok_ict.socialgen.socialgen_app.ui.ranking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import com.nitok_ict.socialgen.socialgen_app.model.ServerCommunicationModel
import com.nitok_ict.socialgen.socialgen_app.model.UserRank

class RankingViewModel : ViewModel() {

    private val rankingModel = ServerCommunicationModel()

    private val _rankingDataList = MutableLiveData<List<UserRank>>(emptyList())
    val rankingLiveData: LiveData<List<UserRank>> = _rankingDataList.distinctUntilChanged()

    private val _text = MutableLiveData<String>().apply {
        value = "ランキング"
    }
    val text: LiveData<String> = _text

    fun getRanking() {
        _rankingDataList.value = rankingModel.getRanking()
    }
}