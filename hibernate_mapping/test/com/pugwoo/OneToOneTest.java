package com.pugwoo;

import junit.framework.TestCase;

import hibernate.HibernateUtils;
import com.pugwoo.oneone.IdCard;
import com.pugwoo.oneone.Person;

/**
 * 2011年4月10日 上午12:13:40
 */
public class OneToOneTest extends TestCase{

	public void testIdCardPerson(){
		IdCard idCard = new IdCard();
		idCard.setCardNo("88888888");
		
		Person person = new Person();
		person.setName("pugwoo");
		person.setIdCard(idCard);
		
		//不会出现TransientObjectException异常
		//因为一对一主键关联映射中，默认了cascade属性
		HibernateUtils.save(person);
		
		HibernateUtils.commit();
	}
}
