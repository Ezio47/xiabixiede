package com.sgcc.framework.base.menuTreeByRole.bizc;

import java.io.Serializable;
import java.util.List;

import com.sgcc.framework.base.menuTreeByRole.po.TreeMenu;
import com.sgcc.isc.core.orm.resource.Function;
import com.sgcc.isc.core.orm.role.OrganizationalRole;
import com.sgcc.isc.core.orm.role.Role;
import com.sgcc.isc.core.orm.role.RoleGroup;
import com.sgcc.uap.mdd.runtime.base.IBizC;
import com.sgcc.uap.rest.support.QueryResultObject;
import com.sgcc.uap.rest.support.RequestCondition;


public interface ITreeMenuBizc extends IBizC<TreeMenu,Serializable>{

	public TreeMenu add(TreeMenu be);
	
	public void delete(Serializable id);
	
	public QueryResultObject query(RequestCondition queryCondition);
	
	public TreeMenu get(Serializable id);
	
	public void update(TreeMenu TreeMenu,Serializable pk);
	
	/**
	 * 查询登录用户业务角色根节点
	 * @return list
	 */
	public List<RoleGroup> getRoleRoot();
	
	/**
	 * 查询登录用户业务角色下级节点
	 * @return list
	 */
	public List<Role> getRoleNodes(String id);
	
	/**
	 * 查询当前节点下面是否含有子节点
	 * @param funcCode
	 * @return list
	 */
	public boolean hasChildRole(String id);
	/**
	 * 根据业务角色ID 获取菜单集合
	 * @return list
	 */
	public List<TreeMenu> getMenuRoot(String id);
	
	/**
	 * 组织角色下级节点
	 * @param id
	 * @param rid
	 * @return list
	 */
	public List<TreeMenu> getMenuNodes(String id,String rid);
	
	/**
	 * 查询当前菜单节点下面是否含有子节点
	 * @return list
	 */
	public boolean hasChildMenu(String id,String rid);
	
	/**
	 * 根据菜单id获取菜单描述
	 * @param id
	 * @return
	 */
	public String  getMenuNameById(String id);
	
	/**
	 * 根据业务角色ID 获取菜单集合（ISC）
	 * @return list
	 */
	public List<Function> getiMenuRoot();
	/**
	 * 从ISC获取菜单下级节点
	 * @return list
	 */
	public List<Function> getiMenuNodes(String id);
	
	/**
	 * 查询当前节点下面是否含有子节点
	 * @param funcCode
	 * @return list
	 */
	
	
	public boolean hasChildiMenu(String id);

	
	
	/**
	 * 根据节点名称获取节点
	 * @param nodeName
	 * @return
	 */
	public List<Function> findNodeIdByName(String nodeName);
	/**
	 * 根据 菜单id集合和角色id删除节点
	 * @param MenuIds 菜单id集合 
	 * @param rid  角色id
	 */
	public void deleteNode(String MenuIds,String rid);
	
	/**
	 * 根据批量的菜单id获取批量的菜单集合
	 * @param fids
	 * @return
	 */
	public List <Function> funByFids(String [] fids);
	
	/**
	 * 根据菜单功能集合和角色id 添加节点
	 * @param list
	 * @param rid
	 */
	public void saveNode(List <Function> list ,String rid);
	
	/**
	 * 判断数据库是否有该数据，返回没有的数据
	 * @param fids
	 * @param rid
	 * @return  
	 */
	public String [] isInclude(String [] fids,String rid);
	/**
	 * 查询业务角色下的组织角色
	 * @param id
	 * @return
	 */
	public List<OrganizationalRole> getOrgRoleList(String id);
}
