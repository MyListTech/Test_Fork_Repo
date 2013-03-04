package com.pugwoo.old;

import hibernate.HibernateUtilsOld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pugwoo.Container;

import junit.framework.TestCase;
@SuppressWarnings("unchecked")
public class ContainerTest extends TestCase {

	
	public void testSave(){
		Container container = new Container();
		Set setValue = new HashSet();
		setValue.add("a");
		setValue.add("b");
		container.setSetValue(setValue);
		
		List listValue = new ArrayList();     
        listValue.add("c");     
        listValue.add("d");   
        container.setListValue(listValue);
        
        String[] arrayValue = new String[]{"e", "f"};
        container.setArrayValue(arrayValue);
        
        Map mapValue = new HashMap();     
        mapValue.put("key_1", "value_1");     
        mapValue.put("key_2", "value_2");
        container.setMapValue(mapValue);
        
        List bagValue = new ArrayList();  
        bagValue.add("h");  
        bagValue.add("i");  
        container.setBagValue(bagValue); 
		
		HibernateUtilsOld.save(container);
		HibernateUtilsOld.close(container);
	}
}
