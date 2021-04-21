package com.example.WebStore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.WebStore.web.JuiceStoreController;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WebStoreApplicationTests {

	@Autowired
	private JuiceStoreController controller;
		
	@Test
	 public void contextLoads() throws Exception {
	 assertThat(controller).isNotNull();
	 }

}
