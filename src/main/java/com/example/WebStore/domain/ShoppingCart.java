package com.example.WebStore.domain;

import java.util.List;
import java.util.*;
public class ShoppingCart {

    private String shop_name;

    private List<Items> items;

    private List<Customer> customers;

    public ShoppingCart(String shop_name) {
        this.shop_name = shop_name;
        this.customers=new ArrayList<>();
        this.items=new ArrayList<>();
    }


    public boolean add_item(Items item){
        int pos=check_item(item.getName());
        if(pos<0){
            this.items.add(item);
            return true;
        }else {
            System.out.println("Item "+item.getName()+" already exist");
        }
        return false;
    }

    public int check_item(String item){
        int i=0;
        for (Items item1:this.items
             ) {
            if(item1.getName().trim().toLowerCase().equals(item.trim().toLowerCase())){
                return i;
            }
            i++;
        }
        return -1;

    }

    public boolean add_customer(Customer customer){

        int pos=check_cust(customer.getName());
        if(pos<0){
            this.customers.add(customer);
            return true;
        }else {
            System.out.println("Customer "+customer.getName()+" already exists");
        }

        return false;
    }

    public int check_cust(String customer){

        int i=0;
        for (Customer cust:this.customers
             ) {
            if(cust.getName().toLowerCase().equals(customer.toLowerCase())){
                return i;
            }
            i++;
        }
        return -1;

    }

    public boolean add_prod_basket(String customer,String item,int quantity){

        int checkcust=check_cust(customer);
        int itemcheck=check_item(item);
        if(checkcust>=0 && itemcheck>=0){
            Customer cust=this.customers.get(checkcust);
            Items item_add=this.items.get(itemcheck);
            HashMap<Items,Integer> basket=cust.getBasket().getBasket();
            if(basket.containsKey(item_add)){
                basket.put(item_add,quantity+basket.get(item_add));
                item_add.reduce_stock(quantity);
            }else {
                basket.put(item_add,quantity);
                item_add.reduce_stock(quantity);
            }
            return true;
        }else {
            return false;
        }
    }

    public boolean remove_prod_basket(String customer,String item){

        int checkcust=check_cust(customer);
        int itemcheck=check_item(item);
        if(checkcust>=0 && itemcheck>=0){
            Customer cust=this.customers.get(checkcust);
            Items item_add=this.items.get(itemcheck);
            HashMap<Items,Integer> basket=cust.getBasket().getBasket();
            if(basket.containsKey(item_add)){
                basket.remove(item_add);
            }
            return true;
        }else {
            return false;
        }
    }

    public String getShop_name() {
        return shop_name;
    }

    public List<Items> getItems() {
        return items;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
