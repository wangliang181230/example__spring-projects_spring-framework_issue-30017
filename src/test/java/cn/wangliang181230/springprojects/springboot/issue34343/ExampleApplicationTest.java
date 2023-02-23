package cn.wangliang181230.springprojects.springboot.issue34343;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ExampleApplicationTest {

	/**
	 * Please run this test after execute `mvn clean install -Pnative -e` .
	 *
	 * @throws Exception the exception
	 */
	@Test
	@Disabled("For debugging")
	public void runWithSpringAotModeAfterProcessAot() throws Exception {
		// Enable spring-aot-mode
		System.setProperty("spring.aot.enabled", "true");

		// Start the application
		ExampleApplication.main(new String[0]);
	}

}
