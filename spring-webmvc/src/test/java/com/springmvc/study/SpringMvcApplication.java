package com.springmvc.study;

import org.junit.jupiter.api.Test;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.StaticWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.SimpleWebApplicationContext;
import org.springframework.web.testfixture.http.server.reactive.bootstrap.TomcatHttpServer;
import org.springframework.web.testfixture.servlet.MockServletConfig;
import org.springframework.web.testfixture.servlet.MockServletContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;

/**
 * @program: spring
 * @description:
 * @author: Micah
 * @create: 2021-02-19 19:46
 **/
public class SpringMvcApplication {

	@Test
	public void testMvc() throws ServletException {
		MockServletContext mockServletContext = new MockServletContext("");
		MockServletConfig mockServletConfig = new MockServletConfig();
		mockServletConfig.addInitParameter(ContextLoader.CONFIG_LOCATION_PARAM,"/com/springmvc/study/spring-mvc.xml");
		DispatcherServlet dispatcherServlet = new DispatcherServlet();
		ContextLoaderListener listener = new ContextLoaderListener();
		// ----------------
		mockServletContext.addInitParameter(ContextLoader.CONFIG_LOCATION_PARAM,"/com/springmvc/study/spring-mvc.xml");
		listener.contextInitialized(new ServletContextEvent(mockServletContext));
		// ---------------
		dispatcherServlet.setApplicationContext(new SimpleWebApplicationContext());
		dispatcherServlet.init(mockServletConfig);
	}
}
