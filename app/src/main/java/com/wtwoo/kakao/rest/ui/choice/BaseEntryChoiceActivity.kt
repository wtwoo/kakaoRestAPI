package com.wtwoo.kakao.rest.ui.choice

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.wtwoo.kakao.rest.R
import com.wtwoo.kakao.rest.model.Choice
import com.wtwoo.kakao.rest.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_entry_choice.*

abstract class BaseEntryChoiceActivity : BaseActivity() {

    protected abstract fun choices(): List<Choice>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry_choice)

        choicesRecycler.layoutManager = LinearLayoutManager(this)
        choicesRecycler.adapter = ChoiceAdapter(this, choices())
    }
}