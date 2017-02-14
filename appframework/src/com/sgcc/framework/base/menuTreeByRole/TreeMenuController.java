package com.sgcc.framework.base.menuTreeByRole;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import com.sgcc.framework.base.menuTreeByRole.po.TreeMenu;
import com.sgcc.framework.base.menuTreeByRole.util.MenuTreeUtils;
import com.sgcc.framework.base.menuTreeByRole.bizc.ITreeMenuBizc;
import com.sgcc.isc.core.orm.organization.BusinessOrganization;
import com.sgcc.isc.core.orm.resource.Function;
import com.sgcc.isc.core.orm.role.OrganizationalRole;
import com.sgcc.isc.core.orm.role.Role;
import com.sgcc.isc.core.orm.role.RoleGroup;
import com.sgcc.uap.bizc.sysbizc.datadictionary.IDataDictionaryBizC;
import com.sgcc.uap.mdd.runtime.meta.IMetadataService;
import com.sgcc.uap.mdd.runtime.utils.BodyReaderRequestWrapper;
import com.sgcc.uap.mdd.runtime.utils.HttpMessageConverter;
import com.sgcc.uap.mdd.runtime.validate.IValidateService;
import com.sgcc.uap.rest.annotation.ColumnRequestParam;
import com.sgcc.uap.rest.annotation.ColumnResponseBody;
import com.sgcc.uap.rest.annotation.IdRequestBody;
import com.sgcc.uap.rest.annotation.ItemRequestParam;
import com.sgcc.uap.rest.annotation.ItemResponseBody;
import com.sgcc.uap.rest.annotation.ItemsRequestBody;
import com.sgcc.uap.rest.annotation.QueryRequestParam;
import com.sgcc.uap.rest.annotation.TreeResponseBody;
import com.sgcc.uap.rest.annotation.VoidResponseBody;
import com.sgcc.uap.rest.annotation.attribute.ViewAttributeData;
import com.sgcc.uap.rest.support.IDRequestObject;
import com.sgcc.uap.rest.support.QueryResultObject;
import com.sgcc.uap.rest.support.RequestCondition;
import com.sgcc.uap.rest.support.TreeNode;


@Controller
@RequestMapping("/treeMenu")
public class TreeMenuController {

	
	private ITreeMenuBizc treemenuBizc;
	
	
	private IDataDictionaryBizC dataDictionaryBizC;
	
	
	private IMetadataService metadataService;
	
