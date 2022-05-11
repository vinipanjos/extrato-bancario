package com.vinipanjos.extratobancario.domain

data class Movimentacao(
    val id: Int,
    val dataHora: String,
    val descricao: String,
    val valor: Double,
    val tipo: TipoMovimentacao,
    //TODO Mapear idCorrentista como idConta
    val idCorrentista: Int
)
