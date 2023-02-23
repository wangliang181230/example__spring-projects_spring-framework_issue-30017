package cn.wangliang181230.springprojects.springboot.issue34343;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyServiceProxyTest {

	@Autowired
	private IService service;


	@Test
	public void testFoo() {
		Assertions.assertEquals("proxy-foo", service.foo());
	}

	@Test
	public void testBar() {
		Assertions.assertEquals("proxy-bar", service.bar());
	}

}
