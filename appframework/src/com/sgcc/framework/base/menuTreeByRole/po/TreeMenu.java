package com.sgcc.framework.base.menuTreeByRole.po;

import org.hibernate.validator.constraints.NotBlank;

/**
 * RoleMenu
 * @author Kenshin
 * @date 2014-05-12
 */
public class TreeMenu implements java.io.Serializable {

	/** id*/
	
	private String id ;
	/** sysid*/
	@NotBlank(message="不能为空") 
	private String sysid ;
	/** roleid*/
	@NotBlank(message="不能为空") 
	private String roleid ;
	/** menuid*/
	@NotBlank(message="不能为空") 
	private String menuid ;
	/** menupid*/
	@NotBlank(message="不能为空") 
	private String menupid ;
	/** note1*/
	
	private String note1 ;
	/** note2*/
	
	private String note2 ;
	
	/**虚拟主键*/
	private String mxVirtualId ;

    /** 无参构造方法 */
    public TreeMenu() {
    }
	
	/** 构造方法 */
    public TreeMenu(String sysid, String roleid, String menuid, String menupid) {
        this.sysid = sysid;
        this.roleid = roleid;
        this.menuid = menuid;
        this.menupid = menupid;
    }
 	   
	
	
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
	
	
    public String getSysid() {
        return this.sysid;
    }
    
    public void setSysid(String sysid) {
        this.sysid = sysid;
    }
	
	
    public String getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }
	
	
    public String getMenuid() {
        return this.menuid;
    }
    
    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }
	
	
    public String getMenupid() {
        return this.menupid;
    }
    
    public void setMenupid(String menupid) {
        this.menupid = menupid;
    }
	
	
    public String getNote1() {
        return this.note1;
    }
    
    public void setNote1(String note1) {
        this.note1 = note1;
    }
	
	
    public String getNote2() {
        return this.note2;
    }
    
    public void setNote2(String note2) {
        this.note2 = note2;
    }
	
	
    public String getMxVirtualId() {
        return this.mxVirtualId;
    }
    
    public void setMxVirtualId(String mxVirtualId) {
        this.mxVirtualId = mxVirtualId;
    }
	

     public String toString() {
         StringBuffer buffer = new StringBuffer();

		 buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		 buffer.append("id").append("='").append(getId()).append("' ");			
		 buffer.append("sysid").append("='").append(getSysid()).append("' ");			
		 buffer.append("roleid").append("='").append(getRoleid()).append("' ");			
		 buffer.append("menuid").append("='").append(getMenuid()).append("' ");			
		 buffer.append("menupid").append("='").append(getMenupid()).append("' ");			
		 buffer.append("note1").append("='").append(getNote1()).append("' ");			
		 buffer.append("note2").append("='").append(getNote2()).append("' ");			
		 buffer.append("mxVirtualId").append("='").append(getMxVirtualId()).append("' ");			
		 buffer.append("]");
      
         return buffer.toString();
     }

	public boolean equals(Object other) {
        if ( (this == other ) ) return true;
		if ( (other == null ) ) return false;
		if ( !(other instanceof TreeMenu) ) return false;
		TreeMenu castOther = ( TreeMenu ) other; 
         
		return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getSysid()==castOther.getSysid()) || ( this.getSysid()!=null && castOther.getSysid()!=null && this.getSysid().equals(castOther.getSysid()) ) )
 && ( (this.getRoleid()==castOther.getRoleid()) || ( this.getRoleid()!=null && castOther.getRoleid()!=null && this.getRoleid().equals(castOther.getRoleid()) ) )
 && ( (this.getMenuid()==castOther.getMenuid()) || ( this.getMenuid()!=null && castOther.getMenuid()!=null && this.getMenuid().equals(castOther.getMenuid()) ) )
 && ( (this.getMenupid()==castOther.getMenupid()) || ( this.getMenupid()!=null && castOther.getMenupid()!=null && this.getMenupid().equals(castOther.getMenupid()) ) )
 && ( (this.getNote1()==castOther.getNote1()) || ( this.getNote1()!=null && castOther.getNote1()!=null && this.getNote1().equals(castOther.getNote1()) ) )
 && ( (this.getNote2()==castOther.getNote2()) || ( this.getNote2()!=null && castOther.getNote2()!=null && this.getNote2().equals(castOther.getNote2()) ) )
 && ( (this.getMxVirtualId()==castOther.getMxVirtualId()) || ( this.getMxVirtualId()!=null && castOther.getMxVirtualId()!=null && this.getMxVirtualId().equals(castOther.getMxVirtualId()) ) );
   }
   
   public int hashCode() {
       int result = 17;
         
		result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
		result = 37 * result + ( getSysid() == null ? 0 : this.getSysid().hashCode() );
		result = 37 * result + ( getRoleid() == null ? 0 : this.getRoleid().hashCode() );
		result = 37 * result + ( getMenuid() == null ? 0 : this.getMenuid().hashCode() );
		result = 37 * result + ( getMenupid() == null ? 0 : this.getMenupid().hashCode() );
		result = 37 * result + ( getNote1() == null ? 0 : this.getNote1().hashCode() );
		result = 37 * result + ( getNote2() == null ? 0 : this.getNote2().hashCode() );
		result = 37 * result + ( getMxVirtualId() == null ? 0 : this.getMxVirtualId().hashCode() );
		return result;
   }   

}
