package com.pugwoo;

public class OptimisticLock {

	public static void main(String[] args) {

		// 假设当前对象的version为10
		
		// 开始两个session及其transaction
		
		// 某个session更新数据，此时version变为11
		
		// 该session提交数据
		
		// 第二个session提交时，由于其version才为10，所以拒绝提交
	}

}
