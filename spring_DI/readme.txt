2010年12月17日 上午12:55:07

该项目演示Spring的各种注入类型：

1)User演示setter注入、构造器参数注入

2)Random演示方法注入：即使用Spring配置的Bean替代原有方法的返回值
需要cglib

3)Hello演示方法替换，要替换的新方法必须是实现MethodReplacer接口，用reimplement方法替换原来的方法
需要cglib

还有一种是parent引用<ref parent，主要是用来代理父类，与父类同名。

内部bean总是匿名的且它们总是prototype模式的.

3.3.2.4.1. 集合的合并.
3.3.2.6.2. 使用p名称空间配置属性
3.3.3. 使用depends-on
3.3.4. 延迟初始化bean  <bean lazy-init  <beans default-lazy-init

<null/>用于处理null值