package com.xh.eduservice;

import org.junit.Test;
import org.springframework.cache.annotation.Cacheable;

public class test {

    @Cacheable(key = "'aa'", value = "key")
    public String testRedis(){
        return "aaaaa";
    }

    @Test
    public void test1(){
        System.out.println(testRedis());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
