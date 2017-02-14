package com.sgcc.framework.base.UserInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgcc.framework.base.loginrecord.po.LoginRecord;
import com.sgcc.isc.core.orm.identity.Department;
import com.sgcc.isc.core.orm.identity.User;
import com.sgcc.isc.core.orm.role.OrganizationalRole;
import com.sgcc.isc.service.adapter.factory.AdapterFactory;
import com.sgcc.isc.service.adapter.helper.IIdentityService;
import com.sgcc.uap.integrate.isc.wrapper.factory.AdapterWrapperFactory;
import com.sgcc.uap.persistence.IBaseDao;
import com.sgcc.uap.utils.context.ClientContextHolder;

@Controller
@RequestMapping("/userinfo")
public class UserInfoController {
	
	@Autowired
	private IBaseDao baseDao;
	
	/**
	 * 
	 * @param myip
	 * @return
	 */
	@RequestMapping("/getinfo")
	public @ResponseBody
	String[] isPerm(@RequestParam("ip") String myip) {
		
		String[] strList = new String[]{"","","",""};
		IIdentityService orgService = AdapterFactory.getIdentityService();
		
		LoginRecord lr = new LoginRecord();

		// 获取用户ID
		String userid = ClientContextHolder.getInstance().getClientContext()
				.getUserID();
		strList[0] = userid;
		lr.setUserId(userid);
		// 获取用户名
		String userName = ClientContextHolder.getInstance().getClientContext().getUserName();
		strList[1] = userName;
		// 获取用户
		List<User> userList = null;
		try {
			userList = orgService.getUserByIds(new String[] { userid });
			if(userList.size()>0){
				User user = userList.get(0);
				Department org = AdapterFactory.getIdentityService().getDepartmentById(user.getBaseOrgId());// 根据基准组织ID查询基准组织所在的单位
				String baseOrgId = user.getBaseOrgId();
				String m = "{\"le\":{\"value\":\"id\",\"type\":\"1\"}, \"optr\":\"=\", \"re\":{\"value\":\""+baseOrgId+"\",\"type\":\"1\"},\"type\":\"0\"}";
				List<Department> listDepts = AdapterFactory.getIdentityService().getQuoteDepartmentsByConditionAndOrderBy(m,null);
				if(listDepts.size() > 0){
					Department dept = listDepts.get(0);
					strList[2] = dept.getName();
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// 获取系统ID
		String sysId = com.sgcc.uap.config.util.PlatformConfigUtil.getString("ISC_APPID");
		List<OrganizationalRole> orgRoleList;
		
		try {
			orgRoleList = AdapterWrapperFactory.getRoleService().getOrgRolesByUserId(userid, sysId, null);
			
			if(orgRoleList.size()>0){
				OrganizationalRole or = orgRoleList.get(0);
				strList[3] = or.getName();
				lr.setRoleName(or.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		lr.setIp(myip);
		addRecord(lr);
		return strList;
	}
	
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
	
}
