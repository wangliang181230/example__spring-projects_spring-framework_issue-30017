package cn.wangliang181230.spring_projects__spring_framework__issue_30017;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class MyConfiguration {

	@Bean
	public static cn.wangliang181230.spring_projects__spring_framework__issue_30017.MyMergedBeanDefinitionPostProcessor myMergedBeanDefinitionPostProcessor() {
		return new cn.wangliang181230.spring_projects__spring_framework__issue_30017.MyMergedBeanDefinitionPostProcessor();
	}


	@cn.wangliang181230.spring_projects__spring_framework__issue_30017.MyAnnotation
	@Bean
	public cn.wangliang181230.spring_projects__spring_framework__issue_30017.IService myService() {
		return new cn.wangliang181230.spring_projects__spring_framework__issue_30017.MyService();
	}

}
