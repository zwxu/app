package com.ustc.zwxu.app.InvocationHandler;


class Professor implements Cloneable 
{ 
  String name; 
  int age; 
  Professor(String name,int age) 
  { 
  this.name=name; 
  this.age=age; 
  } 
 public Object clone() 
  { 
   Object o=null; 
  try
   { 
    o=super.clone(); 
   } 
  catch(CloneNotSupportedException e) 
   { 
    System.out.println(e.toString()); 
   } 
  return o; 
  } 
}
class Student implements Cloneable 
{ 
	  String name;// 常量对象。 
	  int age; 
	  Professor p;// 学生1和学生2的引用值都是一样的。 
	  Student(String name,int age,Professor p) 
	  { 
		  this.name=name; 
		  this.age=age; 
		  this.p=p; 
	  } 
	 public Object clone() 
	  { 
		 Student o=null; 
		    try
		   { 
		    	//在运行时刻，Object中的clone()要识别出你要复制的是哪一个对象
			  o=(Student)super.clone(); 
		   } 
		  catch(CloneNotSupportedException e) 
		   { 
			  System.out.println(e.toString()); 
		   } 
	       o.p=(Professor) p.clone();
		   return o; 
	  }  
}
/*
 * 浅负责无法改变引用的值
 * 
 */
public class CloneDemo{
 public static void main(String[] args) 
 { 
	  Professor p=new Professor("wangwu",50); 
	  Student s1=new Student("zhangsan",18,p); 
	  Student s2=(Student)s1.clone(); 
	  s2.p.name="lisi"; 
	  s2.p.age=30;  
	  System.out.println("name="+s1.p.name+","+"age="+s1.p.age);
	  System.out.println("name="+s2.p.name+","+"age="+s2.p.age);
	  //输出结果学生1和2的教授成为lisi,age为30。
  } 
}