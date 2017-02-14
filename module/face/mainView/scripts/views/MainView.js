$ns("mainView.views");

$import("mx.controls.ToolBar");
$import("mx.datacontrols.DataForm");
$import("mx.containers.Panel");
$import("mx.containers.HtmlContainer");
$import("mx.containers.HSplit");
$import("mx.containers.VSplit");

mainView.views.MainView = function()
{
    var me = $extend(mx.views.View);
    var base = {};
    
    //me.objID = null;
    me.object = null;
    //me.vSplit = null;
    
    
    me.expanded = false;//popupwindow是否为空
    //me.htmlContainer = null;
    
    //me.form = null;
    
    base.init = me.init;
    me.init = function()
    {
        base.init();

        _initControls();
    };
    
    function _initControls()
    {
//        var toolBar = new mx.controls.ToolBar({
//            width: "100%",
//	    items:[
//	    	{name: "save", text: mx.msg("SAVE"), imageKey : "save", toolTip: mx.msg("SAVE"), onclick: me.controller._btnSave_onclick},
//	    	"-",
//	    	{name: "record", text: "录制", imageKey : "new", toolTip:"录制", 
//	    		items:[
//	    		       {name: "location", text:"定位点", imageKey : "new", toolTip: "录制定位点", onclick: me.controller._btnRecordLoaction_onclick},
//	    		       {name: "presentation", text: "演示", imageKey : "new", toolTip: "录制演示", onclick: me.controller._btnRecordPresentation_onclick}
//	    		       ]}
//	    ]
//        });
//        
//        me.addControl(toolBar);
        
        
        
    	//上下
        var hSplit = new mx.containers.HSplit({
            rows:"105px, auto"
            
        });
        
        
        
//        var titlePanel = new mx.containers.Panel(
//        	{ 
//        		name:"titlePanel",
//        		width:"100%",
//        		height:"100%",
//        		displayHead:false
//        		//css:{background:"url(\"images/top1.png\")no-repeat"}
//        			
//        	}
//        );
        
        //hSplit.addControl(titlePanel,0);
        
        var topL = document.createElement("div");
        topL.id = "abc";
        topL.style.cssText = "width:100%;height:100%;background:url('images/top1.png');display:inline-block";
        hSplit.$panel1.append(topL);
        //hSplit.addControl(topL,0);
       	//titlePanel.append(topL);
        
        
//        me.expandBtn = new mx.controls.Button({
//            text: "展开",
//            onclick:me.controller._expandBtn_onclick
//            
//        
//            //css:{paddingRight:"0px",paddingBottom:"0px"}
//        });
//        //me.expandBtn.setRight(0);
//        //me.expandBtn.setBottom(0);
//        
//        titlePanel.addControl(me.expandBtn,0);
        
        
        
        
//        var sgPanel = new mx.containers.Panel(
//        		{ name: "sgPanel",width:"100%",height:"100%",displayHead:false}); 
//        me.addControl(hSplit);
//        
//        hSplit.addControl(sgPanel,1);
//
//        me.htmlContainer = new mx.containers.HtmlContainer({
//            url:"main.html",
//            height:"100%",
//            width:"100%",
//            onload:me.controller._sgworldInit});
//        sgPanel.addControl(me.htmlContainer);
//        //me.addControl(sgworldDiv);


	
		var sgDiv = document.createElement("div");
		sgDiv.style.cssText = "position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px;";
		var TE3DWindow = document.createElement('object');
		TE3DWindow.id = "TE3DWindow";
		TE3DWindow.style.cssText = "position: absolute; top: 0px; left: 0px; bottom: 0px; width : 100% ;height: 100%; filter: alpha(opacity=0); z-index: -1;";
		TE3DWindow.classid = "clsid:3a4f9192-65a8-11d5-85c1-0001023952c1";
		sgDiv.appendChild(TE3DWindow);
		
		var sgworld = document.createElement('object');
		sgworld.id = "sgworld";
		sgworld.classid = "CLSID:3a4f9199-65a8-11d5-85c1-0001023952c1";
		sgworld.style.cssText = "visibility: hidden; height: 0";
		sgDiv.appendChild(sgworld);
		
		//hSplit.$panel2.append(sgDiv);
	
		

        me.on("activate", me.controller._onactivate);
    }
    
    


    
    
    return me.endOfClass(arguments);
};