package com.ustc.zwxu.app.InvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;





public class SubjectProxy<T> implements InvocationHandler {
	private T targetService;
	public SubjectProxy(T targetService) {
		this.targetService = targetService;

	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if("say".equals(method.getName())){  
            System.out.println("++++++before " + method.getName() + "++++++");  
            Object result = method.invoke(targetService, args);  
            System.out.println("++++++after " + method.getName() + "++++++");  
            return result;  
        }else{  
            Object result = method.invoke(targetService, args);  
            return result;  
        }  

	}
	
	
}
