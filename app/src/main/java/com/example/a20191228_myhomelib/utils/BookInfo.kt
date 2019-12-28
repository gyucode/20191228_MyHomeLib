package com.example.a20191228_myhomelib.utils

import android.content.Context
import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class BookInfo {

    interface JsonResponseHandler{
        fun onResponse(json:JSONObject)
    }

    companion object{
        val serviceKey = "a15b77774eebc4665decf3c0b3678292"
        val baseReqURL = "http://seoji.nl.go.kr/landingPage/SearchApi.do?"

        fun getBookInfo(context: Context, isbn:String, handler:JsonResponseHandler){
            val client = OkHttpClient()

            var url = baseReqURL
            url += "cert_key=$serviceKey"
            url += "&result_style=json"
            url += "&page_no=1"
            url += "&page_size=10"
            url += "&isbn=$isbn"

            val request = Request.Builder()
                .url(url)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("서버연결실패",e.localizedMessage)
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body()?.string()
                    val json = JSONObject( body )
                    handler.onResponse(json)
                }
            })

        }
    }
}

