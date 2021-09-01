package com.example.layerd_architecture.dao;


import java.util.Date;

import com.example.layerd_architecture.dto.Guestbook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.layerd_architecture.config.ApplicationConfig;
import com.example.layerd_architecture.dto.Log;

public class GuestbookDaoTest {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		GuestbookDao guestbookDao = ac.getBean(GuestbookDao.class);

		Guestbook guestbook = new Guestbook();
		guestbook.setName("정현모");
		guestbook.setContent("반갑습니다. 여러분.");
		guestbook.setRegdate(new Date());
		Long id = guestbookDao.insert(guestbook);
		System.out.println("id : " + id);

        LogDao logDao = ac.getBean(LogDao.class);
        Log log = new Log();
        log.setIp("127.0.0.1");
        log.setMethod("insert");
        log.setRegdate(new Date());
        logDao.insert(log);
    }

}