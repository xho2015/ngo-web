package org.ngo.web.tutorial;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceLocator {
	private ServiceLocator() {
	}

	public static final ServiceLocator Instance = new ServiceLocator();
	
	public final Object get(Class clazz) {
		Object proxy =  generateProxy(clazz);
		//System.out.println(String.format("proxy=%s",proxy.toString()));
		return proxy;
	}
	
	private final Map<String,Object> CACHE = new ConcurrentHashMap<String, Object>();

	private Object generateProxy(Class clazz) {
		if (CACHE.containsKey(clazz.getName()))
			return CACHE.get(clazz.getName());
		InvocationHandler handler = new UnderlayingInvocationHandler(clazz);
		ClassLoader classloader = handler.getClass().getClassLoader();
		Class[] interfaces = clazz.getInterfaces();
		Object proxy =  Proxy.newProxyInstance(classloader, interfaces, handler);
		CACHE.put(clazz.getName(),proxy);
		return proxy;
	}

	private static class UnderlayingInvocationHandler implements InvocationHandler {
		private RPCInvoker rpc;
		private Class clazz;

		public UnderlayingInvocationHandler(Class serviceClazz) {
			this.clazz = serviceClazz;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			
			if (clazz.isAnnotationPresent(RemoteService.class))
				//RPC invoke
				return new RPCInvoker(clazz.getName(), method.getName(), args).invoke();
			else
				//local invoke
				return method.invoke(clazz.newInstance(), args);
		}
	}

	private static class RPCInvoker {
		private String service;
		private String method;
		private Object[] args;

		public RPCInvoker(String service, String method, Object[] args) {
			this.service = service;
			this.method = method;
			this.args = args;
		}

		public Object invoke() {
			System.out.println("connect to remote service node...");
			System.out.println(String.format("invoke remote service [%s@%s(%s)]",service,method,args));
			System.out.println("get returned object");
			return new User((Long)args[0], "RemoteUser", 10);

		}
	}

}
