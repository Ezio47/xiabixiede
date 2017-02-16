$ns("threeD.views");

$import("mx.containers.HSplit");
$import("mx.containers.HtmlContainer");


threeD.views.MainView = function()
{
    var me = $extend(mx.views.View);
    var base = {};
    me.hSplit = null;
    me.htmlContainer = null;
    
    base.init = me.init;
    me.init = function()
    {
        base.init();

        _initControls();
    };
    
    function _initControls()
    {
    	
    	
    	_initHsplit();
        me.on("activate", me.controller._onactivate);
    }
    
    
    
    //创建一个上下方向的split
    function _initHSplit(){
    
    	me.hSplit = new mx.containers.HSplit({
    		rows:"20% 80%",
    		resizable:true,
    		borderThick:"2px"
		});
		
		me.$(e).css({
			"background":"#e2e5ec"
		});
		
		me.hSplit.$panel1.css({
			"border" : "0px solid #e1e4eb",
			"background" : 'url(image/allPlantbg.png) repeat',
			"border-bottom" : "1px solid #bec8d1",
			"-webkit-border-radius" : "0px 0px 0px 0px",
			"border-radius" : "0px 0px 0px 0px"
		});
		
		me.hSplit.$panel2.css({
					"border" : "0px solid #e1e4eb"
				});
				
		me.addControl(me.hSplit);
    }
    
    
    function _initHtmlContainer(){
    	
    	me.htmlContainer = new mx.containers.HtmlContainer({
    		url:"main.html",
    		height:"100%",
    		width:"100%"
    		
    		
    	});
    	
    	me.hSplit.addControl(me.htmlContainer,1);
    }
	
	
	
    
    return me.endOfClass(arguments);
};