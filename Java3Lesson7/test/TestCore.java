package Java3Lesson7.test;

import Java3Lesson7.annotations.AfterSuite;
import Java3Lesson7.annotations.BeforeSuite;
import Java3Lesson7.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class TestCore {
    public static void start(Class<?> tClass) {
        Method [] methods = tClass.getDeclaredMethods();
        FirstTest firstTest = new FirstTest();

        long countBeforeSuite = Arrays.stream(methods)
                .filter(method -> method.getAnnotation(BeforeSuite.class) != null).count();
        long countAfterSuite = Arrays.stream(methods)
                .filter(method -> method.getAnnotation(AfterSuite.class) != null).count();

        if(countAfterSuite>1 || countBeforeSuite>1){
            throw new RuntimeException("AfterSuite and BeforeSuite methods must be unique");
        }

        Arrays.stream(methods)
                .filter(method -> method.getAnnotation(BeforeSuite.class)!=null)
                .forEach(method -> {
                    try {
                        method.invoke(firstTest);
                    } catch (InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
        Comparator<Method> comparator = ((o1, o2) -> {
            if (o1.getAnnotation(Test.class).priority()>o2.getAnnotation(Test.class).priority())
                return 1;
            else if(o1.getAnnotation(Test.class).priority()< o1.getAnnotation(Test.class).priority())
                return -1;
            else
                return 0;
        });
        Arrays.stream(methods)
                .filter(method -> method.getAnnotation(Test.class)!=null)
                .sorted(comparator)
                .forEach(method -> {
                    try {
                        method.invoke(firstTest);
                } catch (InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
        });
        Arrays.stream(methods)
                .filter(method -> method.getAnnotation(AfterSuite.class)!=null)
                .forEach(method -> {
                    try {
                        method.invoke(firstTest);
                    } catch (InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }
}
