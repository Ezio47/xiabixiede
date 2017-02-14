package com.sgcc.sbInfo;

public class SbInfo {
	//所属电站
	private String ssdz;
	
	//电压等级
	private String dydj;
	
	
	//类别名称
	private String lbmc;
	
	//设备名称
	private String sbmc;
	
	//设备id
	private String sbid;
	
	
	//间隔名称
	private String jgmc;
	
	public SbInfo(String ssdz, String dydj, String lbmc, String sbmc,
			String sbid, String jgmc) {
		super();
		this.ssdz = ssdz;
		this.dydj = dydj;
		this.lbmc = lbmc;
		this.sbmc = sbmc;
		this.sbid = sbid;
		this.jgmc = jgmc;
	}
	public SbInfo() {
		super();
	}
	@Override
	public String toString() {
		return "SbInfo [ssdz=" + ssdz + ", dydj=" + dydj + ", lbmc=" + lbmc
				+ ", sbmc=" + sbmc + ", sbid=" + sbid + ", jgmc=" + jgmc + "]";
	}
	public String getSsdz() {
		return ssdz;
	}
	public void setSsdz(String ssdz) {
		this.ssdz = ssdz;
	}
	public String getDydj() {
		return dydj;
	}
	public void setDydj(String dydj) {
		this.dydj = dydj;
	}
	public String getLbmc() {
		return lbmc;
	}
	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}
	public String getSbmc() {
		return sbmc;
	}
	public void setSbmc(String sbmc) {
		this.sbmc = sbmc;
	}
	public String getSbid() {
		return sbid;
	}
	public void setSbid(String sbid) {
		this.sbid = sbid;
	}
	public String getJgmc() {
		return jgmc;
	}
	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}

}
