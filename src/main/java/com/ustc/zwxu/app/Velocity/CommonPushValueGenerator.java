package com.ustc.zwxu.app.Velocity;

import java.util.HashMap;
import java.util.Map;

public class CommonPushValueGenerator {
	private String title;
	private String htmlTemplate;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHtmlTemplate() {
		return htmlTemplate;
	}
	public void setHtmlTemplate(String htmlTemplate) {
		this.htmlTemplate = htmlTemplate;
	}
	
protected Map<String, Object>  generateMap(){
		
		Map<String,Object> paraMap = new HashMap<String,Object>();
		
		paraMap.put("bankName", "招商银行");
		paraMap.put("cardNo", 473);
		paraMap.put("submitTime", 1111);
		paraMap.put("productName", "test");
		paraMap.put("orderAmount", 10000);
		paraMap.put("sendTime", 22222);
		
		
		
		return paraMap;
	}
	
	protected String genValue(String template, Map<String, Object> paramMap) {
		try {
			String content = VelocityUtil.getString(template, paramMap);
			if (content.indexOf("$") > 0) {
				throw new ValidationException("模板[" + template + "]执行异常");
			}
			return content;
		} catch (ValidationException e) {

			throw new RuntimeException("模板[" + template + "]执行异常", e);
		}
	}
	

}
