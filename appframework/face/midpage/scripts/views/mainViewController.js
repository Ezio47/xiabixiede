$ns("midpage.views");

//midpage、mainView在编译时将替换为实际值，设计过程中不要随意修改

midpage.views.mainViewController = function() {
	
	var me = $extend(mx.views.ViewController);
	var base = {};
	me.typeName="midpage.views.mainViewController";
	me.getController=function(key){
		return me.getLinkViewController("midpage.views."+key+"Controller");
	};
	me.getView = function() {
		if (me.view == null) {
			me.view = new midpage.views.mainView({ controller: me });
		}
		return me.view;
	};
	/*************公共函数库定义****************************/
	//引入外部定义的公共函数库,me.getLinkViewController、me.getController二选其一
	//me.utils=me.getController("abcView").utils;
	//me.utils=me.getLinkViewController("aaWeblet.views.bbViewController").utils;
	if(typeof(me.utils)=="undefined"||me.utils==null)
		me.utils={};
	/*************公共函数***********
	me.utils.customFn=function(input){
		alert("自定义的公共函数customFn,传入参数："+input);
	};
	********************/
	/*************公共函数库结束****************************/
	me._onactivate = function(e) {
		// TODO: 窗体激活时的逻辑。
	};


	


	return me.endOfClass(arguments);
};