package org.megam.deccanplato.provider.crm.info;

public  class Person{
	private String name;
	public Person(String temp){
		this.name=temp;
	}
	public String getName(){
		return name;
	}
	public String toString(){
		return "My Name is:"+getName();
	}
}
