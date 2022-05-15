package com.vinipanjos.extratobancario.ui.statement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvBankStatement.layoutManager = LinearLayoutManager(this)

        findBankStatement()

    }

    private fun findBankStatement() {
        val dataSet = ArrayList<Movimentacao>()
        dataSet.add(Movimentacao(1, "15/05/2022 11:36:42", "Pratique Fitness", 150.0, TipoMovimentacao.DESPESA, 1))
        dataSet.add(Movimentacao(1, "15/05/2022 11:36:42", "Pratique Fitness", 150.0, TipoMovimentacao.RECEITA, 1))
        dataSet.add(Movimentacao(1, "15/05/2022 11:36:42", "Pratique Fitness", 150.0, TipoMovimentacao.DESPESA, 1))
        dataSet.add(Movimentacao(1, "15/05/2022 11:36:42", "Pratique Fitness", 150.0, TipoMovimentacao.DESPESA, 1))
        dataSet.add(Movimentacao(1, "15/05/2022 11:36:42", "Pratique Fitness", 150.0, TipoMovimentacao.DESPESA, 1))
        binding.rvBankStatement.adapter = BankStatementAdapter(dataSet)
    }
}