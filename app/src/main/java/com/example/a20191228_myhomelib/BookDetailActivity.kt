package com.example.a20191228_myhomelib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a20191228_myhomelib.datas.Book
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

    }

    override fun setValues() {
        val book = intent.getSerializableExtra("book") as Book
        titleTxt.text = book.title
        authorTxt.text = book.author
        priceTxt.text = book.price
        pageTxt.text = book.page
    }
}
