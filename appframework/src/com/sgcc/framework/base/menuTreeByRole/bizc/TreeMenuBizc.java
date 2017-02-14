package com.sgcc.framework.base.menuTreeByRole.bizc;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import com.sgcc.framework.base.menuTreeByRole.po.TreeMenu;
import com.sgcc.framework.base.utils.PropertyLoader;
import com.sgcc.isc.core.orm.resource.Function;
import com.sgcc.isc.core.orm.role.OrganizationalRole;
import com.sgcc.isc.core.orm.role.Role;
import com.sgcc.isc.core.orm.role.RoleGroup;
import com.sgcc.uap.integrate.isc.wrapper.factory.AdapterWrapperFactory;
import com.sgcc.uap.mdd.runtime.base.BizCDefaultImpl;
import com.sgcc.uap.persistence.IHibernateDao;
public class TreeMenuBizc extends BizCDefaultImpl<TreeMenu, Serializable>
		implements ITreeMenuBizc {
	@Autowired
	private IHibernateDao hibernateDao;
    
	public void setHibernateDao(IHibernateDao hibernateDao){
		this.hibernateDao=hibernateDao; 
	}

	/**************** 标准方法执行前后事件,默认全部返回true *******************/
	@Override
	protected void afterDelete(TreeMenu TreeMenu) {
		// 自定义逻辑

	}

	@Override
	protected void afterAdd(TreeMenu TreeMenu) {
		// 自定义逻辑
	}

	@Override
	protected boolean beforeDelete(TreeMenu TreeMenu) {
		// 自定义逻辑

		return true;
	}

	@Override
	protected boolean beforeAdd(TreeMenu TreeMenu) {
		// 自定义逻辑
		return true;
	}

	@Override
	protected void afterUpdate(TreeMenu TreeMenu, Serializable pk) {
		// 自定义逻辑
	}

	@Override
	protected boolean beforeUpdate(TreeMenu TreeMenu, Serializable pk) {
		// 自定义逻辑
		return true;
	}
    

	
	/**
	 * 查询登录用户组织根节点
	 * 
	 * @return list
	 */
	public List<RoleGroup> getRoleRoot() {
		// 获取接入易用化的系统ID
		String yiYouId = com.sgcc.uap.config.util.PlatformConfigUtil
				.getString("ISC_APPID");
		PropertyLoader pl = new PropertyLoader();
		String roleSysIds = pl.getConfig("bussconfig.properties", "ROLESYSTEMIDS");
		String orgRoleIds = pl.getConfig("bussconfig.properties", "ORGROLESYSTEMIDS");
		String allRoleIds = pl.getConfig("bussconfig.properties", "ALLSYSTEMIDS");
		if(orgRoleIds!=null && !orgRoleIds.equals(""))
			roleSysIds += "," + orgRoleIds;
		if(allRoleIds!=null && !allRoleIds.equals(""))
			roleSysIds += "," + allRoleIds;
		roleSysIds +=","+yiYouId;  //添加易用化ID
		String[] osids = roleSysIds.split(",");
		List<RoleGroup> rgList = new ArrayList<RoleGroup>();
		for(String osid : osids){
			try {
				//System.out.println(AdapterWrapperFactory.getRoleService().getRoleGroup(osid, null));
				rgList.addAll(AdapterWrapperFactory.getRoleService().getRoleGroup(osid, null));
			} catch (Exception e) {
				System.err.println("getUserRGRoot error!");
				e.printStackTrace();
			}
		}
		return rgList;
	}
	
	/**
	 * 查询登录用户组织下级节点
	 * 
	 * @return list
	 */
	public List<Role> getRoleNodes(String id) {
		List<Role> rgList = new ArrayList<Role>();
		try {
			rgList = AdapterWrapperFactory.getRoleService().getRolesByRoleGroupID(id,null,null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rgList;
	}
	
	/**
	 * 判断是否有子节点
	 */
	public boolean hasChildRole(String id){
		List<Role> rgList = new ArrayList<Role>();
		try {
			//System.out.println(id);
			rgList = AdapterWrapperFactory.getRoleService().getRolesByRoleGroupID(id,null,null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rgList.size() == 0)
			return false;
		return true;
	}

	public List<TreeMenu> getMenuRoot(String roleid) {

		List<TreeMenu> list =new ArrayList<TreeMenu>();
		List listMap=new ArrayList();
		String  sql ="select * from T_EU_ROLE_MENU_RELATION where ROLEID= '"+roleid+"' and MENUPID = '-1'";
		
		listMap=hibernateDao.queryForListWithSql(sql);
		for (int i = 0; i < listMap.size(); i++) {
			TreeMenu tm =new TreeMenu(); 
			Map map = new HashMap();
			map =(Map) listMap.get(i);
			tm.setId((String)map.get("ID"));
			tm.setSysid((String)map.get("SYSID"));
			tm.setRoleid((String)map.get("ROLEID"));
			tm.setMenuid((String)map.get("MENUID"));
			tm.setMenupid((String)map.get("MENUPID"));
			tm.setNote1((String)map.get("NOTE1"));
			tm.setNote2((String)map.get("NOTE2"));
			list.add(tm);
		}
		
		return list;
	}

	public boolean hasChildMenu(String menuid,String rid) {
		List<TreeMenu> list =new ArrayList<TreeMenu>();
		List listMap=new ArrayList();
		String  sql ="select * from T_EU_ROLE_MENU_RELATION where MENUPID= '"+menuid+"' and ROLEID= '"+rid+"'";
		
		listMap=hibernateDao.queryForListWithSql(sql);
		for (int i = 0; i < listMap.size(); i++) {
			TreeMenu tm =new TreeMenu(); 
			Map map = new HashMap();
			map =(Map) listMap.get(i);
			tm.setId((String)map.get("ID"));
			tm.setSysid((String)map.get("SYSID"));
			tm.setRoleid((String)map.get("ROLEID"));
			tm.setMenuid((String)map.get("MENUID"));
			tm.setMenupid((String)map.get("MENUPID"));
			tm.setNote1((String)map.get("NOTE1"));
			tm.setNote2((String)map.get("NOTE2"));
			list.add(tm);
		}
		if(list.size()>0){
			return true;
		}
		return false;
	}

	public List<TreeMenu> getMenuNodes(String menuid,String rid) {
		
		List<TreeMenu> list =new ArrayList<TreeMenu>();
		List listMap=new ArrayList();
		String  sql ="select * from T_EU_ROLE_MENU_RELATION where MENUPID= '"+menuid+"' and ROLEID ='"+rid+"'";
		//System.out.println("select * from T_EU_ROLE_MENU_RELATION where MENUPID= '"+menuid+"'and ROLEID ='"+rid+"——————————执行！！！！");
		listMap=hibernateDao.queryForListWithSql(sql);
		for (int i = 0; i < listMap.size(); i++) {
			TreeMenu tm =new TreeMenu(); 
			Map map = new HashMap();
			map =(Map) listMap.get(i);
			tm.setId((String)map.get("ID"));
			tm.setSysid((String)map.get("SYSID"));
			tm.setRoleid((String)map.get("ROLEID"));
			tm.setMenuid((String)map.get("MENUID"));
			tm.setMenupid((String)map.get("MENUPID"));
			tm.setNote1((String)map.get("NOTE1"));
			tm.setNote2((String)map.get("NOTE2"));
			list.add(tm);
		}
		
		return list;
		
	}

	public String getMenuNameById(String id) {
		String[] menuids ={id};
		List<Function> list=  new ArrayList<Function>();
		
		try {
			List<Function> funclist = AdapterWrapperFactory
					.getResourceService().getFuncsByIds(menuids);
			
			if(funclist.size()!=0){
				list.addAll(funclist);	
			}
			//System.out.println(list.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list.size()==0){
			return "菜单描述获取失败";	
		}else if(list.size()==1){
			String s="";
			for(Function fun: list){
				s=fun.getName();
			}
			return s;	
		}else{
			return "菜单描述数量唯一";
		}
		
	}
	/**
	 * 从ISC中获取菜单根节点
	 */
	public List<Function> getiMenuRoot() {
		// 获取UAP配置的系统ID(易用化主系统)
		String sysId = com.sgcc.uap.config.util.PlatformConfigUtil
				.getString("ISC_APPID");
		 List<Function> list =new ArrayList<Function>();
		 try {
			list=AdapterWrapperFactory.getResourceService().getFirstLayerFuncs(sysId,null,null,null);
		
		 } catch (Exception e) {
			System.err.println();
			e.printStackTrace();
		}
	 return list;
	}
    /**
     * 菜单下级节点
     */
	public List<Function> getiMenuNodes(String id) {
		List<Function> list =new ArrayList<Function>();
		try {
			list=AdapterWrapperFactory.getResourceService().getFuncsByParentId(id, null, null);
		} catch (Exception e) {
			//System.out.println("在统一权限中，获取下级菜单节点错误！！！");
			e.printStackTrace();
		}
		return list;
	}
	/**
     * 判断菜单树种节点下面是否有子节点
     */
	public boolean hasChildiMenu(String id) {
		List<Function> list =new ArrayList<Function>();
		try {
			list=AdapterWrapperFactory.getResourceService().getFuncsByParentId(id, null, null);
		} catch (Exception e) {
			//System.out.println("判断菜单树种节点下面是否有子节点错误，（统一权限中）！！！");
			e.printStackTrace();
		}
		if(list.size()==0)
		return false;
		return true;
	}

	/**
	 * 根据节点名称获取节点
	 */
	public List<Function> findNodeIdByName(String nodeName) {
		List<Function> list =new ArrayList<Function>();
		// 获取UAP配置的系统ID(易用化主系统)
				String sysId = com.sgcc.uap.config.util.PlatformConfigUtil
						.getString("ISC_APPID");
		// 获取接入易用化的系统ID
		PropertyLoader pl = new PropertyLoader();		
		String roleSysIds = pl.getConfig("bussconfig.properties", "ROLESYSTEMIDS");
		String orgRoleIds = pl.getConfig("bussconfig.properties", "ORGROLESYSTEMIDS");
		String allRoleIds = pl.getConfig("bussconfig.properties", "ALLSYSTEMIDS");
		if(orgRoleIds!=null && !orgRoleIds.equals(""))
			roleSysIds += "," + orgRoleIds;
		if(allRoleIds!=null && !allRoleIds.equals(""))
			roleSysIds += "," + allRoleIds;
		roleSysIds +=","+sysId;  //添加易用化ID

		try {
			list=AdapterWrapperFactory.getResourceService(). getFuncsByFuncName(sysId,nodeName,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (roleSysIds != null && !roleSysIds.equals("")) {
			List<Function> listsys =new ArrayList<Function>();
			String[] sysIds = roleSysIds.split(",");
			for (int i = 0; i < sysIds.length; i++) {
				try {
					listsys=AdapterWrapperFactory.getResourceService(). getFuncsByFuncName(sysIds[i],nodeName,null);
					list.addAll(listsys);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	/**
	 * 删除节点
	 */
	public void deleteNode(String MenuIds, String rid) {
		// TODO Auto-generated method stub
		//操作数据库
		String [] menuIdArr=MenuIds.split(",");
		String sql ="delete from t_eu_role_menu_relation where ";
		for (int i = 0; i < menuIdArr.length; i++) {
			 String menuID=menuIdArr[i];
			if(!("".equals(rid)&&rid==null)){
				//sql="delete from t_eu_role_menu_relation where MENUID ="+menuID+" and roleid ="+rid;
				hibernateDao.update("delete from " + TreeMenu.class.getName() + " where menuid = ?"+" and roleid =?",new Object[]{menuID,rid});
			}
		}
		
	}
	/**
	 * 根据批量的菜单id获取批量的菜单集合
	 */
	public List<Function> funByFids(String[] fids) {
		// TODO Auto-generated method stub
		List<Function> list =new ArrayList<Function>();
		try {
			list=AdapterWrapperFactory.getResourceService().getFuncsByIds(fids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println("根据批量的菜单id获取批量的菜单集合失败！！！！！");
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 添加节点
	 */
	public void saveNode(List <Function> list ,String rid){
		String sysID ="";
		List listR= new ArrayList();
		List<Role> listRo= new ArrayList<Role>();
		List<RoleGroup> listRg= new ArrayList<RoleGroup>();
		try {
			listR=AdapterWrapperFactory.getRoleService().getRoleByRoleId(rid);
			if(listR.size()>0){
				listRo=AdapterWrapperFactory.getRoleService().getRoleByRoleId(rid);
			    for(Role r:listRo){
			    	String roleGroupId= r.getRoleGroupId();//获取分组Id
			    	listRg=AdapterWrapperFactory.getRoleService().getRoleGroupByRoleGroupId(roleGroupId);
			    	for(RoleGroup rp:listRg){
			    		sysID=rp.getSystemId();
			    	}
			    }
			}else{
				listRg=AdapterWrapperFactory.getRoleService().getRoleGroupByRoleGroupId(rid);
		    	for(RoleGroup rp:listRg){
		    		sysID=rp.getSystemId();
		    	}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			Function fun=list.get(i);
			TreeMenu tm =new TreeMenu();
			tm.setMenuid(fun.getId());//菜单Id
			if("".equals(fun.getFuncId())){
				tm.setMenupid("-1");	
			}else if("-1".equals(fun.getFuncId())){
				tm.setMenupid("-1");
			}else if(fun.getFuncId()==null){
				tm.setMenupid("-1");
			}else{
				if(fun.getFuncId().equals(fun.getSystemId())){
					//如果父节点为系统 ，父Id默认为"-1"
					tm.setMenupid("-1");
				}else{
					tm.setMenupid(fun.getFuncId());//菜单父id
				}
			}
			//System.out.println("系统ID----------------------------------"+sysID);
			if(sysID == null || sysID.equals("")){
				String[] ids = {rid};
				try {
					List<OrganizationalRole> orgRoleList = AdapterWrapperFactory.getRoleService().getOrgRolesByIds(ids);
					if(!orgRoleList.isEmpty()){
						sysID = orgRoleList.get(0).getSystemId();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			tm.setSysid(sysID);//系统id
			tm.setRoleid(rid);//角色id
			//System.out.println(tm.toString()+"--------保存成功");
			hibernateDao.saveOrUpdateObject(tm);
		}
	}
	/**
	 * 判断是否又该数据
	 */
	public String[] isInclude(String[] fids, String rid) {
		String jiLu="";
		StringBuilder sb= new StringBuilder("");
		for(int i=0;i< fids.length;i++){
			List listMap=new ArrayList();
			String  sql ="select * from T_EU_ROLE_MENU_RELATION where ROLEID= '"+rid+"' and MENUID = '"+fids[i]+"'";
			listMap=hibernateDao.queryForListWithSql(sql);
			if(listMap.size()==0){
				sb.append(fids[i]+",");
			}else{
				//System.out.println("该菜单Id为【"+fids[i]+"】已数据库存在，不能再次放到数据里！！！");	
			}
		}	
		jiLu= sb.toString();
		if(!("".equals(jiLu))){
			//System.out.println("数据库不存在的菜单Id---------------------"+jiLu);
			String s1 [] =jiLu.split(",");
			return s1; 
		}
			return null;
	}

	public List<OrganizationalRole> getOrgRoleList(String id) {
		// TODO Auto-generated method stub
		try {
			return AdapterWrapperFactory.getRoleService().getOrgRolesByRoleId(id, null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
