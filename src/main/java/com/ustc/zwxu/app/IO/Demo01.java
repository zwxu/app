package com.ustc.zwxu.app.IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;

public class Demo01 {

	public static void main(String[] args) {
		String content = "No.0";
		System.out.println(content.substring(3));
	/*	try
		{
			Writer writer = new FileWriter("demo01.txt");
	        BufferedWriter buffer = new BufferedWriter(writer);  


	        
	        for(int i=0;i<30;i++)
	        {
	        	buffer.write("No:"+i);
	        	buffer.newLine();
	        	//空缓冲区的数据流，进行流的操作时，数据先被读到内存中，然后再用数据写到文件中,以保证缓冲区数据全部写入文件
	        	buffer.flush(); 
	        }
	        	

	        
	        buffer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
		
	}

}
