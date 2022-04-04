package com.dardell.reflection_api_tests;

public class Cat {
    private int privateField;
    int defaultField;
    public int publicField;

    public Cat(int privateField, int defaultField, int publicField) {
        this.privateField = privateField;
        this.defaultField = defaultField;
        this.publicField = publicField;
    }

    public Cat(){
    }

    public void publicAction(){
        System.out.println("cat just did some public action");
    }
    private void privateAction(){
        System.out.println("cat just did some private action");
    }

}
