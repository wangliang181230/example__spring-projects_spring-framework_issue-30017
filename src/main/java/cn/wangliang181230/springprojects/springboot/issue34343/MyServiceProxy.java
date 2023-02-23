package cn.wangliang181230.springprojects.springboot.issue34343;

import java.util.Objects;

public class MyServiceProxy implements IService {

	private final IService service;


	public MyServiceProxy(IService service) {
		Objects.requireNonNull(service, "The service must not be null.");
		this.service = service;
	}


	@Override
	public void foo() {
		System.out.println("Before running foo.");
		try {
			service.foo();
		} finally {
			System.out.println("After running foo.");
		}
	}

	@Override
	public void bar() {
		System.out.println("Before running bar.");
		try {
			service.bar();
		} finally {
			System.out.println("After running bar.");
		}
	}
}
