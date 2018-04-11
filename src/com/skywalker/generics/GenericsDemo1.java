package com.skywalker.generics;

import org.junit.jupiter.api.Test;

public class GenericsDemo1 {

    private abstract class Source11<T>{
        abstract void set(T t);
        abstract T get();
    }
    @Test void test11(Source11<String> source){
        Source11<? extends Object> t=source;
    }

    private abstract class Source12<T>{

        abstract void set(T t);
        abstract T get();
    }
    @Test void test12(Source12<Number> source){
        Source12<? super Number> t=source;

    }
}
