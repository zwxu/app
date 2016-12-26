package com.ustc.zwxu.app.Velocity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app =new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");  
		CommonPushValueGenerator generator = (CommonPushValueGenerator) app.getBean("pushGenerator");
		String value = generator.genValue(generator.getTitle(), generator.generateMap());
		System.out.println(value);
		
	
		
        

		
	}
	
}
