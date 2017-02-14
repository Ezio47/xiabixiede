
$import("mx.containers.HtmlContainer");				
$import("mx.windows.Window");				
$import("midpage.views.mainView");				
$import("midpage.views.mainViewController");				

mx.weblets.WebletManager.register(
{
    id: "midpage",
    name: "midpage",
    requires: [],
    onload: function (e) {

    }, 
    onstart: function (e) {
        var mvc = new midpage.views.mainViewController();
        e.context.rootViewPort.setViewController(mvc);
    }
});