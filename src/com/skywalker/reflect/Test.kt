package com.skywalker.reflect

import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.lang.reflect.Modifier

class Bean{

    public constructor(flags:Int){

    }

    private constructor(flags: String){

    }
    companion object {
        private val Id1=123
        var Id2=999

    }

    private val name1="name1"
    protected val name2="name2"
    public val name3="name3"

    protected fun method1(args:Int){

    }

    public fun method2(args:String):String{
        return args
    }

    public fun method3(){

    }
}

fun main(args:Array<String>){
    val clazz=Bean::class.java
    val annotations=clazz.declaredAnnotations
    val fields=clazz.declaredFields
    val methods=clazz.declaredMethods
    val constructors=clazz.declaredConstructors
    //processAnnotations(annotations)
    processConstructors(constructors)
    processFields(fields)
    processMethods(methods)
    //println(annotations.size+fields.size+methods.size)

    val bean=Bean(12)
    try {
        var t=bean::class.java.getMethod("method2", String::class.java).invoke(bean, "hello world")
        println(t)
    }catch (e:Exception){
        println(e)
    }

}

private fun processConstructors(constructors:Array<Constructor<*>>){

    println("constructors")
    constructors.forEach {
        //println(it.toString())
        println("modifiers:${Modifier.toString(it.modifiers)} name:${it.name} parameters:${it.parameters}")
    }
}

private fun processAnnotations(annotations:Array<Annotation>){
    println("annotations")
    annotations.forEach {
        //println(it.toString())
        println("$it")
    }
}

private fun processFields(fields:Array<Field>){
    println("fields")
    fields.forEach {
        //println(it.toString())
        println("modifiers:${Modifier.toString(it.modifiers)} name:${it.name} type:${it.type}")
    }
}

private fun processMethods(methods:Array<Method>){
    println("methods")
    methods.forEach {
        //println(it.toString())
        println("modifiers:${Modifier.toString(it.modifiers)} name:${it.name} parameters:${it.parameters}")
    }
}