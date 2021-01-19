package com.nitok_ict.socialgen.socialgen_app.model
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import org.json.JSONObject


data class UserRank(val userName:String,
                    val rank:Int,
                    val score:Long)

class ServerCommunicationModel()
{
    fun getRanking():List<UserRank>{
        "http://10.0.2.2:5000/total".httpGet().response { request, response, result ->
        //"http://www.google.co.jp".httpGet().response { request, response, result ->
            when (result) {
                is Result.Success -> {
                    // レスポンスボディを表示
                    Log.d("hoge", "accept")
                }
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.d("hoge", ""+ex+"")
                }
            }
            Log.d("hoge", "test")
        }
        return listOf(UserRank("hoge", 3, 1245))
    }



}

