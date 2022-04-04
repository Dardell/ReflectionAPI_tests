package com.dardell.reflection_api_tests;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class CatReflected {
    public static void TestReflectionAPI() throws Exception {
        Class classCat = Cat.class;
        Cat cat = new Cat(1,2,3);

        //Собираем методы
        Method[] methods = classCat.getDeclaredMethods();
        System.out.println(classCat.getName()+" METHODS: ");
        for (Method o : methods) {
            System.out.println(o.getName()); //Выводим список методов
            //Проверяем на приватность и делаем метод доступным при необходимости
            if (Modifier.isPrivate(o.getModifiers())) o.setAccessible(true);
            o.invoke(cat); //Вызываем
        }

        //Собираем поля
        Field[] fields = classCat.getDeclaredFields();
        System.out.println(classCat.getName()+" FIELDS: ");
        for (Field f : fields) {
            System.out.println(f.getName()); //Выводим список
        }
        //Обращение к полю и вывод значения для проверки
        fields[1].set(cat, 20);
        System.out.println(cat.toString() + " " +
                fields[1].getName() + " = "  + fields[1].get(cat));

        Cat cat2 = (Cat)classCat
                .getConstructor(int.class, int.class, int.class)
                .newInstance(20,30,40);
    }
}
