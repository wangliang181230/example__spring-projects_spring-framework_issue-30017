package cn.wangliang181230.springprojects.springboot.issue34343;

public class MyService implements IService {

	@Override
	public String foo() {
		System.out.println("Running foo.");
		return "foo";
	}

	@Override
	public String bar() {
		System.out.println("Running bar.");
		return "bar";
	}
}
