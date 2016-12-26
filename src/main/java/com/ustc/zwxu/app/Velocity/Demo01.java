package com.ustc.zwxu.app.Velocity;

import java.util.HashMap;
import java.util.Map;

public class Demo01 {
	private static String succDescTemplate = "您于${date}提交的${fundName}基金申购申请已确认成功，购买金额为${orderAmount}元，成交净值是${netValue}元。";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Map<String,Object> paraMap = new HashMap<String,Object>();
		
		paraMap.put("date", "2015年11月23日");
		paraMap.put("fundName", "易方达");
		paraMap.put("orderAmount", "100.01");
		paraMap.put("netValue", "3.2300");
		
		try {
			/*String value = VelocityUtil.getString(succValueTemplate, paraMap);
			System.out.println(value);
			if(value.indexOf("$") > 0){
				System.out.println("表达式[" + succValueTemplate + "]执行异常");
			}*/
			String desc = VelocityUtil.getString(succDescTemplate, paraMap);
			System.out.println(desc);
			
			
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
