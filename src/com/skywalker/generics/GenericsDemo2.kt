package com.skywalker.generics

private abstract class Source21<out T>{

    abstract fun get():T
}

private fun test21(strs:Source21<String>){
    val objects:Source21<Any> =strs
}


private abstract class Source22<in T>{
    abstract fun add(item:T)
}

private fun test22(strs:Source22<Number>){
    val objects:Source22<Int> =strs
}