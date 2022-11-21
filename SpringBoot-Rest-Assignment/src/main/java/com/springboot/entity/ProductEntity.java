package com.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class ProductEntity {

	public ProductEntity() {
		
	}

	public ProductEntity(Long id, String name, Integer quantity, Double price) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
//		this.lastupdate = lastupdate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
    
	@Column(name = "name")
    private String name;
	
    @Column(name = "quantity")
    private Integer quantity;
    
    @Column(name = "totalprice")
    private Double price;

//	@Column(name = "lastupdatedate")
//	private String lastupdate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

//	public String getLastupdate() {
//		return lastupdate;
//	}
//
//	public void setLastupdate(String lastupdate) {
//		this.lastupdate = lastupdate;
//	}
}
