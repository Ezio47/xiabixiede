$import("mainView.views.MainViewController");

mx.weblets.WebletManager.register({
    id: "mainView",
    name: "全球能源互联网主场景",
    onload: function(e)
    {
    },
    onstart: function(e)
    {
        var mvc = new mainView.views.MainViewController();
        e.context.rootViewPort.setViewController(mvc);
    }
});