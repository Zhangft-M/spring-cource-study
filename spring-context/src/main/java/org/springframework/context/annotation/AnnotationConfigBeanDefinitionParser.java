/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.annotation;

import java.util.Set;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.event.DefaultEventListenerFactory;
import org.springframework.context.event.EventListenerMethodProcessor;
import org.w3c.dom.Element;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.parsing.CompositeComponentDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.lang.Nullable;

/**
 * todo:解析<context:annotation-config></context:annotation-config>标签
 * 如果定义了注解驱动,则系统会注册一系列的beanDefinitionProcessor来处理beanDefinition
 * Parser for the &lt;context:annotation-config/&gt; element.
 *
 * @author Mark Fisher
 * @author Juergen Hoeller
 * @author Christian Dupuis
 * @since 2.5
 * @see AnnotationConfigUtils
 */
public class AnnotationConfigBeanDefinitionParser implements BeanDefinitionParser {

	@Override
	@Nullable
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		Object source = parserContext.extractSource(element);

		// 只要在配置文件开启了注解扫描
		// 在这儿注册configuration注解配置类的beanDefinition
		// Obtain bean definitions for all relevant BeanPostProcessors.
		/**
		 * 默认会注册
		 * @see ConfigurationClassPostProcessor
		 * @see AutowiredAnnotationBeanPostProcessor
		 * @see org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor
		 * @see EventListenerMethodProcessor
		 * @see DefaultEventListenerFactory
		 */
		Set<BeanDefinitionHolder> processorDefinitions =
				AnnotationConfigUtils.registerAnnotationConfigProcessors(parserContext.getRegistry(), source);

		// Register component for the surrounding <context:annotation-config> element.
		// 为<context:annotation-config>也定义一个CompositeComponentDefinition组件
		// 该组件是一个复杂型组件,理解为装饰者模式,在该类中维护着一个
		// org.springframework.beans.factory.parsing.CompositeComponentDefinition.nestedComponents属性
		// 该属性中放入的就是由于<context:annotation-config>被定义以后需要的一些组件,就是之前实例化的那五个
		CompositeComponentDefinition compDefinition = new CompositeComponentDefinition(element.getTagName(), source);
		parserContext.pushContainingComponent(compDefinition);

		// 将之前实例化的五个beanDefinition进行包装后放入CompositeComponentDefinition维护的队列中
		// 我的理解就是分组,将功能相同的组件进行一次分组
		// Nest the concrete beans in the surrounding component.
		for (BeanDefinitionHolder processorDefinition : processorDefinitions) {
			parserContext.registerComponent(new BeanComponentDefinition(processorDefinition));
		}

		// 这一步就牛逼了,
		// 运用队列的性质,对组件再进行包装,针对的是有多个复杂型CompositeComponentDefinition组件
		// Finally register the composite component.
		parserContext.popAndRegisterContainingComponent();

		return null;
	}

}
