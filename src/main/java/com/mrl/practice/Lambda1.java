package com.mrl.practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

public class Lambda1
{
    @Test
    public void test1() {

        //匿名内部类写法
        Thread t1 = new Thread(new Runnable()
        {

            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        t1.start();

        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
        ;

        System.out.println(Thread.currentThread().getName());
    }

    @Test
    public void test2() {
        System.out.println(operaterStr(x -> x.toUpperCase(), "hello"));
        System.out.println(operaterStr(str -> {
            //对字符串进行自定义的操作
            //用的是函数式接口
            //还可以用策略模式,先定义一个接口,由相应的实现判断是否满足条件
            return str;
        }, "hello"));
    }

    @Test
    public void test3() {
        //内置的函数式接口
        //Consumer<T> 消费型    void accept(T t)
        //Supplier<T> 供给型   T get()
        //Function<T,R> 函数型  R apply(T t);
        //Predicate<T> 断定型  booelan boolean test(T t)
        List<String> sourceList = new ArrayList<>();
        sourceList.add("abc");
        sourceList.add("def");
        sourceList.add("JJJJ");
        List<String> list = filterList(sourceList, sourceStr -> {
            return sourceStr.length() > 3;
        });

        list.forEach(System.out::println);

        //Function
        String handleStr = handleStr("abc", sourceStr -> sourceStr.toUpperCase());
        System.out.println(handleStr);

        ////Supplier<T> 供给型接口 :
        List<Integer> numList = getNumList(5, () -> new Random().nextInt(10));
        numList.forEach((x) -> System.out.println(x));

    }

    @Test
    public void test4() {
        //方法引用，构造器引用
        //当要传递给Lambda体的操作，已经有实现的方法了，可以使用方法引用！
        //实现抽象方法的参数列表，必须与方法引用方法的参数列表保持一致
        
        //使用操作符 “::” 将方法名和对象或类的名字分隔开来
        //对象::实例方法
        //类::静态方法
        //类::实例方法
        
//        (x) - > System.out.println(x); = System.out::println;
        
        BinaryOperator<Double> bo = (x,y)-> Math.pow(x, y);
        BinaryOperator<Double> bo1 = Math::pow;
        
        //类::静态方法
        Comparator<Integer> com = (x,y) -> Integer.compare(x, y);
        TreeSet<Integer> treeSet = new TreeSet<>(com);
        treeSet.add(1);
        treeSet.add(2);
        treeSet.forEach(System.out::println);
      
        Comparator<Integer> com2 = Integer::compare;
        
        
        //当需要引用方法的第一个参数是调用对象，
        //并且第二个参数是需要引用方法的第二个参数(或无参数)时：ClassName::methodName
        //类::实例方法
        BiPredicate<String,String> bp = (x,y) -> x.equals(y);
        System.out.println(bp.test("avv", "avv"));
        
        BiPredicate<String,String> bp2 = String::equals;
        System.out.println(bp2.test("111", "111"));
        
        
        //构造器引用
        Function<String, Teacher> fun = (name) -> new Teacher();
//        Function<String, Teacher> fun2 = Teacher::new;
        
        //数组引用
        Function<Integer, String[]> fun3 = (args) -> new String[args];
        String[] apply = fun3.apply(10);
        System.out.println(apply);
        
        Function<Integer, String[]> fun4 = String[]::new;
    }
    
    

    //需求：产生指定个数的整数，并放入集合中
    public List<Integer> getNumList(int count, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(sup.get());
        }
        return list;
    }

    //需求：用于处理字符串  函数式接口
    public String handleStr(String sourceStr, Function<String, String> fun) {
        return fun.apply(sourceStr);
    }

    //需求：将满足条件的字符串，放入集合中
    public List<String> filterList(List<String> sourceList, Predicate<String> pre) {
        List<String> targetList = new ArrayList<>();

        for (String str : sourceList) {
            if (pre.test(str)) {
                targetList.add(str);
            }
        }

        return targetList;
    }

    public String operaterStr(MyFunInter<String> op, String sourceStr) {
        return op.getValue(sourceStr);
    }
}

@FunctionalInterface
interface MyFunInter<T>
{
    T getValue(T t);
}

@FunctionalInterface
interface MyFunInter2<T>
{
    boolean getValue(T t);
}

class Student{
    
    String name;
    String age;
    
   public Student() {
        
    }

    public Student(String name, String age) {
        super();
        this.name = name;
        this.age = age;
    }

    public Student(String name) {
        super();
        this.name = name;
    }
    
    
    
    
    
}
