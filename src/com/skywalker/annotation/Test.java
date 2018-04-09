package com.skywalker.annotation;

public class Test {
    public static void main(String[] args){
        Retrofit retrofit=new Retrofit();
        Call call=retrofit.create(Call.class);
        System.out.println(call.getInfos("tom",0,10));
        //System.out.println(call.toString());
    }
}
