package org.apache.zookeeper.recipes.lock;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

/**
 * 2013年3月3日
 */
public class Test {

	public static void main(String[] args) throws IOException {
		
		String connectString = "127.0.0.1:2181";
		int sessionTimeout = 10000;

		ZooKeeper zooKeeper = new ZooKeeper(connectString, sessionTimeout,
				null);
		WriteLock lock = new WriteLock(zooKeeper, "/lock", null);
		try {
			System.out.println(lock.lock());
			System.out.println(lock.lock()); // ? 不是应该阻塞在这里吗
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
