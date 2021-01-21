package com.nitok_ict.socialgen.socialgen_app.model

interface ServerCommunicationInterface {
    fun onFailure()
    fun onSuccess()
    fun onTry()
}