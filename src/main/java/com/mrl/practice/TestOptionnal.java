package com.mrl.practice;

import java.util.Optional;

import org.junit.Test;

public class TestOptionnal
{
    @Test
    public void test1(){
        //Optional
        Optional<Teacher> op = Optional.of(new Teacher());
        Teacher teacher = op.get();
        System.out.println(teacher);
    }
}
