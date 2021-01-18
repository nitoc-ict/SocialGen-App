package com.nitok_ict.socialgen.socialgen_app.ui.ranking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nitok_ict.socialgen.socialgen_app.model.ServerCommunicationModel
import com.nitok_ict.socialgen.socialgen_app.model.UserRank

class RankingViewModel : ViewModel() {

    private val rankingModel = ServerCommunicationModel()

    val _rankingDataList = rankingModel.getRanking()
    val rankingLiveData: LiveData<UserRank> = _rankingDataList

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}