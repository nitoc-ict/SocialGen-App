package com.nitok_ict.socialgen.socialgen_app.ui.ranking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import com.nitok_ict.socialgen.socialgen_app.model.ServerCommunicationModel
import com.nitok_ict.socialgen.socialgen_app.model.UserRank

class RankingViewModel : ViewModel() {

    private val rankingModel = ServerCommunicationModel()

    private val _rankingLiceData = MutableLiveData<List<UserRank>>(emptyList())
    val rankingLiveData: LiveData<List<UserRank>> = _rankingLiceData.distinctUntilChanged()
    var rankingName = "ランキング"

    fun getRanking() {
        _rankingLiceData.value = rankingModel.getRanking()
    }
}