package com.dardell.reflection_api_tests;

import com.dardell.reflection_api_tests.anno_tests.SomeAnnotation;
import com.dardell.reflection_api_tests.anno_tests.TestAnnoClass;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {
        CatReflected.TestReflectionAPI(); //Тесты с java.lang.reflect

        String currentPath = System.getProperty("user.dir"); //Получаем директорию проекта
        ClassLoader classLoader = new URLClassLoader(new URL[] {new File(currentPath
                + "\\compiled_imported").toURI().toURL()}); //Создаём classLoader
        Class loadedClass = classLoader.loadClass("AnotherClass");//Подгружаем скомпилированный класс
        System.out.println("LOADED EXTERNAL CLASS: " + loadedClass.getName()); //Выводим

        Object loadedObj = loadedClass.getConstructor().newInstance(); //Создаем экземпляр объекта

        //Собираем методы
        Method[] methods = loadedClass.getDeclaredMethods();
        System.out.println(loadedClass.getName()+" METHODS: ");
        for (Method o : methods) {
            //Проверяем на приватность и делаем метод доступным при необходимости
            if (Modifier.isPrivate(o.getModifiers())) o.setAccessible(true);
            System.out.println("INVOKING " + o.getName()); //Выводим
            o.invoke(loadedObj); //Вызываем
        }

        //Тестируем аннотации
        Class testAnnoClass = TestAnnoClass.class;
        List<Method> someAnnoMethodsForExec = Arrays.stream(testAnnoClass.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(SomeAnnotation.class))
                .sorted((o1, o2) -> o2.getAnnotation(SomeAnnotation.class).priority() -
                                    o1.getAnnotation(SomeAnnotation.class).priority())
                .collect(Collectors.toList());

        System.out.println("Invoking methods with SomeAnnotation: ");
        for (Method m: someAnnoMethodsForExec){
            m.invoke(null);
        }
    }
}
