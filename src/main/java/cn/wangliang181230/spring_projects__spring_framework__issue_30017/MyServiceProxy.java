package cn.wangliang181230.spring_projects__spring_framework__issue_30017;

import java.util.Objects;

public class MyServiceProxy implements cn.wangliang181230.spring_projects__spring_framework__issue_30017.IService {

	private final cn.wangliang181230.spring_projects__spring_framework__issue_30017.IService service;


	public MyServiceProxy(cn.wangliang181230.spring_projects__spring_framework__issue_30017.IService service) {
		Objects.requireNonNull(service, "The service must not be null.");
		this.service = service;
	}


	@Override
	public String foo() {
		System.out.println("Before running foo.");
		try {
			return "proxy-" + service.foo();
		} finally {
			System.out.println("After running foo.");
		}
	}

	@Override
	public String bar() {
		System.out.println("Before running bar.");
		try {
			return "proxy-" + service.bar();
		} finally {
			System.out.println("After running bar.");
		}
	}
}
