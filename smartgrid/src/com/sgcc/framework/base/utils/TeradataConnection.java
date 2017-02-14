//package com.sgcc.framework.base.utils;
//
//import org.apache.commons.dbcp.BasicDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.sgcc.uap.integrate.isc.utils.AESUtils;
//
//public class TeradataConnection  extends BasicDataSource {
//	
//	String keyseed = com.sgcc.uap.config.util.PlatformConfigUtil
//			.getString("ISC_KEYSEED");
//
//	public TeradataConnection() {
//        // TODO Auto-generated constructor stub
//        super();
//    }
//    
//	@Autowired
//	public void setPassword(String password){
//        try{
//            this.setPassword(AESUtils.decrypt(password,keyseed));
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//}
