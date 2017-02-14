package com.sgcc.framework.base.loginrecord.bizc;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sgcc.framework.base.loginrecord.po.LoginRecord;
import com.sgcc.uap.mdd.runtime.base.BizCDefaultImpl;
import com.sgcc.uap.persistence.IBaseDao;

public class LoginRecordBizc extends BizCDefaultImpl<LoginRecord, Serializable>
		implements ILoginRecordBizc {

	@Autowired
	private IBaseDao baseDao;

	public boolean addRecord(LoginRecord lr) {
		// 获取当前时间
		Date date = new Date();
		// 当前日期
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
		// 当前时间，精确到秒
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");

		lr.setLoginDate(sdfDate.format(date) + " " + sdfTime.format(date));

		String[] params = { lr.getUserId() };

		List<Object[]> list = baseDao.findAll(
				"from LoginRecord where userId=?", params);
		
		if (list.size() > 0) {
			baseDao.removeObject(list.get(0));
		}
		baseDao.saveObject(lr);
		return true;
	}

	@Override
	protected void afterAdd(LoginRecord arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void afterDelete(LoginRecord arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void afterUpdate(LoginRecord arg0, Serializable arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean beforeAdd(LoginRecord arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean beforeDelete(LoginRecord arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean beforeUpdate(LoginRecord arg0, Serializable arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
