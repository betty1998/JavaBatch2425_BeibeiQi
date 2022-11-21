package com.springboot.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {


	private Long id;

	@NotNull
    private String name;

	 @Min(0)
    private Integer quantity;
	 @NotNull
    private Double price;
//	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
//	@NotNull
//	private String lastupdate;

    public Product() {
    	
    }
	public Product(Long id, String name, Integer quantity, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
//		this.lastupdate = lastupdate;
	}

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

//	public void setLastupdate(String lastupdate) {
//		this.lastupdate = lastupdate;
//	}
}

