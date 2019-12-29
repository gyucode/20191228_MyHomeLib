package com.example.a20191228_myhomelib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.a20191228_myhomelib.datas.Book
import com.example.a20191228_myhomelib.utils.BookInfo
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : BaseActivity() {

    var scannedResult: String = ""
    val book = Book()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        scanBtn.setOnClickListener {
            run {
                IntentIntegrator(this@MainActivity).initiateScan();
            }

        }
        testBtn.setOnClickListener {
            getBookInfo("9788959896127")

        }

    }

    override fun setValues() {

    }

    fun getBookInfo(isbn: String){

        BookInfo.getBookInfo(mContext, isbn, object :BookInfo.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {
                Log.d("서버응답json1",json.toString())

                val data = json.getJSONArray("docs")
                Log.d("서버응답json2",data.toString())

                val data2 = data.getJSONObject(0)
                Log.d("서버응답json3",data2.toString())

                book.title = data2.getString("TITLE")
                book.author = data2.getString("AUTHOR")
                book.page = data2.getString("PAGE")
                book.price = data2.getString("PRE_PRICE")
                book.subject = data2.getString("SUBJECT")
//                book.img = data2.getString("TITLE")
            val intent = Intent(mContext,BookDetailActivity::class.java)
            intent.putExtra("book", book)
            startActivity(intent)
            }
        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        var result: IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if(result != null){

            if(result.contents != null){
                scannedResult = result.contents
                txtValue.text = scannedResult
            } else {
                txtValue.text = "scan failed"
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
    override fun onSaveInstanceState(outState: Bundle?) {

        outState?.putString("scannedResult", scannedResult)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState?.let {
            scannedResult = it.getString("scannedResult").toString()
            txtValue.text = scannedResult
//            getBookInfo(scannedResult)


//            var intent = Intent(mContext,BookDetailActivity::class.java)
//            putExtraData("book",)
        }
    }

}
