package com.skywalker.collections

fun main(args:Array<String>){
    val list=ArrayList<MyObject>()
    for (i in 0..10){
        list.add(MyObject(i))
    }
    println(list.toString())
    val iterator=list.iterator()
    while (iterator.hasNext()){
        val t=iterator.next()
        t.id=99
    }
    println(list.toString())
}

class MyObject(var id:Int){

    override fun toString(): String {
        return id.toString()
    }
}