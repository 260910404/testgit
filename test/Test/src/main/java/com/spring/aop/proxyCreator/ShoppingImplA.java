package com.spring.aop.proxyCreator;

public class ShoppingImplA implements Shopping {

	private Customer customer;
	
	public Customer getCustomer(){
		return customer;
	}
	
	public void setCustomer(Customer customer){
		this.customer = customer;
	}
	
	public String buySomething(String type) {
		System.out.println(this.getCustomer().getName()+ " a buys "+type+" success");
		return null;
	}

	public String buyAnything(String type) {
		System.out.println(this.getCustomer().getName()+ " a buya "+type+" success");
		return null;
	}

	public String sellSomething(String type) {
		System.out.println(this.getCustomer().getName()+ " a sells "+type+" success");
		return null;
	}

	public String sellAnything(String type) {
		System.out.println(this.getCustomer().getName()+ " a sella "+type+" success");
		return null;
	}

}
