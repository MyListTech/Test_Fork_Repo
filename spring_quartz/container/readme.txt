这个示例把quartz配置到spring容器中，crontab也是写死的。

适合随容器启动的定时任务。

Quartz本身也带了一个容器，不用依赖spring的容器，看examples里的plugin那个
但是这个容器的配置挺复杂的，看起来没有实用的地方

这里还有个问题，Service的execute方法不能传递任何参数，也不知道怎样获知quartz context