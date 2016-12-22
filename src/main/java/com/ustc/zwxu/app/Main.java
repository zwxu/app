package com.ustc.zwxu.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
           ApplicationContext app =new ClassPathXmlApplicationContext("classpath:dispatcher-servlet.xml" ); 
           	//此时bean的创建有factory-bean接管，而不再是Spring
            Subject2 s2 = (Subject2) app.getBean("subject2" );   
            s2.say();
            Subject s = (Subject) app.getBean("subject" );
            s.say();
        
            
   
       
            

	}

}

