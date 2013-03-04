package com.pugwoo.hibernate.view.cmp;


/**
 * 过滤条件的子项
 * 构成的查询字句为：name + op + value
 * @author PugwooChia
 * 2011-5-5
 */
public class FilterItem {
	
	public FilterItem(String name, String op, Object value){
		this.name = name;
		this.op = op;
		this.value = value;
		
		// 适应SQL，虽然hibernate会自动处理这些，不过还是显式做一下吧
		if(value == null){
			if(op.equals("="))
				this.op = "is";
			else if(op.equals("!=") || op.equals("<>"))
				this.op = "is not";
		}
	}

	private String name;
	
	/**
	 * 取值：
	 * 各种关系运算符号：< <= > >= != <> =等
	 * 字符表达的符号：like is not in
	 * 【不支持between，between请转换成>= AND <=】
	 */
	private String op;
	
	/**
	 * 类型：
	 * Number
	 * 容器Set
	 * String和其它类型：均当作String类型看待
	 */
	private Object value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public static void main(String args[]){
	}
}
