package org.ap.lucene.poc.model;

import java.math.BigDecimal;

public class Product {
	private Integer id;
	private String name;
	private BigDecimal price;
	private String company;
	
	
	
	
	public Product(Integer id, String name, BigDecimal price, String company) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.company = company;
	}
	/** Getters and setters*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	
	
	
	
}
