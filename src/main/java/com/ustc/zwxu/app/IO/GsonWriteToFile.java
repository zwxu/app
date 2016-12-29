package com.ustc.zwxu.app.IO;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class GsonWriteToFile {
	
	public static void main(String[] args) {
		
		//BufferedReader reader=new BufferedReader(new InputStreamReader(GsonTest.class.getResourceAsStream("/config/json.txt")));
		try {
			//常用获取文件路径方式
			String path = GsonWriteToFile.class.getClassLoader().getResource("config/json.txt").getPath();
			FileInputStream f = new FileInputStream(path);
			BufferedReader reader=new BufferedReader(new InputStreamReader(f));
			long start = System.currentTimeMillis();
	        String content = "";
	        Gson gson = new GsonBuilder().create();
	        try {
				while ((content = reader.readLine()) != null) {
				
					FcmInfo behaviour = gson.fromJson(content, FcmInfo.class);
					System.out.println(behaviour.getApp());
					
			        System.out.println((System.currentTimeMillis() - start) + "ms");
				}
				reader.close();
			} catch (JsonSyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
       
	}

}

class FcmInfo
{
	private String versionNo;
	private String sysProCode;
	private String fundsChannel;
	private String productId;
	private String productType;
	
	private String app;
	private List<EventInfo> events;
	private ClientInfo clientData;
	
	
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public List<EventInfo> getEvents() {
		return events;
	}
	public void setEvents(List<EventInfo> events) {
		this.events = events;
	}
	public ClientInfo getClientData() {
		return clientData;
	}
	public void setClientData(ClientInfo clientData) {
		this.clientData = clientData;
	}
	public String getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
	public String getSysProCode() {
		return sysProCode;
	}
	public void setSysProCode(String sysProCode) {
		this.sysProCode = sysProCode;
	}
	public String getFundsChannel() {
		return fundsChannel;
	}
	public void setFundsChannel(String fundsChannel) {
		this.fundsChannel = fundsChannel;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
}

class EventInfo
{
	private String time;
	private String target;
	private String type;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
class ClientInfo
{
	private String fid;
	private String platform;
	private String os_ver;
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getOs_ver() {
		return os_ver;
	}
	public void setOs_ver(String os_ver) {
		this.os_ver = os_ver;
	}
	
	
}