package com.dardell.reflection_api_tests;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {

    public static void main(String[] args) throws Exception {
        CatReflected.TestReflectionAPI(); //Тесты с java.lang.reflect

        String currentPath = System.getProperty("user.dir"); //Получаем директорию проекта
        ClassLoader classLoader = new URLClassLoader(new URL[] {new File(currentPath
                + "\\compiled_imported").toURI().toURL()}); //Создаём classLoader
        Class loadedClass = classLoader.loadClass("AnotherClass");//Подгружаем скомпилированный класс
        System.out.println("LOADED EXTERNAL CLASS: " + loadedClass.getName()); //Выводим
    }
}
