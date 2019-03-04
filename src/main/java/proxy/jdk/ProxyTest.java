package test.proxytest.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyTest implements InvocationHandler {
	private Object target;
	
	public void setTarget(Object target) {
		this.target = target;
	}
	
	public ProxyTest(Object target) {
		super();
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		s();
		Object result = method.invoke(target, args);
		System.out.println("之后执行");
		return result;
	}
	
	public void s(){
		System.out.println("之前执行");
	}

	
}
