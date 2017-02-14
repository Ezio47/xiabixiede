$ns("midpage.views");

midpage.views.mainView=function(){
	
	var client = new mx.rpc.RESTClient();
	var strList = null;
	var data = {ip:myip};
	strList = client.getSync("~/appframework/rest/userinfo/getinfo",data);
	var userid = strList[0];
	var userName = strList[1];
	var userDeptName = strList[2];
	var roleName = strList[3];
	
	var me = $extend(mx.views.View);
	var base = {};
	base.init = me.init;
	me.init = function () {
		me.permissionID = "-1";
		base.init();
		_initControls();
	};
	
	//----声明mx组件变量------
	var _HtmlContainer1 = null;
	var _Window = null;
	
	function _initControls(){
		//---调用初始化函数-----
		_init_HtmlContainer1();
	  
		me.on("activate", me.controller._onactivate);
	}
	
	//-----定义初始化函数-----
	function _init_HtmlContainer1(){
		
//		alert("http://192.168.12.150/LoadTerraExplor.html?userid="+userid+"&loginname="+userName+"&rolename="+roleName+"&userDeptName="+userDeptName);
		
		var w=window.open('','_self','');
        setTimeout(function(){
        	//192.168.3.238/hnweb
            w.location="http://10.223.38.147/LoadTerraExplor.html?userid="+userid+"&loginname="+userName+"&rolename="+roleName+"&userDeptName="+userDeptName;
        },1);
        
//		_HtmlContainer1=new mx.containers.HtmlContainer({width:"100%",height:"100%",
//			url:"http://localhost:8484/LoadTerraExplor.html?userid="+userid+"&loginname="+userName+"&rolename="+roleName+"&userDeptName="+userDeptName
//			,id:"HtmlContainer1"});
//		me.addControl(_HtmlContainer1);
	}
	
	function _init_Window() {		
		if(_Window == null || ((_Window.reusable==false) && _Window.disposed==true)) {
			_Window = midpage.context.windowManager.create({
				entry:true
			});
		}
		_Window.on("activate", function() {
			_Window.setView(me);
		});
		_Window.on("close", function(e){
		    $.each(_Window.controls, function(i, o){
				o.$e.detach();
			});
		})
	}
	
	me.getWindow = function() {
		_init_Window();
		return _Window;
	};
	
	
	me.findControlById = function(controlId){
		try{
			return eval("_"+controlId);
		} catch(err) {
			mx.indicate("info","未找到对应的mx控件:    "+ err.message);
			return null;
		}	
	};
    return me.endOfClass(arguments);
};