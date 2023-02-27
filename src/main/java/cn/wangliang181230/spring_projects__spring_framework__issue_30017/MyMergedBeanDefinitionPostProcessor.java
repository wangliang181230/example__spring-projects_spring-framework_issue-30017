package cn.wangliang181230.spring_projects__spring_framework__issue_30017;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.type.StandardMethodMetadata;

public class MyMergedBeanDefinitionPostProcessor implements MergedBeanDefinitionPostProcessor {

	private static final Map<String, MyAnnotation> cache = new HashMap<>();


	@Override
	public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
		if (IService.class.isAssignableFrom(beanType)) {
			//if (AotDetector.useGeneratedArtifacts()) {
				if (beanDefinition.getSource() == null) {
					throw new BeanDefinitionValidationException("The source of the beanDefinition is null");
				}
			//}

			MyAnnotation anno = this.getAnnotation(beanDefinition);
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

	private MyAnnotation getAnnotation(RootBeanDefinition beanDefinition) {
		MyAnnotation anno = null;
		if (beanDefinition.getSource() instanceof StandardMethodMetadata sentinelSource) {
			anno = sentinelSource.getIntrospectedMethod().getAnnotation(MyAnnotation.class);
		}

		if (anno == null && beanDefinition.getResolvedFactoryMethod() != null) {
			anno = beanDefinition.getResolvedFactoryMethod().getAnnotation(MyAnnotation.class);
		}

		return anno;
	}

}
