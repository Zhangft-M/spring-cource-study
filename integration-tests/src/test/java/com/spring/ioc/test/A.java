package com.spring.ioc.test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: spring
 * @description:
 * @author: Micah
 * @create: 2021-02-05 21:40
 **/
public class A {

	@Autowired
	private B b;

	private String str;

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public B getB() {
		return b;
	}
}
