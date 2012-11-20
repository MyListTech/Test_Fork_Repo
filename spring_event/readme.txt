2011年1月9日 下午04:36:00

当LoginAction执行的时候，激发一个自定义消息ActionEvent，该事件将由ActionListener捕获

1)通过继承ApplicationEvent自定义一个ActionEvent

2)在程序过程中调用applicationContext.publishEvent发布event

3)通过实现ApplicationListener接口接收并处理event

注意：需要导入Spring AOP包!