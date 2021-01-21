package com.nitok_ict.socialgen.socialgen_app.model
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import org.json.JSONObject
import com.squareup.moshi.FromJson
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi


data class UserData(
    val id:Int,
    val name:String,
    val score:Long
)

class ServerCommunicationModel()
{
    private val result = MediatorLiveData<List<UserData>>()
    val userRankingList:LiveData<List<UserData>> = result
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
                    Log.d("stringTest",
                            "string$data")
                    val mapper = jacksonObjectMapper()
                    this.result.value = mapper.readValue<List<UserData>>(data)
                    Log.d("Debug",
                            "getRanking_Success:${this.result.value}")
                    listener.onSuccess()
                }
            }
        }
    }



}

