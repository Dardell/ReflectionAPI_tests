package com.dardell.reflection_api_tests.anno_tests;

public class TestAnnoClass {

    @SomeAnnotation(priority = 1)
    public static void someMethod1(){
        System.out.println("someMethod1");
    }

    public static void someMethod2(){
        System.out.println("someMethod2");
    }

    @SomeAnnotation(priority = 4)
    public static void someMethod3(){
        System.out.println("someMethod3");
    }

}
