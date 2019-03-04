package test.proxytest.jdk;

import java.lang.reflect.Proxy;

public class ClientTest {
	
	public static void main(String[] args) {
		
		UserService service=new UserServiceImpl();
		UserService o=(UserService)Proxy.newProxyInstance(service.getClass().getClassLoader(), service.getClass().getInterfaces(),new ProxyTest(service));
		o.printsss();
		
	}
}
