package com.vinipanjos.extratobancario.ui.statement

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.vinipanjos.extratobancario.data.State
import com.vinipanjos.extratobancario.databinding.ActivityBankStatementBinding
import com.vinipanjos.extratobancario.domain.Correntista
import com.vinipanjos.extratobancario.domain.Movimentacao
import com.vinipanjos.extratobancario.domain.TipoMovimentacao

class BankStatementActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_ACCOUNT_HOLDER = "com.vinipanjos.extratobancario.ui.statement.EXTRA_ACCOUNT_HOLDER"
    }

    private val binding by lazy {
        ActivityBankStatementBinding.inflate(layoutInflater)
    }

    private val accountHolder by lazy {
        intent.getParcelableExtra<Correntista>(EXTRA_ACCOUNT_HOLDER) ?: throw IllegalArgumentException()
    }

    private val viewModel by viewModels<BankStatementViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvBankStatement.layoutManager = LinearLayoutManager(this)

        findBankStatement()

    }

    private fun findBankStatement() {
        viewModel.findBankStatement(accountHolder.id).observe(this){state->
            when(state){
                is State.Sucess -> {
                    binding.rvBankStatement.adapter = state.data?.let { BankStatementAdapter(it) }
                    binding.srlBankStatement.isRefreshing = false
                }
                is State.Error -> {
                    state.message?.let { Snackbar.make(binding.rvBankStatement, it, Snackbar.LENGTH_LONG).show() }
                    binding.srlBankStatement.isRefreshing = false
                }
                State.Wait -> binding.srlBankStatement.isRefreshing = true
            }
        }

//        val dataSet = ArrayList<Movimentacao>()
//        dataSet.add(Movimentacao(1, "15/05/2022 11:36:42", "Academia", 150.0, TipoMovimentacao.DESPESA, 1))
//        dataSet.add(Movimentacao(1, "15/05/2022 11:38:45", "Seguro do carro", 180.0, TipoMovimentacao.DESPESA, 1))
//        dataSet.add(Movimentacao(1, "15/05/2022 11:39:20", "Salario", 1000.0, TipoMovimentacao.RECEITA, 1))
//        dataSet.add(Movimentacao(1, "15/05/2022 11:39:42", "Netflix", 50.0, TipoMovimentacao.DESPESA, 1))
//        dataSet.add(Movimentacao(1, "15/05/2022 11:40:42", "Cartão de credito", 200.0, TipoMovimentacao.DESPESA, 1))
//        binding.rvBankStatement.adapter = BankStatementAdapter(dataSet)
    }
}