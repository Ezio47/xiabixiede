$ns("mainView.views");

$import("mx.controls.ToolBar");
$import("mx.datacontrols.DataForm");
$import("mx.containers.Panel");
$import("mx.containers.HtmlContainer");
$import("mx.containers.HSplit");
$import("mx.containers.VSplit");
$import("mx.containers.Accordion");
 	
mainView.views.MainView = function()
{
    var me = $extend(mx.views.View);
    var base = {};
    
    //me.objID = null;
    me.object = null;
    //me.vSplit = null;
    me.sgworld = null;
    me.hSplit = null;
    me.expanded = false;//popupwindow是否为空
    me.htmlContainer = null;
    
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
        me.hSplit = new mx.containers.HSplit({
            rows:"105px, auto"
            
        });
        
        
        
        var titlePanel = new mx.containers.Panel(
        	{ 
        		name:"titlePanel",
        		width:"100%",
        		height:"100%",
        		displayHead:false,
        		css:{border:"10",bordercolor:"#000000"}
        		//css:{background:"url(\"images/top1.png\")no-repeat"}
        			
        	}
        );
        //titlePanel.setBorder("border:10");
        
        me.hSplit.addControl(titlePanel,0);
        
        var topL = document.createElement("div");
        topL.id = "abc";
        topL.style.cssText = "width:100%;height:100%;background:url('images/top1.png');display:inline-block";
        //me.hSplit.$panel1.append(topL);
        titlePanel.append(topL,0);
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
        
        
        
        
        var sgPanel = new mx.containers.Panel(
        		{ name: "sgPanel",width:"100%",height:"100%",displayHead:false}); 
        me.addControl(me.hSplit);
        
        
//
//        me.htmlContainer = new mx.containers.HtmlContainer({
//            url:"main.html",
//            height:"100%",
//            width:"100%",
//            onload:me.controller._sgworldInit});
//        sgPanel.addControl(me.htmlContainer);
        //me.addControl(sgworldDiv);
        
        var accordion = new mx.containers.Accordion({
    		height:"100%",
    		width:"100%",
    		panels:[
        		{ title: "三维地图", name: "threeDMap" },
        		{ title: "工具箱", name: "toolBox" }
    				]
		});
		
		var shapingButton = new mx.controls.Button(
			{ 
				text: "沙坪定位",
				css:{background:"url('images/41.png')"},
				onclick:me.controller._shapingDot_Onclick
			}
		);
		
		
		var button = new mx.controls.Button(
			{ 
				text: "点选查询",
				css:{background:"url('images/41.png')"},
				onclick:me.controller._search_Onclick
			}
		);
		accordion.panels["threeDMap"].addControl(shapingButton);
		accordion.panels["toolBox"].addControl(button);
		
		var vSplit = new mx.containers.VSplit({
    		cols:"10%,90%"
		});
		vSplit.addControl(accordion,0);
		vSplit.addControl(sgPanel,1);

		me.hSplit.addControl(vSplit,1);

	
		var sgDiv = document.createElement("div");
		sgDiv.style.cssText = "position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px;";
		var TE3DWindow = document.createElement('object');
		TE3DWindow.id = "TE3DWindow";
		TE3DWindow.style.cssText = "position: absolute; top: 0px; left: 0px; bottom: 0px; width : 100% ;height: 100%; filter: alpha(opacity=0); z-index: -1;";
		TE3DWindow.classid = "clsid:3a4f9192-65a8-11d5-85c1-0001023952c1";
		sgDiv.appendChild(TE3DWindow);
		
		me.sgworld = document.createElement('object');
		me.sgworld.id = "sgworld";
		me.sgworld.classid = "CLSID:3a4f9199-65a8-11d5-85c1-0001023952c1";
		me.sgworld.style.cssText = "visibility: hidden; height: 0";
		sgDiv.appendChild(me.sgworld);
		
////		me.hSplit.$panel2.append(sgDiv);
//		me.hSplit.addControl(sgDiv,1);
//		//me.hSplit.append(sgDiv,1);
		sgPanel.append(sgDiv);
		

        me.on("activate", me.controller._onactivate);
    }
    
    
    function createTable(){
    	var div = document.CreateElement("div");
    	
    }
    


    
    
    return me.endOfClass(arguments);
};