package com.sgcc.framework.base.loginrecord.po;

import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.NotBlank;

public class LoginRecord  implements java.io.Serializable {
	
	private String id;

	@NotBlank(message = "不能为空")
	private String userId;
	
	@NotBlank(message = "不能为空")
	private String loginDate;
	
	@NotBlank(message = "不能为空")
	private String roleName;
	
	@NotBlank(message = "不能为空")
	private String ip;
	
	/** 虚拟主键 */
	private String mxVirtualId;
	
	/** 无参构造方法 */
	public LoginRecord(){
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getMxVirtualId() {
		return mxVirtualId;
	}

	public void setMxVirtualId(String mxVirtualId) {
		this.mxVirtualId = mxVirtualId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "LoginRecord [id=" + id + ", userId=" + userId + ", loginDate="
				+ loginDate + ", roleName=" + roleName + ", ip=" + ip
				+ ", mxVirtualId=" + mxVirtualId + "]";
	}

}
