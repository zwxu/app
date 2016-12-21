package com.ustc.zwxu.app;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Set;

import org.springframework.util.ClassUtils;




public class SubjectFactory<T>{
	
	private Class<T> target;
	

	public Class<T> getTarget() {
		return target;
	}


	public void setTarget(Class<T> target) {
		this.target = target;
	}


	private T serviceInterface;

	
	public T getServiceInterface() {
		return serviceInterface;
	}


	public void setServiceInterface(T serviceInterface) {
		this.serviceInterface = serviceInterface;
	}


	@SuppressWarnings("unchecked")
	public T getObject() throws Exception {


		InvocationHandler handler = new SubjectProxy<T>(serviceInterface);

		return (T) Proxy.newProxyInstance(serviceInterface.getClass().getClassLoader(), 
				serviceInterface.getClass().getInterfaces(), handler);
	}
	
	public T getObject2() throws Exception {
		T targetService = getTarget(target);
		InvocationHandler handler = new SubjectProxy<T>(targetService);

		return (T) Proxy.newProxyInstance(target.getClassLoader(), 
				new Class[]{target}, handler);
	}
	
	 @SuppressWarnings("unchecked")
		protected T getTarget(Class<T> entityClass) throws Exception{
		 String name = ClassUtil.getAllAssignedClass(entityClass).get(0).getName();
		 //System.out.println(name);
			     Class<?> demo = Class.forName(name);
				 return (T) demo.newInstance();
			 }
	 
	 
		 

}