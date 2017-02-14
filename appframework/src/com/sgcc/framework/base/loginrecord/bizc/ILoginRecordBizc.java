package com.sgcc.framework.base.loginrecord.bizc;

import java.io.Serializable;

import com.sgcc.framework.base.loginrecord.po.LoginRecord;
import com.sgcc.uap.mdd.runtime.base.IBizC;

public interface ILoginRecordBizc extends IBizC<LoginRecord,Serializable> {
	
	public boolean addRecord(LoginRecord lr);

}
