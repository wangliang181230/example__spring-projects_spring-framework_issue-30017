package cn.wangliang181230.spring_projects__spring_framework__issue_30017;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.type.StandardMethodMetadata;

public class MyMergedBeanDefinitionPostProcessor implements MergedBeanDefinitionPostProcessor, BeanFactoryAware {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyMergedBeanDefinitionPostProcessor.class);


	private ListableBeanFactory listableBeanFactory;

	private static final Map<String, MyAnnotation> cache = new HashMap<>();


	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		if (beanFactory instanceof ListableBeanFactory listableBeanFactory) {
			this.listableBeanFactory = listableBeanFactory;
		}
	}

	@Override
	public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
		if (IService.class.isAssignableFrom(beanType)) {
			//if (AotDetector.useGeneratedArtifacts()) {
			//	if (beanDefinition.getSource() == null) {
			//		throw new BeanDefinitionValidationException("The source of the beanDefinition is null");
			//	}
			//}

			MyAnnotation anno = this.getAnnotation(beanDefinition, beanName);

			if (anno != null) {
				cache.put(beanName, anno);
			}
		}
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof IService) {
			MyAnnotation anno = cache.get(beanName);
			if (anno != null) {
				return new MyServiceProxy((IService) bean);
			}
		}

		return bean;
	}

	private MyAnnotation getAnnotation(RootBeanDefinition beanDefinition, String beanName) {
		MyAnnotation anno = null;

		if (this.listableBeanFactory != null) {
			anno = this.listableBeanFactory.findAnnotationOnBean(beanName, MyAnnotation.class);
			if (anno != null) {
				LOGGER.info("Annotation from listableBeanFactory");
			}
		}

		if (anno == null && beanDefinition.getSource() instanceof StandardMethodMetadata sentinelSource) {
			anno = sentinelSource.getIntrospectedMethod().getAnnotation(MyAnnotation.class);
			if (anno != null) {
				LOGGER.info("Annotation from source introspectedMethod");
			}
		}

		if (anno == null && beanDefinition.getResolvedFactoryMethod() != null) {
			anno = beanDefinition.getResolvedFactoryMethod().getAnnotation(MyAnnotation.class);
			if (anno != null) {
				LOGGER.info("Annotation from resolvedFactoryMethod");
			}
		}

		return anno;
	}

}
