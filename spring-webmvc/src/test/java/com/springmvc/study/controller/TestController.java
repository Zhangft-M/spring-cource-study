package com.springmvc.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: spring
 * @description:
 * @author: Micah
 * @create: 2021-02-20 21:06
 **/
@Controller
@RequestMapping("/test")
public class TestController {

	@ResponseBody
	@GetMapping("/testMethod")
	public String test(){
		return "hello world";
	}
}
