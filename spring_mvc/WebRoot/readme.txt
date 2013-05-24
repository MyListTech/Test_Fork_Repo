2011年4月15日 下午06:19:27

Spring MVC工作过程:

1) 用户发送URL request
2) 由Web.xml中的Spring Dispatcher Servlet接收
3) 转发到Controller，Controller处理后返回ModelAndView对象
4) Spring Dispatcher Servlet根据ModelAndView对象结合viewResolver渲染成HTML文件


该项目还演示了在Web容器(如Tomcat)中直接载入Spring配置文件,即把Spring作为tomcat的IoC容器


步骤：
1) 配置web.xml，主要是dispatcherServlet配置和spring配置文件的位置【通用配置，需要设置xml位置】
2) 创建Controller，推荐使用annotation【通用配置，需要改下Controller的包位置】
       使用注解就不需要实现Controller接口，直接在需要映射的方法上注解就行

============2013年5月23日 08:22:37
拦截器
拦截器是面向切面编程的经典，常用来判断登录状态或审计（记录信息），非常方便。
SpringMVC 中的Interceptor 是链式的调用的，pre的方法先声明先调用，post的方法先声明后调用
拦截器都实现了HandlerInterceptor接口：
boolean preHandle(  // 该方法将在请求处理之前进行调用。返回false停止请求
            HttpServletRequest request, HttpServletResponse response,   
            Object handler) 
void postHandle( // 在当前请求进行处理之后，也就是Controller 方法调用之后执行，
 // 但是它会在DispatcherServlet 进行视图返回渲染之前被调用
            HttpServletRequest request, HttpServletResponse response,   
            Object handler, ModelAndView modelAndView)   
void afterCompletion(  //该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。
            HttpServletRequest request, HttpServletResponse response,   
            Object handler, Exception ex) 

配置
头部：xmlns:mvc="http://www.springframework.org/schema/mvc"
xsi:schemaLocation加上http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd
然后用<mvc:interceptors>定义一系列拦截器：
1）使用<bean>对所有的请求都生效（也可以在这个bean内手动判断url来实现对哪些请求生效）
2）使用<mvc:interceptor>可以设定对哪些请求生效

执行图参见：http://jinnianshilongnian.iteye.com/blog/1670856