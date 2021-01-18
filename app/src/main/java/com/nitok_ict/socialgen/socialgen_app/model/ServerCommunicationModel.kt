package com.nitok_ict.socialgen.socialgen_app.model
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import sun.security.pkcs11.wrapper.CK_C_INITIALIZE_ARGS

data class UserRank(val userName:String,
                    val rank:Int,
                    val score:Long)

class ServerCommunicationModel()
{
    fun getRanking():List<UserRank>{

        return listOf(UserRank("hoge", 3, 1245))
    }

}

