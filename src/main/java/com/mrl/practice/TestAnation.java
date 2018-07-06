package com.mrl.practice;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;

import org.junit.Test;

public class TestAnation
{
    //注解与重复注解
    @MyAnno("Hello")
    @MyAnno("World")
    public void show(){
        
    }
    
    @Test
    public void test1() throws NoSuchMethodException, SecurityException{
        Class<TestAnation> test = TestAnation.class;
        Method method = test.getMethod("show");
        MyAnno[] annotationsByType = method.getAnnotationsByType(MyAnno.class);
        for (MyAnno myAnno : annotationsByType) {
            System.out.println(myAnno.value());
        }
    }
}
