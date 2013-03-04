package com.pugwoo.manymany;

import java.util.HashSet;
import java.util.Set;

/**
 * 订单
 */
public class Order {

	private Long id;

	// 总价
	private Double total;
	
	// 客户姓名
	private String name;
	
	// 购买货物
	private Set<Product> products = new HashSet<Product>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	
}
