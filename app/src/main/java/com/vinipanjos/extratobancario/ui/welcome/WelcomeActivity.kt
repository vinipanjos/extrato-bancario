package com.vinipanjos.extratobancario.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vinipanjos.extratobancario.databinding.ActivityWelcomeBinding
import com.vinipanjos.extratobancario.domain.Correntista
import com.vinipanjos.extratobancario.ui.statement.BankStatementActivity

class WelcomeActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityWelcomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.btContinue.setOnClickListener {
            val accountId = binding.tilAccountHolderId.editText?.text.toString().toInt()
            val accountHolder = Correntista(accountId)

            val intent = Intent(this, BankStatementActivity::class.java).apply {
                putExtra(BankStatementActivity.EXTRA_ACCOUNT_HOLDER, accountHolder)
            }
            startActivity(intent)

        }
    }
}