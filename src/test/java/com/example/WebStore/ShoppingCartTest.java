package com.example.WebStore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.WebStore.domain.Basket;
import com.example.WebStore.domain.Customer;
import com.example.WebStore.domain.Items;
import com.example.WebStore.domain.ShoppingCart;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ShoppingCartTest {

	
	ShoppingCart shoppingCart = new ShoppingCart("Juice shop");
	
	
	public ShoppingCart shoppingcart;
	
	
	public Customer customer;
	
	
	public Items item;
	
	
	public Basket basket;
	
	@Test
	public void addItem() {
		shoppingcart.add_item(item);
		//assertThat(item.getName());
	}
			
}
