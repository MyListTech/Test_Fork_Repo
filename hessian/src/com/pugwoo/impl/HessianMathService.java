package com.pugwoo.impl;

import com.caucho.hessian.server.HessianServlet;
import com.pugwoo.MathService;

/**
 * 2012年11月10日 下午04:24:21
 */
@SuppressWarnings("serial")
public class HessianMathService extends HessianServlet implements MathService {

	public int add(int a, int b) {
		return a + b;
	}
}