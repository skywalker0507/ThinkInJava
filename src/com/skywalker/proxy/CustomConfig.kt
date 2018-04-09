package com.skywalker.proxy

import java.io.FileInputStream
import java.io.InputStream
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.util.*

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION,AnnotationTarget.PROPERTY_GETTER,AnnotationTarget.PROPERTY_SETTER)
annotation class Value(val Value:String)

interface IConfig{

    @Value("db.url")
    fun dbUrl():String

    @Value("db.Validation")
    fun isValidated():Boolean

    @Value("db.pool.size")
    fun poolSize():Int
}

final class ConfigFactory{
    companion object {
        @JvmStatic
        fun create(input:InputStream):IConfig{
            val properties=Properties()
            properties.load(input)

            return Proxy.newProxyInstance(
                    IConfig::class.java.classLoader,
                    arrayOf(IConfig::class.java),
                    PropertyMapper(properties)
                    ) as IConfig
        }

        class PropertyMapper(private val properties: Properties): InvocationHandler{

            override fun invoke(proxy: Any?, method: Method, args: Array<out Any>?): Any? {
                val value= method.getAnnotation(Value::class.java) ?: return null
                val property=properties.getProperty(value.Value)?:return null
                val returns=method.returnType
                if (returns.isPrimitive){
                    when(returns){

                        Int::class.java  -> return property.toInt()
                        Long::class.java -> return property.toLong()
                        Double::class.java -> return property.toDouble()
                        Float::class.java -> return property.toFloat()
                        Boolean::class.java -> return property.toBoolean()
                    }
                }
                return property
            }

        }
    }
}

fun main(args: Array<String>) {
    val config: IConfig = ConfigFactory.create(FileInputStream("c://"))
    val dbUrl = config.dbUrl()
    val isValidated = config.isValidated()
    val dbPoolSize = config.poolSize()
}
