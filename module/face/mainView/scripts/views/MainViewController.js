$ns("mainView.views");
$import("mainView.views.MainView");

mainView.views.MainViewController = function()
{
    var me = $extend(mx.views.ViewController);
    var base = {};
    var searching = false;
  
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
    
    
    me._shapingDot_Onclick = function(){
    	
    }
    
    var _3dmlfeatureLayer = null;
    me._search_Onclick = function(){
    	var sgworld = me.getView().sgworld;
    	if(!searching){
    		searching = true;
    		sgworld.Window.SetInputMode(1);
    		sgworld.AttachEvent("OnFrame",SGEventOnFrame);
//    		sgworld.AttachEvent("OnLButtonDown",Mouse_Info_OnLButtonDown);
    	}
    	else{
    		searching = false;
    		sgworld.Window.SetInputMode(0);
    		sgworld.detachEvent("OnFrame",SGEventOnFrame);
//    		sgworld.detachEvent("OnLButtonDown",Mouse_Info_OnLButtonDown);
    		_3dmlfeatureLayer.FeatureProperties.Tooltip.Text = "";
            _3dmlfeatureLayer.FeatureProperties.Tint.SetAlpha(0.0);
    	}
    	
    }
    
    
    var framesNr = 0;
    var FPSLimitation = 10;
    var mi;
    var wpi;
    var lastID;
    var layer2 = null;
    var theFeature;
    var filename;
    var lx;
    var sb_id;
    function SGEventOnFrame(){
    	 try {
    	 		var sgworld = me.getView().sgworld;
                if (sfnum1 == false) {
                    mi = sgworld.Window.GetMouseInfo();
                    wpi = sgworld.Window.PixelToWorld(mi.X, mi.Y);

                    if (wpi.Type == 8192 && lastID != wpi.ObjectID) {

                        theFeature = sgworld.ProjectTree.GetObject(wpi.ObjectID);

                        _3dmlfeatureLayer = sgworld.ProjectTree.GetObject(theFeature.ParentGroupID);
                        filename = theFeature.FeatureAttributes.GetFeatureAttribute("FileName").Value;
                        var words = filename.split('_');

                        //_3dmlfeatureLayer.FeatureProperties.Tooltip.Text = "";
                        _3dmlfeatureLayer.FeatureProperties.Tint.SetAlpha(0.0);
                        if (words[0] == "沙坪500kV变电站" || words[0] == "沙坪500KV变电站" || words[0] == "沙坪变电站500kV" || words[0] == "沙坪变电站500KV" || words[0] == "鼎功500kV变电站" || words[0] == "君山110kV变电站" || words[0] == "杉树变电站") {
                            var objid = words[4].split('.');
                            theFeature.Tint.FromHTMLColor("#00FFFF");
                            theFeature.Tint.SetAlpha(0.6);
                             
                            //theFeature.show = false;
                            //sgworld.ProjectTree.SetVisibility(theFeature.ID,false)
                            _3dmlfeatureLayer.FeatureProperties.Tooltip.Text = "变电站名称：" + words[0] + "\r\n" + "电流类型：" + words[1] + "\r\n" + "设备类型：" + words[2] + "\r\n" + "编码：" + objid[0];
                            lx = words[2];
                            sb_id = objid[0];
                        }

                        lastID = wpi.ObjectID;

                    }
                }

            }
            catch (ex)
            {
               
               
            }

    }
    
    window.onload = function(){
    	//alert("111");
    	//alert("111");
    	var sgworld = me.getView().sgworld;
//    	var file = "D:\\智慧城市框架\\智慧城市开发框架\\De.fly";
    	var file = "D:\\skyline\\lcy\\数据\\hunan.FLY";
    	sgworld.Project.Open(file);
    }
    
    
    
    return me.endOfClass(arguments);
};