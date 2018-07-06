package com.mrl.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

/**
 *  StreamAPI
 *  //处理集合
 *  //查找、过滤和映射数据等操作
 *  //类似SQL的查询-对比
 *  //并行操作
 *  [功能详细描述]
 * @作者 lwqMR
 * @version [版本号, 2017年12月14日]
 * @see [相关类/方法]
 * @since [产品/模块版本] 
 */
public class StreamAPI
{
    @Test
    public void test1() {
        //Stream 流 专注于计算
        //不存储元素，不改变原对象，返回一个持有结果的新的Stream，延迟执行(需要结果的时候才会执行),终止操作的时候一次计算
        //创建(获取流  中间操作 终止操作

        //        int[] array = new int[]{1,2,3,4};
        //        Arrays.stream(array).forEach(System.out::println);

        //        Stream.of(2,4,555,4,5).sorted(Integer::compare).forEach(System.out::println);

        //创建无限流
        //迭代
        Stream<Integer> stream = Stream.iterate(0, x -> x + 2).limit(10);
        //        stream.forEach(System.out::println);

        //生成
        Stream.generate(() -> new Random().nextInt(20)).limit(10);

        //filter  distinct去重  limit skip跳过
        //        Stream.iterate(0, x->x+2)
        //            .limit(10)
        //            .filter((x)-> x>5)
        //            .forEach(System.out::println);

        Stream.iterate(0, x -> x + 2).limit(10).skip(8).forEach(System.out::println);

    }

    //中间操作
    @Test
    public void test2() {
        //筛选与切片
        //生成
        Stream.generate(() -> new Random().nextInt(20)).limit(10);

        //filter  distinct去重  limit skip跳过
        //        Stream.iterate(0, x->x+2)
        //            .limit(10)
        //            .filter((x)-> x>5)
        //            .forEach(System.out::println);

        Stream.iterate(0, x -> x + 2).limit(10).skip(8).forEach(System.out::println);

        //映射
        //将集合里的每一个元素转换,接收一个函数   每个元素都  应用到函数上 
        List<String> asList = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        asList.stream().map((str) -> str.toUpperCase()).forEach(System.out::println);

        List<Teacher> list = Arrays.asList(new Teacher("张老师", 30),new Teacher("李老师",20),new Teacher("王老师",25));
       
        //取出老师的名字
        list.stream()
            .map(Teacher::getName)
            .forEach(System.out::println);
        
        Stream<Stream<Character>> stram = asList.stream().map(StreamAPI::filterCharacter);
        stram.forEach(sm -> sm.forEach(System.out::println));
     
        //flatmap
//        接收一个函数作为参数，将流中的每个值都换成另 一个流，然后把所有流连接成一个流
        Stream<Character> flatMap = asList.stream().flatMap(StreamAPI::filterCharacter);
        flatMap.forEach(System.out::println);
        
    }
    
    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();
        for (Character character : str.toCharArray()) {
            list.add(character);
        }
        return list.stream();
    }
    
    //终止操作
    @Test
    public void test3(){
        List<Teacher> list = Arrays.asList(new Teacher("测试",20),new Teacher("张老师", 30),new Teacher("李老师",20),new Teacher("王老师",25));
        
//        //规约
//        List<Integer> asList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
//        
//        Integer reduce = asList.stream().reduce(0, (x,y)->x+y);
//        System.out.println(reduce);
//        
//        Optional<Integer> op = list.stream()
//            .map(Teacher::getAge)
//            .reduce(Integer::sum);
//    
//        Integer integer = op.get();
//        System.out.println(integer);
//        
//        //收集  ，收集老师的名字到集合
//        List<String> collect = list.stream().map(Teacher::getName).collect(Collectors.toList());
//        collect.forEach(System.out::println);
//        
//        Set<String> collect2 = list.stream().map(Teacher::getName).collect(Collectors.toSet());
//        collect2.forEach(System.out::println);
//        
//        Optional<Teacher> collect3 = list.stream().collect(Collectors.maxBy((x,y)->Double.compare(x.getAge(),y.getAge())));
//        System.out.println(collect3.get().getAge());
//        
//        Optional<Integer> collect4 = list.stream().map(Teacher::getAge).collect(Collectors.minBy(Integer::compare));
//        Integer integer2 = collect4.get();
//        System.out.println(integer2);
        
        //分组
//        list.add(new Teacher("测试",20));
        Map<Integer, List<Teacher>> collect5 = list.stream().collect(Collectors.groupingBy(Teacher::getAge));
        System.out.println(collect5);
        
        //组函数获取
        IntSummaryStatistics iss = list.stream().collect(Collectors.summarizingInt(Teacher::getAge));
        iss.getAverage();
        iss.getCount();
        
    }

}

