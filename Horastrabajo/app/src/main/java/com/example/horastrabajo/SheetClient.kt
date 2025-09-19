package com.example.horastrabajo

import okhttp3.*
import com.squareup.moshi.*
import java.io.IOException

object SheetClient {
    private const val SHEET_URL = "https://docs.google.com/spreadsheets/d/1q8fNzIvuQPw8p31mwZ2nHV2UwAF9KFkJ-ntU8KIpFDA/gviz/tq?tqx=out:json"

    private val client = OkHttpClient()

    fun fetchData(onResult: (List<Activity>) -> Unit, onError: (Throwable) -> Unit) {
        val request = Request.Builder().url(SHEET_URL).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) = onError(e)

            override fun onResponse(call: Call, response: Response) {
                val raw = response.body?.string() ?: return onError(Exception("Empty response"))
                val json = raw
                    .removePrefix("/*O_o*/\ngoogle.visualization.Query.setResponse(")
                    .removeSuffix(");")

                val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
                val adapter = moshi.adapter(SheetResponse::class.java)
                val sheet = adapter.fromJson(json)

                val actividades = sheet?.table?.rows?.mapNotNull { row ->
                    val c = row.c
                    if (c.size >= 3) Activity(
                        start = c[0].f ?: "",
                        end = c[1].f ?: "",
                        detail = c[2].v ?: ""
                    ) else null
                } ?: emptyList()

                onResult(actividades)
            }
        })
    }
}