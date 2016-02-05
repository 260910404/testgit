package com.spring.aop.proxyCreator;

public class ShoppingImplB implements Shopping {

	private Customer customer;
	
	public Customer getCustomer(){
		return customer;
	}
	
	public void setCustomer(Customer customer){
		this.customer = customer;
	}
	
	public String buySomething(String type) {
		System.out.println(this.getCustomer().getName()+ " b buys "+type+" success");
		return null;
	}

	public String buyAnything(String type) {
		System.out.println(this.getCustomer().getName()+ " b buya "+type+" success");
		return null;
	}

	public String sellSomething(String type) {
		System.out.println(this.getCustomer().getName()+ " b sells "+type+" success");
		return null;
	}

	public String sellAnything(String type) {
		System.out.println(this.getCustomer().getName()+ " b sella "+type+" success");
		return null;
	}

}
