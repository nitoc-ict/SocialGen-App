package com.nitok_ict.socialgen.socialgen_app.ui.ranking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nitok_ict.socialgen.socialgen_app.model.ServerCommunicationInterface
import com.nitok_ict.socialgen.socialgen_app.model.ServerCommunicationModel
import com.nitok_ict.socialgen.socialgen_app.model.UserRank

class RankingViewModel : ViewModel() {

    private val rankingModel = ServerCommunicationModel()

    val rankingLiveData: LiveData<List<UserRank>> = rankingModel.userRankList
    val userTotalPoint = rankingModel.userTotalScore

    private val _rankingName = MutableLiveData<String>().apply { value = "ランキング" }
    val rankingName: LiveData<String> = _rankingName


    private val _isDataLoading = MutableLiveData<Boolean>().apply { value = true }
    private val _isDataLoaded = MutableLiveData<Boolean>().apply { value = false }
    private val _isDataLaadFailure = MutableLiveData<Boolean>().apply { value = false }
    val isDataLoading : LiveData<Boolean> = _isDataLoading
    val isDataLoaded : LiveData<Boolean> = _isDataLoaded
    val isDataLoadFailed : LiveData<Boolean> = _isDataLaadFailure

    val conectionListner = object : ServerCommunicationInterface {
        override fun onTry() {
            _isDataLoading.value = true
            _isDataLaadFailure.value = false
        }

        override fun onSuccess() {
            _isDataLoading.value = false
        }

        override fun onFailure() {
            _isDataLaadFailure.value = true
            _isDataLoading.value = false
        }
    }

    fun getData() {
        rankingModel.getRanking(conectionListner)
        rankingModel.getUserTotalScore(conectionListner)
    }
}