package com.parce.core.util
import com.google.gson.Gson
import com.google.gson.GsonBuilder

fun <A> String.fromJson(type: Class<A>): A = Gson().fromJson(this, type)
fun <A> A.toJson(): String? = GsonBuilder().disableHtmlEscaping().create().toJson(this)