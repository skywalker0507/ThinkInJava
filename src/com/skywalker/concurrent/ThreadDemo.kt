package com.skywalker.concurrent

import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask


class Thread1:Runnable{
    companion object {
        val random=Random()
    }
    override fun run() {
        Thread.sleep(random.nextInt(50).toLong())
        println("${Thread.currentThread().name}: Runnable -> hello world")
    }
}

class Thread2 : Callable<String>{

    override fun call(): String {
        //println("Callable -> hello world")
        return "${Thread.currentThread().name}: Callable -> hello world"
    }
}



fun main1(args:Array<String>){
    val thread1=Thread(Thread1()).start()

    val executors= Executors.newFixedThreadPool(1)
    val future=executors.submit(Thread2())
    println(future.get())

    val task=FutureTask<String>(Thread2())
    println(task.get())


}

fun main(args:Array<String>){

    val executors1=Executors.newCachedThreadPool()
    val executors2=Executors.newFixedThreadPool(3)
    val executors3=Executors.newSingleThreadExecutor()

    for (i in 1..10){
        val task=Thread1()
        //executors1.execute(task)
        //executors2.execute(task)
        executors3.execute(task)
    }

}