package com.sgcc.framework.base.menuTreeByRole.util;

import java.util.List;

import com.sgcc.framework.base.menuTreeByRole.bizc.TreeMenuBizc;
import com.sgcc.isc.core.orm.resource.Function;

public class MenuTreeUtils {
 
	public static String  stringUtils(String s){
		String [] ss = null;
		ss=s.split(":",2);//{"params":"{\"itemType\":\"8ad5997642942db501429433c5320008\"}"}
		String s1=ss[1];//"{\"itemType\":\"8ad5997642942db501429433c5320008\"}"}
		ss=s1.split(":");
		s=ss[1];//\"8ad5997642942db501429433c5320008\"}"}
		ss=s.split("\"");
		s1=ss[1];//8ad5997642942db501429433c5320008\
		byte[] b= s1.getBytes();
		byte[] b2= new byte[b.length-1];
		for (int i = 0; i < b.length-1; i++) {
			b2[i]=b[i];
		}
		String s3= new String(b2);
		
		return s3; 
	}
}
