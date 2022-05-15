package com.vinipanjos.extratobancario.ui.statement

import androidx.lifecycle.ViewModel
import com.vinipanjos.extratobancario.data.BankLineRepository

class BankStatementViewModel :  ViewModel() {

    fun findBankStatement(accountId : Int) =
        BankLineRepository.findBankStatement(accountId)


}