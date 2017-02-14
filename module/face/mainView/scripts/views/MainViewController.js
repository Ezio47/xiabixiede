$ns("mainView.views");
$import("mainView.views.MainView");

mainView.views.MainViewController = function()
{
    var me = $extend(mx.views.ViewController);
    var base = {};
  
    me.getView = function()
    {
        if (me.view == null)
        {
            me.view = new mainView.views.MainView({ controller: me });
        }
        return me.view;
    };
    
    me._onactivate = function(e)
    {
        // TODO: 窗体激活时的逻辑。
	if (me.view != null && typeof(me.view.form) != "undefined")
	{
 	    //me.view.form.load(me.view.objID);
		
		
	}	
    };
    
//    me._sgworldInit = function(){
//    	//alert("11");
//    	
//    	var sgworld = window.document.getElementById("sgworld");
//    	var file = "D:\\智慧城市框架\\智慧城市开发框架\\De.fly";
//    	sgworld.Project.Open(file);
//    
//    };
//    
//    me._expandBtn_onclick = function(){
//    	
//    	
//    	var mainView = me.getView();
//    	var sgworld = window.document.getElementById("sgworld");
//    	if(!mainView.expanded){
//    		
//    		var comPopup = null;
//    		var screenRectHeight = sgworld.Window.Rect.Height;
//    		var screenRectWidth = sgworld.Window.Rect.Width;
//    		var width = screenRectWidth * 0.1;
//    		comPopup = sgworld.Creator.CreatePopupMessage(0, "", screenRectWidth- width, 0, width, screenRectHeight-20);
//        	comPopup.AllowResize = false;
//        	comPopup.ShowCaption = false;
//        	comPopup.AnchorToView = false;
//        	sgworld.Window.ShowPopup(comPopup);
//        	mainView.expanded = true;
//        	
//        	
//    	}
//    	else{
//    		 sgworld.Window.RemovePopupByCaption(0);
//    		 mainView.expanded = false;
//    	}
//    	//vsplit.expand();
//
//    	
//    };
//    
//    me._btnSave_onclick = function()
//    {
//        //me.view.form.save();
//    	//alert("save clicked");
//    };
//    
//    
//    me._btnRecordLoaction_onclick = function(){
//    	//alert("recordLocation clicked");
//    };
//    
//    me._btnRecordPresentation_onclick = function(){
//    	//alert("recordPresentation clicked");
//    };
//    
////    window.onresize = function(){
////    	//窗口大小变换时，重画弹出的popup
////    	var mainView = me.getView();
////    	
////    	//popupwindow = true;
////    	if(mainView.expanded){
////    		sgworld.Window.RemovePopupByCaption(0);
////    		var comPopup = null;
////    		var screenRectHeight = sgworld.Window.Rect.Height;
////    		var screenRectWidth = sgworld.Window.Rect.Width;
////    		var width = screenRectWidth * 0.1;
////    		comPopup = sgworld.Creator.CreatePopupMessage(0, "", screenRectWidth- width, 0, width, screenRectHeight-20);
////        	comPopup.AllowResize = false;
////        	comPopup.ShowCaption = false;
////        	comPopup.AnchorToView = false;
////        	sgworld.Window.ShowPopup(comPopup);
////        	
////    	}
////    	
////    }
    
    window.onload = function(){
    	var sgworld = window.document.getElementById("sgworld");
    	var file = "D:\\智慧城市框架\\智慧城市开发框架\\De.fly";
    	sgworld.Project.Open(file);
    }
    
    
    
    return me.endOfClass(arguments);
};