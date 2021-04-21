package com.example.WebStore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Items {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ItemID;

    private String name;

    private Double price;

    private int stock;

    public Items(){

    }

    public Items(String name, Double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void add_stock(int add){
        this.stock+=add;
    }

    public void reduce_stock(int add){
        this.stock-=add;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

	public Long getItemID() {
		return ItemID;
	}

	public void setItemID(Long itemID) {
		ItemID = itemID;
	}

	@Override
	public String toString() {
		return "Items [ItemID=" + ItemID + ", name=" + name + ", price=" + price + ", stock=" + stock + "]";
	}
    
    
}
