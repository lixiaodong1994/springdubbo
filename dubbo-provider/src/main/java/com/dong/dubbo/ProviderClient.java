package com.dong.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @ClassName ProviderClient
 * @Description 启动
 * @Author LXD
 * @Date 2018/12/24 14:19
 **/
public class ProviderClient {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-provider.xml");
        applicationContext.start();

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
