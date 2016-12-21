package com.ustc.zwxu.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
           ApplicationContext app =new ClassPathXmlApplicationContext("classpath:dispatcher-servlet.xml" ); 
         /* SubjectFactory factory = (SubjectFactory) app.getBean("factory" );
            Subject s1 = (Subject) factory.getObject2();
            s1.say();
           SubjectFactory factory2 = (SubjectFactory) app.getBean("factory2" );
            Subject2 s2 = (Subject2) factory2.getObject2();
            s2.say();*/
           Subject s1 = new RealSubject();
           s1.say();
            
   
       
            

	}

}

