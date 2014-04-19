package com.pugwoo.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 2014年4月2日 11:20:20
 * 来自：http://www.cnblogs.com/xwdreamer/archive/2012/02/21/2360818.html
 * 
 * 启动：bin\activemq.bat
 * 页面查看：http://localhost:8161/admin/queues.jsp 用户名密码admin
 */
public class Point2PointSender {

	private static final int SEND_NUMBER = 5;

	public static void main(String[] args) {
		// ConnectionFactory ：连接工厂，JMS 用它创建连接
		ConnectionFactory connectionFactory;

		// Connection ：JMS 客户端到JMS Provider 的连接
		Connection connection = null;

		// Session： 一个发送或接收消息的线程
		Session session;

		// Destination ：消息的目的地;消息发送给谁.
		Destination destination;

		// MessageProducer：消息发送者
		MessageProducer producer;

		// TextMessage message;
		// 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
		connectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER, // 用户名
				ActiveMQConnection.DEFAULT_PASSWORD, // 密码
				"tcp://localhost:61616");

		try {
			// 构造从工厂得到连接对象
			connection = connectionFactory.createConnection();
			// 启动
			connection.start();
			
			// 获取操作连接
			session = connection.createSession(Boolean.TRUE,
					Session.AUTO_ACKNOWLEDGE);
			
			// 获取指定的队列FirstQueue
			destination = session.createQueue("FirstQueue");
			
			// 得到消息生成者【发送者】
			producer = session.createProducer(destination);
			
			// 设置不持久化，此处学习，实际根据项目决定
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			
			// 构造消息，此处写死，项目就是参数，或者方法获取
			sendMessage(session, producer);
			
			// 提交，没有提交的话发送的信息不会到activeMQ服务器
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != connection)
					connection.close();
			} catch (Throwable ignore) {
			}
		}
	}

	public static void sendMessage(Session session, MessageProducer producer)
			throws Exception {
		for (int i = 1; i <= SEND_NUMBER; i++) {
			TextMessage message = session.createTextMessage("ActiveMq 发送的消息"
					+ i);
			// 发送消息到目的地方
			System.out.println("发送消息：" + "ActiveMq 发送的消息" + i);
			producer.send(message);
		}
	}
}