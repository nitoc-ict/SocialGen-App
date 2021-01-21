package com.nitok_ict.socialgen.socialgen_app.model
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.httpPost


data class UserRank(val userName:String,
                    val rank:Int,
                    val score:Long){
    fun getRankAsString() = rank.toString()

    fun getScoreAsString() = score.toString()
}
data class UserData(
    val id:Int,
    val name:String,
    val score:Long
)

data class EntryUserData(
    val name:String,
    val pref:String
)

data class UserResultData(
    val id: String,
    val score: String
)



class ServerCommunicationModel()
{
    private val _userRankingList = MediatorLiveData<List<UserData>>()
    val userRankingList:LiveData<List<UserData>> = _userRankingList
    private val _userTotalScore = MediatorLiveData<String>()
    val userTotalScore:LiveData<String> = _userTotalScore
    fun getRanking(listener:ServerCommunicationInterface){
        listener.onTry()
        "http://10.0.2.2:5000/ranking?id=1".httpGet().responseString   { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.d("Debug", "getRanking_Failure:$ex")
                    listener.onFailure()
                }

                is Result.Success -> {
                    val data = result.get()
                    Log.d("Debug",
                            "getRanking_Success_origin:$data")
                    val mapper = jacksonObjectMapper()
                    this._userRankingList.value = mapper.readValue<List<UserData>>(data)
                    Log.d("Debug",
                            "getRanking_Success:${this._userRankingList.value}")
                    listener.onSuccess()
                }
            }
        }
    }

    fun getUserTotalScore(listener: ServerCommunicationInterface){
        listener.onTry()
        "http://10.0.2.2:5000/total".httpGet().responseString{ request, response, result ->
            when(result){
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.d("Debug", "getUserTotalScore_Failure:$ex")
                    listener.onFailure()
                }

                is Result.Success -> {
                    val data = result.get()
                    Log.d("Debug", "getUserTotalScore_Success_origin:$data")
                    this._userTotalScore.value = data
                    Log.d("Debug",
                            "getRanking_Success:${this._userTotalScore.value}")
                    listener.onSuccess()
                }
            }
        }
    }

    fun postEntryUserData(userData: EntryUserData, listener: ServerCommunicationInterface){
        listener.onTry()
        val mapper = jacksonObjectMapper()
        val data = mapper.writeValueAsString(userData)
        val header: HashMap<String, String> = hashMapOf("Content-Type" to "application/json")

        "http://10.0.2.2:5000/entry_player".httpPost().header(header).body(data).response{request, response, result ->
            when(result){
                is Result.Failure ->{
                    listener.onFailure()
                    val ex = result.getException()
                    Log.d("Debug", "postEntryData_Failure:$ex")
                }

                is Result.Success ->{
                    listener.onSuccess()
                    Log.d("Debug", "postEntryData_Success:$response")
                    Log.d("Debug", "postEntryData_result:$result")
                }
            }
        }
    }

    fun postUserResultData(userResult: UserResultData, listener: ServerCommunicationInterface){
        listener.onTry()
        val mapper = jacksonObjectMapper()
        val data = mapper.writeValueAsString(userResult)
        val header:HashMap<String, String> = hashMapOf("Content-Type" to "application/json")

        "http://10.0.2.2:5000/result".httpPost().header(header).body(data).response{request, response, result ->
            when(result){
                is Result.Failure ->{
                    listener.onFailure()
                    val ex = result.getException()
                    Log.d("Debug", "postUserResultData_Failure:$ex")
                }

                is Result.Success ->{
                    listener.onSuccess()
                    Log.d("Debug", "postUserResultData_Success:$response")
                    Log.d("Debug", "postUserResultData_result:$result")
                }
            }
        }
    }
}
