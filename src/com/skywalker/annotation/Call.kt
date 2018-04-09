package com.skywalker.annotation

interface Call {
    @GET("http://www.google.com")
    fun getInfos(@Path("name") name: String, @Query("start") start: Int, @Query("count") count: Int): String
}
