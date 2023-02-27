package cn.wangliang181230.spring_projects__spring_framework__issue_30017;

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
