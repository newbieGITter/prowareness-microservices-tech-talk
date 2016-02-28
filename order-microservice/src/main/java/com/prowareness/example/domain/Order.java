package com.prowareness.example.domain;

import java.util.Date;

public class Order {

	private long id;
	private long amount;
	private Date dateOrder;
	private Customer customer;
	private Product product;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(long amount, Customer customer, Product product) {
		super();
		this.amount = amount;
		this.customer = customer;
		this.product = product;
	}
	
	public Order(long amount, Date dateOrder, Customer customer, Product product) {
		super();
		this.amount = amount;
		this.dateOrder = dateOrder;
		this.customer = customer;
		this.product = product;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Date getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
