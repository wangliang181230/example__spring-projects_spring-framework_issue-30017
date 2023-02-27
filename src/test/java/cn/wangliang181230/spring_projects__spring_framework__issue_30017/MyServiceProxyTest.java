package cn.wangliang181230.spring_projects__spring_framework__issue_30017;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled("For debugging")
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
