package cn.wangliang181230.springprojects.springboot.issue34343;

public class MyService implements IService {

	@Override
	public void foo() {
		System.out.println("Running foo.");
	}

	@Override
	public void bar() {
		System.out.println("Running bar.");
	}
}
