package com.spring.ioc.test.config;

import com.spring.ioc.test.A;
import com.spring.ioc.test.B;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: spring
 * @description:
 * @author: Micah
 * @create: 2021-02-12 16:31
 **/
@Configuration
public class IocConfiguration {

	@Bean
	public A a() {
		return new A();
	}

	@Bean
	public B b(){
		return new B();
	}

}
