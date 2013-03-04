package com.pugwoo;

import com.pugwoo.extend.A;
import com.pugwoo.extend.B;
import com.pugwoo.extend.Generic;
import hibernate.HibernateUtils;

import junit.framework.TestCase;

/**
 * 2011年4月12日 下午01:16:30
 */
public class ExtendTest extends TestCase {

	public void testSave(){
		Generic g = new Generic();
		g.setGeneric("generic");
		
		A  a = new A();
		a.setGeneric("a_gen");
		a.setA("a");
		
		B b = new B();
		b.setGeneric("b_gen");
		b.setB("b");
		
		HibernateUtils.save(g);
		HibernateUtils.save(a);
		HibernateUtils.save(b);
		
		HibernateUtils.commit();
	}
}
