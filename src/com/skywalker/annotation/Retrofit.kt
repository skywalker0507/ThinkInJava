package com.skywalker.annotation

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.lang.reflect.Type

class Retrofit {

    @Suppress("UNCHECKED_CAST")
    fun <T> create(service: Class<T>): T {
        return Proxy.newProxyInstance(
                service.classLoader,
                arrayOf<Class<*>>(service),
                object : InvocationHandler {
            @Throws(Throwable::class)
            override fun invoke(proxy: Any, method: Method, args: Array<Any>): Any? {
                println(method)
                if (method.declaringClass == Any::class.java) {
                    return method.invoke(this, args)
                }
                process(method)
                return null
            }
        }) as T
    }


    fun process(method: Method) {
        val methodAnnotations = method.annotations
        for (annotation in methodAnnotations) {
            parseAnnotation(annotation)
        }
        val parameterAnnotationsArray = method.parameterAnnotations
        val parameterTypes = method.genericParameterTypes
        parameterAnnotationsArray.forEachIndexed { index, arrayOfAnnotations ->
            val type=parameterTypes[index]
            parseParameter(type,arrayOfAnnotations)

        }
        println(methodAnnotations.size+parameterAnnotationsArray.size+parameterTypes.size)
    }

    private fun parseAnnotation(annotation: Annotation) {
        if (annotation is GET) {
            annotation.value
        }else if (annotation is POST){
            annotation.value
        }

    }

    private fun parseParameter(parameterType:Type,annotations: Array<Annotation>){
        annotations.forEach { parseParameterAnnotation(parameterType,annotations,it) }
    }
    private fun parseParameterAnnotation(type:Type,annotations:Array<Annotation>,annotation:Annotation){
        when(annotation){
            is Path -> {
                val name=annotation.value
                println("type = $type ,annotation = $annotation , value = $name")
            }
            is Query -> {
                println("type = $type ,annotation = $annotation , value = ${annotation.value}")
            }
        }
    }

}
