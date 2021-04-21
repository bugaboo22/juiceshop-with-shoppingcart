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

@SpringBootApplication
public class WebStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebStoreApplication.class, args);
	}


}