package com.spring.ioc.test.main;

import com.spring.ioc.test.A;
import com.spring.ioc.test.B;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

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
		for (String beanDefinitionName : cxa.getBeanDefinitionNames()) {
			System.out.println(beanDefinitionName);
		}
		XmlWebApplicationContext xwa = new XmlWebApplicationContext();
		A a = cxa.getBean("a", A.class);
		B b = cxa.getBean("b",B.class);
		System.out.println(b);
 		System.out.println(a.getB());
		System.out.println(a);


	}

	@Test
	public void testIocWithAnnotation(){
		AnnotationConfigApplicationContext aac = new AnnotationConfigApplicationContext("com.spring.ioc.test.service");
		for (String beanDefinitionName : aac.getBeanDefinitionNames()) {
			System.out.println(beanDefinitionName);
		}
	}

	@Test
	public void testWeb(){

	}

}
