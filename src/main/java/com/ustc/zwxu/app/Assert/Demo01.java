package com.ustc.zwxu.app.Assert;

import org.springframework.util.Assert;

import com.ustc.zwxu.app.Thread.Asset;

public class Demo01 {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		String str=null;

		try{
			Assert.notNull(str, "not null");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("system is ending");
	}

}
