package com.example.a20191228_myhomelib

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    val mContext = this


    abstract fun setupEvents()
    abstract fun setValues()
}