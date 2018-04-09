package com.skywalker.proxy

interface TargetProvider<out T>{
    fun getTarget(name:String):T
}

