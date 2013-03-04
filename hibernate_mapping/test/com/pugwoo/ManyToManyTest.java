package com.pugwoo;

import hibernate.HibernateUtils;
import com.pugwoo.manymany.Order;
import com.pugwoo.manymany.Product;

/**
 * 2011年4月10日 上午12:13:46
 */
public class ManyToManyTest {

	public static void main(String[] args) {

		Product product1 = new Product();
		product1.setName("雨伞");
		product1.setPrice(12.5);
		product1.setDescription("一把雨伞");
		
		Product product2 = new Product();
		product2.setName("面包");
		product2.setPrice(2.3);
		product2.setDescription("好吃的面包");
		
		Order order1 = new Order();
		order1.setName("pugwoo");
		order1.setTotal(14.8);
		order1.getProducts().add(product1);
		order1.getProducts().add(product2);
		
		Order order2 = new Order();
		order2.setName("nick");
		order2.setTotal(2.3);
		order2.getProducts().add(product2);
		
		HibernateUtils.save(product1);
		HibernateUtils.save(product2);
		HibernateUtils.save(order1);
		HibernateUtils.save(order2);
		
		HibernateUtils.commit();
	}

}
