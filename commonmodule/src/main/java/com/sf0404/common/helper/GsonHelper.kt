package com.sf0404.common.helper

import com.google.gson.Gson

object GsonHelper {
    @JvmStatic
    fun jsonFromObject(gSon: Gson, prepareApi: Any): String {
        return gSon.toJson(prepareApi)
    }

    @JvmStatic
    fun <T> jsonToObject(gSon: Gson, jsonStr: String, clazz: Class<T>): T {
        return gSon.fromJson(jsonStr, clazz)
    }
}
