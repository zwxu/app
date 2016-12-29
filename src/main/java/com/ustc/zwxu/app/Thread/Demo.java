/*package com.ustc.zwxu.app.Thread;

public class Demo {

	public static void main(String[] args) {
		Data d = new Data(1);
		for ( int i = 0; i < 10; i++) {    
            
	          new Thread(new AddThread(d)).start();
	   
	          new Thread(new MinusThread(d)).start();
	   
	      }     

	}

}

class Data
{
	private int i;
	
	public Data(int i) {
		super();
		this.i = i;
	}

	public synchronized void  add()
	{
		i++;
		System.out.println(Thread.currentThread().getName()+ "add :"+i);
	}
	
	public synchronized void  minus()
	{
		i--;
		System.out.println(Thread.currentThread().getName()+ "minus :"+i);
	}
}

class AddThread implements Runnable
{
	
	private Data data;
	
	public AddThread(Data data) {
		super();
		this.data = data;
	}

	public void run() {
		// TODO Auto-generated method stub
		data.add();
	}
	
}

class MinusThread implements Runnable
{
	
	private Data data;
	
	public MinusThread(Data data) {
		super();
		this.data = data;
	}

	public void run() {
		// TODO Auto-generated method stub
		data.minus();
	}
	
}
*/