package com.example.WebStore.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;

public class Basket {

  private HashMap<Items,Integer> basket;

  public Basket() {
      this.basket=new HashMap<>();
  }

  public HashMap<Items, Integer> getBasket() {
      return basket;
  }

}
