2011年1月9日 下午03:16:45

Spring 会自动在 CLASSPATH 根路径中按照如下顺序搜寻配置文件并进行加载
（以 Locale 为 zh_CN 为例） : 
messages_zh_CN.properties 
messages_zh.properties 
messages.properties 
messages_zh_CN.class 
messages_zh.class 
messages.class 


乱码问题解决2种方法：
1) 执行native2ascii messages_zh_CN.properties msg.txt
然后把msg.txt替换messages_zh_CN.properties
【注】native2ascii是jdk自带的命令

2)或者在Apache Ant中设置
<native2ascii encoding="GBK" src="${src}" dest="${build}"/> 