package com.dong.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

/**
 * @ClassName ConsumerClient
 * @Description TODO
 * @Author admin
 * @Date 2018/12/24 14:29
 **/
public class ConsumerClient {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-consumer.xml");
        context.start();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String message = scanner.next();

            //获取接口
            ServiceAPI serviceAPI = (ServiceAPI) context.getBean("consumerService");
            System.out.println(serviceAPI.sendMsg(message));
        }
    }

}
