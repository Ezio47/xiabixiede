$ns('threeD.views');

$import('threeD.views.MainView');

threeD.views.MainViewController = function()
{
    var me = $extend(mx.views.ViewController);
    
    me.getView = function()
    {
        if (me.view == null)
        {
            me.view = new threeD.views.MainView( { controller: me } );
        }
        
        return me.view;
    };
    
    me._onactivate = function()
    {
       
    };
    
    return me.endOfClass(arguments);
};