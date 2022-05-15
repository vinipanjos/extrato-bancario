package com.vinipanjos.extratobancario.data

import android.util.Log
import androidx.lifecycle.liveData
import com.vinipanjos.extratobancario.data.remote.BankLineApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

//singleton é instanciada só uma vez
object BankLineRepository {

    private val TAG = javaClass.simpleName

    private val restApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BankLineApi::class.java)
    }

    fun findBankStatement(accountId: Int) = liveData {
        emit(State.Wait)
        try {
            emit(State.Sucess(data = restApi.findBankStatement(accountId)))
        } catch (e: Exception){
            Log.e(TAG, e.message, e)
            emit(State.Error(e.message))
        }

    }
}