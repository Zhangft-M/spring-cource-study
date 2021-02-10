package com.spirng.ioc.test.main;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: spring
 * @description:
 * @author: Micah
 * @create: 2021-02-05 21:40
 **/
public class IocApplicationTestMain {

	@Test
	public void testIocWithXml(){
		ClassPathXmlApplicationContext cxa = new ClassPathXmlApplicationContext("bean.xml");
		Object a = cxa.getBean("a");
		System.out.println(a);
	}

	@Test
	public void testIocWithAnnotation(){
		AnnotationConfigApplicationContext aac = new AnnotationConfigApplicationContext();

	}

}
