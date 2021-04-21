package com.example.WebStore;

import javax.annotation.PostConstruct;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.WebStore.domain.Basket;
import com.example.WebStore.domain.Customer;

import com.example.WebStore.domain.Items;
import com.example.WebStore.domain.ShoppingCart;

//LOCAL HOST ADDRESS: http://localhost:8080/shop/home
//ON A SERVER: https://freshjuices.herokuapp.com/shop/items

@SpringBootApplication
public class JuiceStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(JuiceStoreApplication.class, args);
	}


}