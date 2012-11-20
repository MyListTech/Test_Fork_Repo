2012年10月27日 22:03:13

DWR使用java servlet和javascript实现了通过javascript异步调用java方法的功能
首先dwr的javascript代理获得用户调用时传递的参数，然后发出ajax请求，url地址即后台java对应的类和方法
然后后台java获得到url和参数，就知道调用哪个类的哪个方法，穿入响应参数，然后获得返回值
最后前台javascript得到后台返回的数据，执行回调函数

总的来说，DWR的设计有其方便的一面，但我不喜欢这样的设计，我希望前台和后台可以足够的分开。

1. DWR的js ajax代理类很方便，但我更喜欢jquery或使用原始ajax去调用，由于DWR的调用的参数是设定好的，
   因此，使用其它方式替代时不够透明
2. DWR暴露了后台java的类和方法，这点不太安全，虽然可以设置哪些方法可以调用哪些不可以

推荐使用jquery+spring mvc来代替dwr。