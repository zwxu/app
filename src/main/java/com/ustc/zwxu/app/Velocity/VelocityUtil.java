package com.ustc.zwxu.app.Velocity;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;


/**
 * <p>velocity工具类</p>
 * @author
 * @version
 */
public class VelocityUtil {
    /** 引擎对象 */
    private static VelocityEngine ve = null;

    /**
     * 获取引擎
     * @return
     */
    private static VelocityEngine loadEngine() {
        if (ve != null) {
            return ve;
        }
        synchronized (VelocityUtil.class) {
            if (ve != null) {
                return ve;
            }

            // 初始化
            VelocityEngine tempVe = new VelocityEngine();
            try {
                tempVe.init();
            } catch (Exception e) {
                e.printStackTrace();
            }

            ve = tempVe;
        }

        return ve;
    }

    /**
     * 获取字数串
     * @param templateContent
     * @param paramMap
     * @return
     */
    public static String getString(String templateContent, Map<String, Object> paramMap)
                                                                                        throws ValidationException {
        if (StringUtils.isEmpty(templateContent)) {
            return null;
        }

        VelocityContext context = new VelocityContext();
        if (paramMap != null && paramMap.size() > 0) {
            for (Entry<String, Object> entry : paramMap.entrySet()) {
                context.put(entry.getKey(), entry.getValue());
            }
        }

        StringWriter writer = new StringWriter();
        try {
            loadEngine().evaluate(context, writer, "velocity", templateContent);

            return writer.toString();
        } catch (Exception e) {
            throw new ValidationException("表达式[" + templateContent + "]不符合规范", e);
        }
    }

    /**
     * 合并字符串
     * 如 "111" + "222" = "111222"
     * @param templateContent
     * @param paramMap
     * @return
     */
    public static String mergeString(String templateContent, Map<String, Object> paramMap)
                                                                                          throws ValidationException {
        return getString(wrapExecuteContent(templateContent), paramMap);
    }

    /**
     * 执行字符串
     * @param templateContent
     * @param paramMap
     * @return
     */
    public static String executeString(String templateContent, Map<String, Object> paramMap)
                                                                                            throws ValidationException {
        if (StringUtils.contains(templateContent, "#if")) {
            return getString(templateContent, paramMap);
        } else {
            return mergeString(templateContent, paramMap);
        }
    }

    /**
     * 判断是否为真
     * @param templateContent
     * @param paramMap
     * @return
     */
    public static boolean isTrue(String templateContent, Map<String, Object> paramMap)
                                                                                      throws ValidationException {
        return Boolean.valueOf(StringUtils.trim(executeString(templateContent, paramMap)));
    }

    /**
     * 获取金额
     * @param templateContent
     * @param paramMap
     * @return
     */
    public static Money getAmount(String templateContent, Map<String, Money> paramMap)
                                                                                      throws ValidationException {
        Money emptyAmount = new Money(0.0);
        if (StringUtils.isEmpty(templateContent) || paramMap == null) {
            return emptyAmount;
        }
        Map<String, Object> amountMap = new HashMap<String, Object>();
        for (Entry<String, Money> entry : paramMap.entrySet()) {
            amountMap.put(entry.getKey(), entry.getValue().getAmount());
        }

        String resultString = executeString(templateContent, amountMap);
        if(StringUtils.isEmpty(resultString)) {
            return emptyAmount;
        }

        return new Money(StringUtils.trim(resultString));
    }

    /**
     * 包装变量
     * @param orgiValue
     * @return
     */
    public static String warpVariable(String orgiValue) {
        return "${" + orgiValue + "}";
    }

    /**
     * 封装执行语句
     * @param orgiContent
     * @return
     */
    private static String wrapExecuteContent(String orgiContent) {
        return "#set($temp=" + orgiContent + ")$temp";
    }

    public static void main(String[] args) {
        try {
            String test = "#if(${test1}>2) true #else false #end";
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("test1", 3);
            System.out.println(executeString(test, paramMap));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
