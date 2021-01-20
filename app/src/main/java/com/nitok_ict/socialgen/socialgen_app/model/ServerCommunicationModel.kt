package com.nitok_ict.socialgen.socialgen_app.model
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import org.json.JSONObject
import com.squareup.moshi.FromJson
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi


data class Ranking(
    val rank: Int,
    val userData: UserData
)

data class UserData(
    val id:Int,
    val name:String,
    val score:Long
)

class ServerCommunicationModel()
{
    fun getRanking():String{
        //val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        "http://10.0.2.2:5000/ranking?id=1".httpGet().response { request, response, result ->
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
        }
       "http://10.0.2.2:5000/ranking?id=1".httpGet().responseString{request, response, result ->
            when(result){
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.d("hoge", ""+ex+"")
                }

                is Result.Success -> {
                    val data = result.get()
                    Log.d("stringTest", "string$data")
                    val mapper = jacksonObjectMapper()
                    val userList = mapper.readValue<Ranking>(data)
                    Log.d("stringTest", "res:$userList")
                    //Log.d("stringTest", (data is String).toString())
                    //val res = moshi.adapter(Ranking::class.java).fromJson(data)
                    //val test = res?.rank.toString()


                }
            }
        }
        return ("hoge")

    }



}

