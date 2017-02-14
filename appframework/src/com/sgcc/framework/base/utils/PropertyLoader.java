package com.sgcc.framework.base.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyLoader {

	private static Map<String, Properties> propMap = new HashMap<String, Properties>();
	public final static String workFlowPro = "workflowconfig.properties";
	public final static String projectFlag = "projectFlag.properties";

	private static void setProperties(String fileName) {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(fileName);
		try {
			Properties prop = new Properties();
			prop.load(inputStream);
			propMap.put(fileName, prop);
		} catch (IOException e) {
			System.out.println("---------加载配置文件异常----------------");
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getConfig(String fileName,String key){
		if(propMap.get(fileName) == null){
			setProperties(fileName);
		}
		Properties prop = propMap.get(fileName);
		if(prop == null){
			System.out.println("---------加载配置文件失败----------------");
			return "";
		}
		return prop.getProperty(key, "");
	}
	
	public static void main(String[] args) {
		System.out.println(PropertyLoader.getConfig(PropertyLoader.projectFlag,
				"tenantId"));
	}
}
