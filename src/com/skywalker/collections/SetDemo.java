package com.skywalker.collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SetDemo {

    public static void main(String[] args){
        HashSet hashSet=new HashSet();
        A a1=new A();
        A a2=new A();
        hashSet.add(a1);
        hashSet.add(a2);

        B b1=new B();
        B b2=new B();
        hashSet.add(b1);
        hashSet.add(b2);

        C c1=new C();
        C c2=new C();
        hashSet.add(c1);
        hashSet.add(c2);


        System.out.println(hashSet);
        //[A@103dbd3, B@1, B@1, C@2, A@167cf4d]
        /*
        分析：
        HashSet内部由HashMap<E,Object>实现，当向Set中添加对象时，首先调用此对象所在类的hashCode()方法，
        计算次对象的哈希值，此哈希值决定了此对象在Set中存放的位置；若此位置没有被存储对象则直接存储，
        若已有对象则通过对象所在类的equals()比较两个对象是否相同，相同则不能被添加。
        1. a1,a2的equals相同，但hashCode不同，故该可以插入，其在set中的位置有hashCode确定
        2. b1,b2的hashCode相同，但equals不同，故该可以插入，并采用链式结构来保存多个对象
        */

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("语文", 1);
        map.put("数学", 2);
        map.put("英语", 3);
        map.put("历史", 4);
        map.put("政治", 5);
        map.put("地理", 6);
        map.put("生物", 7);
        map.put("化学", 8);
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

class A {

    @Override
    public boolean equals(Object obj) {
        return true;
    }
}

class B{
    @Override
    public int hashCode() {
        return 1;
    }
}

class C{

    @Override
    public int hashCode() {
        return 2;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
}
