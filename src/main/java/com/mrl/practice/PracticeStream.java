package com.mrl.practice;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 *  练习题
 *  [功能详细描述]
 * @作者 lwqMR
 * @version [版本号, 2017年12月16日]
 * @see [相关类/方法]
 * @since [产品/模块版本] 
 */
public class PracticeStream
{
    @Test
    public void test1(){
        /*
         * 给定一个数字列表，返回由平方组成的数字列表
         * {1,2,3,4}
         * */
        Arrays.asList(1,2,3,4,5)
            .stream()  //获取流
            .map(x->x*x) //映射
            .collect(Collectors.toList())  //收集
            .forEach(System.out::println); //打印
    }
    
    @Test
    public void test2(){
        //用map和redure流中有多少个Teacher 
        List<Teacher> list = Arrays.asList(new Teacher("测试",20),new Teacher("张老师", 30),new Teacher("李老师",20),new Teacher("王老师",25));
        
        Optional<Integer> reduce = list.stream().map(e-> 1).reduce(Integer::sum);
        System.out.println(reduce.get());
    }
    
    @Test
    public void test3(){
        
    }
    
}
