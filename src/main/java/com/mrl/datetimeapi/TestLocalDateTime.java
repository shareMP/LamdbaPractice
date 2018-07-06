package com.mrl.datetimeapi;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

import org.junit.Test;

public class TestLocalDateTime
{
    public static void main(String[] args) throws InterruptedException {
        //1.LocalDate
        //2.localTime
        //3.LocalDateTime

        //使用方式一样

        LocalDateTime ldt = LocalDateTime.now();//获取当前系统时间,2017-12-17T00:08:03.712标准
        LocalDateTime ldt2 = LocalDateTime.of(2017, 10, 1, 8, 8, 8);//获取指定时间
        //        System.out.println(ldt2); //2017-10-01T08:08:08

        LocalDateTime plusYears = ldt.plusYears(2);//加两年
        System.out.println(plusYears);

        LocalDateTime minusMonths = ldt.minusMonths(2);//减两个月
        System.out.println(minusMonths);
        System.out.println(ldt.getYear()); //年
        System.out.println(ldt.getMonthValue()); //月
        System.out.println(ldt.getMonth().getValue());//月
        System.out.println(ldt.getDayOfMonth()); //日
        System.out.println(ldt.getHour());//小时

        //2.Instant:时间戳(Unix元年 1970年1月1日 0时0分0秒 到某个时间的之间的一个毫秒值)
        Instant instant = Instant.now();//默认获取的是以UTC时区基础的，格林威治时间,时间戳
        System.out.println(instant);

        OffsetDateTime odt = instant.atOffset(ZoneOffset.ofHours(8));//偏移八小时
        System.out.println(odt);

        System.out.println(instant.toEpochMilli());//转成毫秒时间

        //计算时间间隔
        //       Duration  计算时间间隔
        //       Period  日期间隔
        Instant instant2 = Instant.now();
        Thread.sleep(1000);
        Instant instant3 = Instant.now();

        Duration between = Duration.between(instant2, instant3);
        System.out.println(between.toMillis());

        System.out.println("-----------------");

        LocalTime now = LocalTime.now();
        Thread.sleep(100);
        LocalTime now2 = LocalTime.now();
        Duration between2 = Duration.between(now, now2);
        System.out.println(between2.toMillis());

        //日期
        LocalDate now3 = LocalDate.now();
        LocalDate now4 = LocalDate.of(2017, 1, 1);
        Period between3 = Period.between(now4, now3);
        System.out.println(between3.getDays());
    }

    @Test
    public void test2() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime with = now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(with);

        //自定义:下一个工作日
        LocalDateTime with2 = now.with((t) -> {
            //计算下一个工作日
            LocalDateTime ldt = (LocalDateTime) t;
            DayOfWeek dayOfWeek = ldt.getDayOfWeek();//周几
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return ldt.plusDays(3);
            }
            else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return ldt.plusDays(2);
            }
            else {
                return ldt.plusDays(1);
            }
        });

        System.out.println(with2);
    }

    @Test
    public void test3() {
        //        DateTimeFormatter  格式化日期和时间
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime now = LocalDateTime.now();
        String format = now.format(dtf);
        System.out.println(format);
        
        System.out.println("--------------");
        DateTimeFormatter dtf2 =  DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String format2 = dtf2.format(now);
        System.out.println(format2);
        
        //解析回日期
        LocalDateTime parse = now.parse(format2,dtf2);
        System.out.println(parse);
    }
    
    
    @Test
    public void test4(){
//        Java8 中加入了对时区的支持，带时区的时间为分别为：
//        ZonedDate、ZonedTime、ZonedDateTime
        
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds(); //所有时区
        availableZoneIds.forEach(System.out::println);
        
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Zagreb"));//指定时区
        System.out.println(now);
        
        
        
    }
}
