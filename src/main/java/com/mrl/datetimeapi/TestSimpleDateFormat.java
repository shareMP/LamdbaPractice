package com.mrl.datetimeapi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestSimpleDateFormat
{
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyyMMdd");
        
        Callable<LocalDate> task = new Callable<LocalDate>()
        {
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("20171111", dft);
            }
        };
        
        ExecutorService pool = Executors.newFixedThreadPool(10);//长度为10线程池
        
        List<Future<LocalDate>> reults = new ArrayList<>(); 
        
        for (int i = 0; i < 10; i++) {
            reults.add(pool.submit(task));
        }
        
        for (Future<LocalDate> future : reults) {
            System.out.println(future.get());
        }
        
        pool.shutdown();
    }
}
