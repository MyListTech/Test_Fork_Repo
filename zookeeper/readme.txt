Lock
http://chenkangxian.iteye.com/blog/1525807
http://agapple.iteye.com/blog/1184040
关键点：
node节点选择为EPHEMERAL_SEQUENTIAL自增ID，相当于由这个特性来保证一致性

注意：
使用EPHEMERAL会引出一个风险：在非正常情况下，网络延迟比较大会出现session timeout，zookeeper就会认为该client已关闭，从而销毁其id标示，竞争资源的下一个id就可以获取锁。这时可能会有两个process同时拿到锁在跑任务，所以设置好session timeout很重要。
同样使用PERSISTENT同样会存在一个死锁的风险，进程异常退出后，对应的竞争资源id一直没有删除，下一个id一直无法获取到锁对象。
没有两全其美的做法，两者取其一，选择自己一个能接受的即可