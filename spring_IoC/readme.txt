2011年4月14日 下午09:22:47

该项目演示最基本的Spring功能：【依赖注入】

注意Spring实现的是对象管理，解决它们之间的依赖关系

1)基本类型注入演示，Bean1类包括这些类型
     通过修改applicationContext将这些类加入容器，再在Client客户端中调用Spring容器获得对象
     
2)通过Bean2和Bean3的依赖关系演示配置有依赖关系的类
     其中Bean2依赖于Bean3
     
3)通过Bean4的Date数据成员演示Spring的属性编辑器
     自定义属性编辑器:
	* 继承PropertyEditorSupport
	* 覆盖setAsText()方法
	* 将自定义的属性编辑器注入到spring中
	
4、了解关于多配置文件的读取方式
	* 可以采用数组
	* 可以采用*匹配模式
	
5、如何减少spring的配置文件
	* 通过<bean>标签将公共的配置提取出来，然后指定<bean>标签中的abstract属性为true
	* 在其他<bean>标签中指定其parent即可
    
6、spring默认在创建BeanFactory时，将配置文件中所有的对象实例化并进行注入
   但可以采用相关的属性延迟配置文件的初始化，如：default-lazy-init="true"   
   
7、通过名称自动装配
       首先在<beans>标签中加入属性 default-autowire="byName"
       或<bean>标签中加入属性autowire="byName"
       如果一个类A依赖于另一个类B，如果类B的对象名和A的getter方法一致，则不需要配置A类该属性的<property>标签

8、通过类型自动装配
        首先在<beans>或<bean>标签中加入属性 default-autowire="byType"
        或<bean>标签中加入属性autowire="byType"
       如果一个类A依赖于另一个类B，如果类B的对象的只有一个，而其类型和A的某个属性的类型一致，则不需要配置。

9、bean的scope作用域属性
   *Singleton
        只创建一个对象
   *Prototype
        一次创建一个新对象
       
////其它细节
import : 导入其他xml文件

<bean init-method指定bean载入容器时调用的函数
destroy-method指定bean从容器中删除时调用的函数
对应的，该bean也可以通过实现接口InitializingBean和DisposableBean来实现

===============================================
2011年1月9日 下午07:38:36

BeanPostProcessor可以像AOP一样对类的初始化之前和初始化之后进行处理，
但是好像没有可以选择对哪些类、哪些方法或属性进行处理

