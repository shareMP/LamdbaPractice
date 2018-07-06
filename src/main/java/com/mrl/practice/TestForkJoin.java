package com.mrl.practice;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import org.junit.Test;

public class TestForkJoin
{
    @Test
    public void test1(){
        
        Instant start = Instant.now();
        
        
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCal(0, 10000000000000L);
        Long invoke = pool.invoke(task);
        System.out.println(invoke);
        
        Instant end =  Instant.now();
        System.out.println(Duration.between(start,end).toMillis());
        
         
    }
}
