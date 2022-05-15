package com.vinipanjos.extratobancario.data.remote

import com.vinipanjos.extratobancario.domain.Movimentacao
import retrofit2.http.GET
import retrofit2.http.Path

interface BankLineApi {

    @GET("movimentacoes/{id}")
    suspend fun findBankStatement(@Path("id") accountId: Int): List<Movimentacao>
}