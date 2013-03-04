package com.pugwoo.hibernate.utils;

import java.util.List;

import com.pugwoo.hibernate.view.View;
import com.pugwoo.hibernate.view.cmp.Columns;

public class ViewUtils {

	/**
	 * 打印view返回的list数据
	 */
	@SuppressWarnings("unchecked")
	public static void print(View view, List data){
		Columns columns = view.getColumns();
		for(int i=0; i<columns.size(); i++){
			System.out.print(columns.getAlias(i) + "\t");
		}
		System.out.println();
		for(int i=0; i<columns.size(); i++)
		    System.out.print("========");
		System.out.println();
		
		print(data);
	}
	
	/**
	 * 打印view返回的list数据
	 */
	@SuppressWarnings("unchecked")
	public static void print(List data){
		if(data == null || data.size() == 0){
			System.out.println("No data.");
			return;
		}
		for(int i=0; i<data.size(); i++){
			Object[] objs = (Object[]) data.get(i);
			for(int j=0; j<objs.length; j++)
				System.out.print(objs[j] + "\t");
			System.out.println();
		}
	}
}
