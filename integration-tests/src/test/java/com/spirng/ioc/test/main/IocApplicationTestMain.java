package com.spirng.ioc.test.main;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: spring
 * @description:
 * @author: Micah
 * @create: 2021-02-05 21:40
 **/
public class IocApplicationTestMain {

	@Test
	public void testIoc(){
		ClassPathXmlApplicationContext cxa = new ClassPathXmlApplicationContext("bean.xml");
		Object a = cxa.getBean("a");
		System.out.println(a);
	}
}
