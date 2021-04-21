package com.example.WebStore.domain;


import java.util.HashMap;

public class Basket {

  private HashMap<Items,Integer> basket;

  public Basket() {
      this.basket=new HashMap<>();
  }

  public HashMap<Items, Integer> getBasket() {
      return basket;
  }

}
