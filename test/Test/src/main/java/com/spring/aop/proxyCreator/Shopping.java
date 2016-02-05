package com.spring.aop.proxyCreator;


/**
 * 模拟业务接口
 * @author shaojiabing
 *
 */
public interface Shopping {

	public String buySomething(String type);
	public String buyAnything(String type);
	public String sellSomething(String type);
	public String sellAnything(String type);
	
}
