package cn.wangliang181230.springprojects.springboot.issue34343;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class MyConfiguration {

	@Bean
	public static MyMergedBeanDefinitionPostProcessor myMergedBeanDefinitionPostProcessor() {
		return new MyMergedBeanDefinitionPostProcessor();
	}


	@MyAnnotation
	@Bean
	public IService myService() {
		return new MyService();
	}

}
