package com.pugwoo.bean;

/**
 * 测试Java运行用时，一次只允许一个线程调用
 */
public class JavaTime {

	private static Long start = 0L;

	private static Long end = 0L;

	// 修正
	private static Long delta = 0L;

	static {
		long sum = 0;
		for(int i=0; i<1000; i++){
			long s = System.nanoTime();
			long e = 0L;
			if(1 > 0)
				e = System.nanoTime();
			sum += e - s;
		}
		delta = sum / 1000;
	}

	/**
	 * 记录当前计时状态： false 未开始 true 开始未结束
	 */
	private static boolean COUNTING = false;

	/**
	 * 开始计时
	 */
	public static void start() {
		if (!COUNTING) {
			COUNTING = true;
			start = System.nanoTime();
		}
	}

	/**
	 * 结束计时
	 */
	public static void stop() {
		if (COUNTING) {
			end = System.nanoTime();
			COUNTING = false;
		}
	}

	/**
	 * 停止并获得毫秒数
	 */
	public static double getTimeInMs() {
		stop();
		return (end - start - delta) / 1000000.0;
	}

	/**
	 * 停止并获得纳秒数
	 */
	public static Long getTimeInNs() {
		stop();
		return end - start - delta;
	}

	/**
	 * 停止并打印
	 */
	public static void printInMs() {
		stop();
		System.out.println(getTimeInMs() + "ms.");
	}

	/**
	 * 停止并打印
	 */
	public static void printInNs() {
		stop();
		System.out.println(getTimeInNs() + "ns.");
	}
}
