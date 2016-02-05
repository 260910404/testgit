package com.jj.web.mbean;

public class Class3 extends Class1{
	public static void main(String[] args) {
		System.out.println(Class3.class.isAssignableFrom(Class1.class));
		System.out.println("Class1��Class3�ĸ��ࣺ"+Class1.class.isAssignableFrom(Class3.class));
	}

}
