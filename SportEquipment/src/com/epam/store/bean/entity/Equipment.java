package com.epam.store.bean.entity;

import java.io.Serializable;

public class Equipment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String category;
	private String title;
	private int price;
	private int id;
	private int quantity;
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + id;
		result = prime * result + price;
		result = prime * result + quantity;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() == obj.getClass())
			return true;
		Equipment equip = (Equipment) obj;
		if (category == null) {
			if (equip.category != null)
				return false;
		} else if (!category.equals(equip.category))
			return false;
		if (id != equip.id)
			return false;
		if (price != equip.price)
			return false;
		if (quantity != equip.quantity)
			return false;
		if (title == null) {
			if (equip.title != null)
				return false;
		} else if (!title.equals(equip.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Equipment [category=" + category + ", title=" + title + ", price=" + price + ", id=" + id
				+ ", quantity=" + quantity + "]";
	}

}
