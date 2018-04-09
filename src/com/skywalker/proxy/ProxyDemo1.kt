package com.skywalker.proxy

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

interface OnClickListener {

    fun onClick(id: Int)
}

class ClickListenerProxy {

    companion object {
        fun create(): OnClickListener {

            return Proxy.newProxyInstance(
                    OnClickListener::class.java.classLoader,
                    arrayOf(OnClickListener::class.java), object : InvocationHandler {
                override fun invoke(proxy: Any?, method: Method, args: Array<out Any>?): Any? {
                    if (method.declaringClass == Any::class.java) {
                        return method.invoke(this, args)
                    }
                    if (method.name.equals("onClick")) {
                        val parameters = method.parameters
                        if (args != null) {
                            if (args[0]==Int::class.java)
                            println("Proxy -> onClick ${args[0]}")
                        }


                    }

                    return null
                }
            }) as OnClickListener
        }


    }
}

fun main(args: Array<String>) {
    val listener = ClickListenerProxy.create()
    listener.onClick(1)

}