	private IValidateService validateService;
	@RequestMapping("/meta")
	public @ColumnResponseBody List<ViewAttributeData> getPropertyMeta(@ColumnRequestParam("params") String[] filterPropertys) throws Exception {
		List<ViewAttributeData> datas = null;
		datas = metadataService.getPropertyMeta(this.getClass(), "com.sgcc.framework.base.TreeMenu.po.TreeMenu", filterPropertys);
		return datas;
	}
	/**
	 * 注入逻辑构件
	 * @param treeBizc
	 */
	public void setTreeBizc(ITreeMenuBizc treemenuBizc) {
		this.treemenuBizc = treemenuBizc;
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ItemResponseBody List<TreeMenu> save(HttpServletRequest request){
		try {
			//获取servlet API
			ServletServerHttpRequest servlet = new BodyReaderRequestWrapper(request);
			//模型转换器
	        HttpMessageConverter coverter = new HttpMessageConverter();
	        //将模型转换为java对象
			TreeMenu[] TreeMenus = coverter.converter(new TreeMenu[0], servlet);
		    List<Map<String,Object>> changedProperies = coverter.converter(new ArrayList<Map<String,Object>>(), servlet);
	        List<TreeMenu> voList = new ArrayList<TreeMenu>();
	        //对所有属性进行后端校验
			validateService.validateWithException(TreeMenu.class, changedProperies);
			//遍历表单数据, 如果当前数据在数据库里存在, 则做修改, 否则做新增处理
			for (int i = 0; i < TreeMenus.length; i++) {
				TreeMenu TreeMenu= TreeMenus[i];
				Serializable pkValue = TreeMenu.getId();
				if (null != pkValue) {
	            	Map<String,Object> changedProperty = coverter.flatHandle(changedProperies.get(i));
	
					TreeMenu old = treemenuBizc.get(pkValue);

	 				BeanUtils.populate(old, changedProperty);
	 				
	 				treemenuBizc.update(old, pkValue);
					voList.add(TreeMenu);
	
				}else{
					treemenuBizc.add(TreeMenu);
					voList.add(TreeMenu);
				}
			}
			return voList;
		} catch (Exception e) {
			throw new RestClientException("保存方法异常", e);
		}
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @VoidResponseBody Object delete(@IdRequestBody IDRequestObject idObject){
		String[] ids = idObject.getIds();
		for (String id : ids) {
			treemenuBizc.delete(java.lang.String.valueOf(id));
		}
		return null;
	}

	@RequestMapping("/{id}")
    public @ItemResponseBody QueryResultObject get(@PathVariable String id) {
		TreeMenu TreeMenu ;
		if("null".equals(id)){
			TreeMenu = null;
		}else {
			TreeMenu = treemenuBizc.get(java.lang.String.valueOf(id));
		}
		QueryResultObject qObject = new QueryResultObject();
		List items = new ArrayList();
		items.add(TreeMenu);
		qObject.setItems(items);

    	return qObject;
    }


	@RequestMapping("/")
    public @ItemResponseBody QueryResultObject query(@QueryRequestParam("params") RequestCondition queryCondition){
	    QueryResultObject queryResult = treemenuBizc.query(queryCondition);

	    return queryResult;
    }
	
	/**
	 * 获取业务角色根节点
	 * 
	 * @return nodelist
	 */
	@RequestMapping(value = "/rgTree", method = RequestMethod.GET)
	public @TreeResponseBody
	List<TreeNode> getRoot() {
		//System.out.println("getRGRoot");
		List<TreeNode> nodelist = new ArrayList<TreeNode>();
		List<RoleGroup> list = treemenuBizc.getRoleRoot();
		for(int i = 0; i < list.size(); i++){
			TreeNode node = new TreeNode();
			RoleGroup rg = (RoleGroup)list.get(i);
			node.setId(rg.getId());
			node.setText(rg.getName() == null ? "" : rg.getName());
			node.setHasChildren(treemenuBizc.hasChildRole(rg.getId()));
			nodelist.add(node);
		}
		//System.out.println(nodelist);
		return nodelist;
	}
	
	/**
	 * 获取业务角色指定id的下级节点
	 * 
	 * @param id
	 * 
	 * @return nodelist
	 */
	@RequestMapping(value = "/rgTree/{id}", method = RequestMethod.GET)
	public @TreeResponseBody
	List<TreeNode> getNodes(@PathVariable String id,
			@ItemRequestParam("params") String itemType) {
		List<TreeNode> nodelist = new ArrayList<TreeNode>();
		List<Role> list = treemenuBizc.getRoleNodes(id);
		if(list.isEmpty()){// 如果为空说明是业务角色ID，查下级组织角色
			List<OrganizationalRole> orgRoleList = treemenuBizc.getOrgRoleList(id);
			for(OrganizationalRole orgRole : orgRoleList){
				TreeNode node = new TreeNode();
				node.setId(orgRole.getId());
				node.setText(orgRole.getName() == null ? "" : orgRole.getName());
				node.setHasChildren(false);
				nodelist.add(node);
			}
		}else{
			for(int i = 0; i < list.size(); i++){
				TreeNode node = new TreeNode();
				Role role = (Role)list.get(i);
				node.setId(role.getId());
				node.setText(role.getName() == null ? "" : role.getName());
				node.setHasChildren(true);
				nodelist.add(node);
			}
		}
		
		//System.out.println(nodelist);
		return nodelist;
	}
	/**
	 * 获取菜单根节点
	 * 
	 * @return nodelist
	 */
	@RequestMapping(value = "/menuTree", method = RequestMethod.GET)
	public @TreeResponseBody
	List<TreeNode>  getDataBaseRoot(@RequestParam("params") String itemType) {
		//System.out.println(itemType);
		String nodeID =MenuTreeUtils.stringUtils(itemType);
		String rid=nodeID;
		//System.out.println("业务角色ID----------------"+rid);
		List<TreeNode> nodelist = new ArrayList<TreeNode>();
		//System.out.println();
		 List<TreeMenu> list =treemenuBizc.getMenuRoot(rid);
		 for(int i = 0; i < list.size(); i++){
			 TreeNode node = new TreeNode();
			 TreeMenu tm = (TreeMenu)list.get(i);
			 node.setId(tm.getMenuid());
			 node.setText(treemenuBizc.getMenuNameById(tm.getMenuid()));
				node.setHasChildren(treemenuBizc.hasChildMenu(tm.getMenuid(),rid));
				nodelist.add(node);
		 }
			 return nodelist;
		
		
	}
	
	/**
	 * 获取菜单指定id的下级节点
	 * 
	 * @param id
	 * 
	 * @return nodelist
	 */
	@RequestMapping(value = "/menuTree/{id}", method = RequestMethod.GET)
	public @TreeResponseBody
	List<TreeNode> getDataBaseNodes(@PathVariable String id,
			@ItemRequestParam("params") String itemType) {
		List<TreeNode> nodelist = new ArrayList<TreeNode>();
		List<TreeMenu> list =treemenuBizc.getMenuNodes(id,itemType);
		 for(int i = 0; i < list.size(); i++){
			 TreeNode node = new TreeNode();
			 TreeMenu tm = (TreeMenu)list.get(i);
			 node.setId(tm.getMenuid());
			 node.setText(treemenuBizc.getMenuNameById(tm.getMenuid()));
				node.setHasChildren(treemenuBizc.hasChildMenu(tm.getMenuid(),itemType));
				nodelist.add(node);
				
		 }
		 //System.out.println(nodelist);
		return nodelist;
	}
	
	/**
	 * 从统一权限获取菜单根节点
	 * 
	 * @return nodelist
	 */
		@RequestMapping(value = "/iMenuTree", method = RequestMethod.GET)
	public @TreeResponseBody
	List<TreeNode> getCD() {
			List<TreeNode> nodelist = new ArrayList<TreeNode>();
			 List<Function> list =treemenuBizc.getiMenuRoot();
			 for (int i = 0; i < list.size(); i++) {
				 TreeNode node = new TreeNode();
				 Function fun= list.get(i);
				 node.setId(fun.getId());
				 node.setText(fun.getName());
				 node.setHasChildren(treemenuBizc.hasChildiMenu(fun.getId()));
				 nodelist.add(node);
			}
			 //System.out.println(nodelist);
		return nodelist;
	}
	/**
	 * 获取菜单指定id的下级节点
	 * 
	 * @param id
	 * 
	 * @return nodelist
	 */
	@RequestMapping(value = "/iMenuTree/{id}", method = RequestMethod.GET)
	public @TreeResponseBody
	List<TreeNode> getISCNodes(@PathVariable String id,
			@ItemRequestParam("params") String itemType) {
		List<TreeNode> nodelist = new ArrayList<TreeNode>();
		 List<Function> list =treemenuBizc.getiMenuNodes(id);
		 for (int i = 0; i < list.size(); i++) {
			 TreeNode node = new TreeNode();
			 Function fun= list.get(i);
			 node.setId(fun.getId());
			 node.setText(fun.getName());
			 node.setHasChildren(treemenuBizc.hasChildiMenu(fun.getId()));
			 nodelist.add(node);
		 }
		// System.out.println(nodelist);
		return nodelist;
	}
	/**
	 * 从统一权限通过节点名获取菜单根节点
	 * 
	 * @return nodelist
	 */
		@RequestMapping(value = "/iMenuTree/check", method = RequestMethod.GET)
		public @TreeResponseBody
		Object findNodeByName(@RequestParam("nodeName") String nodeName){
		  List idList = treemenuBizc.findNodeIdByName(nodeName);
		  List<TreeNode> nodelist = new ArrayList<TreeNode>();
		  if(idList.size()!=0){
			  for (int i = 0; i < idList.size(); i++) {
					 TreeNode node = new TreeNode();
					 Function fun= (Function) idList.get(i);
					 node.setId(fun.getId());
					 node.setText(fun.getName());
					 node.setHasChildren(treemenuBizc.hasChildiMenu(fun.getId()));
					 nodelist.add(node);
				}
			return nodelist;
		  }else{
			return "fail";
		  }
		}
		/**
		 * 保存节点
		 * 
		 */
			@RequestMapping(value = "/menuTree/save", method = RequestMethod.POST)
			
			public @ItemResponseBody
			void saveNode(@ItemsRequestBody List<Map<String, String>> list){
			 if(list.size()>0&&list!=null){ //防止数据为空导致异常
			  //获取菜单id数组
				Map<String, String> map = new HashMap<String, String>();
		    	for (int i = 0 ; i<list.size(); i++){
		    		map=list.get(i);
		    		String fids = map.get("MenuIDs");
		    		String fidArr []= fids.split(",");
		    		String fidArrS []= treemenuBizc.isInclude(fidArr, map.get("rid"));
		    		List<Function> flist=treemenuBizc.funByFids(fidArrS);
		    		//调用操作数据库
		    		treemenuBizc.saveNode(flist, map.get("rid"));
		    	}
			 }
			}
		/**
		 * 删除节点
		 * 
		 */
		    @RequestMapping(value = "/menuTree/delete", method = RequestMethod.POST)
			public @ItemResponseBody
			void deleteNode(@ItemsRequestBody List<Map<String, String>> list){
		    	Map<String, String> map = new HashMap<String, String>();
		     if(list.size()>0&&list!=null){ //防止数据为空导致异常
		    	for (int i = 0 ; i<list.size(); i++){
		    		map=list.get(i);
		    		//调用操作数据库
		    		String fids = map.get("MenuIDs");
		    		String fidArr []= fids.split(",");
		    		for (int j = 0; j < fidArr.length; j++) {
		    			//System.out.println("菜单ID------"+fidArr[j]);
		    			treemenuBizc.deleteNode(fidArr[j], map.get("rid"));
					}
		    		
		    	}
			 }
		    }
}
