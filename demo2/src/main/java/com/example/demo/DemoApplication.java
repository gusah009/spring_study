package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext(
                "classpath:applicationContext.xml");
        System.out.println("초기화 완료.");

        UserBean userBean = (UserBean)ac.getBean("userBean");
        userBean.setName("kim");
        System.out.println(userBean.getName());

        UserBean userBean2 = (UserBean)ac.getBean("userBean");
        if(userBean == userBean2) {
            System.out.println("같은 인스턴스이다.");
        }
    }

}
