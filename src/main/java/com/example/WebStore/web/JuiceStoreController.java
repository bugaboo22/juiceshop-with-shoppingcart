package com.example.WebStore.web;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.WebStore.domain.Basket;
import com.example.WebStore.domain.Customer;
import com.example.WebStore.domain.ShoppingCart;
import com.example.WebStore.domain.Items;

@Controller
@RequestMapping("/shop")
public class JuiceStoreController {

	ShoppingCart shoppingCart = new ShoppingCart("Juice shop");

	// ADD ITEMS AND CUSTOMERS LOCALLY
	
	@PostConstruct
	public void addcustomers() {

		shoppingCart.add_item(new Items("Apple juice", 3.5, 120));
		shoppingCart.add_item(new Items("Mango juice", 4.5, 220));
		shoppingCart.add_item(new Items("Orange juice", 2.5, 320));
		shoppingCart.add_customer(new Customer("Pekka Hämäläinen", "pekka1234@gmail.com", new Basket()));
		shoppingCart.add_customer(new Customer("Jussi Väänänen", "jussivaananen@hotmail.com", new Basket()));
		shoppingCart.add_customer(new Customer("Matti Vanhanen", "mattimies@gmail.com", new Basket())); 
	} 

	@GetMapping("/home")
	public String homepage(Model model) {

		model.addAttribute("customers", shoppingCart.getCustomers());

		return "home";
	}

	@GetMapping("/shop")
	public String cust_items(@RequestParam("customer") String name, Model model) {

		int pos = shoppingCart.check_cust(name);
		if (pos >= 0) {

			model.addAttribute("customer", name);
			Customer customer = shoppingCart.getCustomers().get(pos);
			model.addAttribute("items", customer.getBasket().getBasket().entrySet());
			return "cust_list";
		}
		return "redirect:home";
	}

	@GetMapping("/addcustomer")
	public String add_cust(Model model) {
		model.addAttribute("customer", new Customer());
		return "cust_form";
	}

	@PostMapping("/savecust")
	public String save_cust(@ModelAttribute("customer") Customer customer) {
		if (customer.getName() != null && customer.getContact() != null) {
			int pos = shoppingCart.check_cust(customer.getName());
			Customer saveCustomer = null;
			if (pos >= 0) {
				saveCustomer = shoppingCart.getCustomers().get(pos);
				saveCustomer.setName(customer.getName());
				saveCustomer.setContact(customer.getContact());
				return "redirect:home";

			} else {
				saveCustomer = customer;
				shoppingCart.add_customer(saveCustomer);
			}
		}
		return "redirect:home";
	}

	@GetMapping("/additems")
	public String add_item(Model model) {
		model.addAttribute("Item", new Items());
		return "item_form";
	}

	@GetMapping("/items")
	public String listitems(Model model) {
		model.addAttribute("Items", shoppingCart.getItems());
		return "items";
	}

	@SuppressWarnings("finally")
	@PostMapping("saveitem")
	public String save_item(@ModelAttribute("Item") Items item) {
		try {
			if ((item.getName() != null && item.getPrice() != 0) && (item.getStock() != 0)) {
				int pos = shoppingCart.check_item(item.getName());
				System.out.println(pos);
				if (pos >= 0) {
					Items items = shoppingCart.getItems().get(pos);
					items.setName(item.getName().trim());
					items.setPrice(item.getPrice());
					items.setStock(item.getStock());
					return "redirect:items";
				} else {
					shoppingCart.add_item(item);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occurs => " + e.getMessage());
		} finally {
			return "redirect:items";
		}

	}

	@PostMapping("updateitem")
	public String update_item(@RequestParam("name") String[] name, @ModelAttribute("Item") Items item) {
		if ((item.getName() != null && item.getPrice() != 0) && (item.getStock() != 0)) {
			int pos = shoppingCart.check_item(name[0]);
			if (pos >= 0) {
				Items items = shoppingCart.getItems().get(pos);
				items.setName(name[1]);
				items.setPrice(item.getPrice());
				items.setStock(item.getStock());
				return "redirect:items";
			} else {
				shoppingCart.add_item(item);
			}
		}
		return "redirect:items";
	}

	@GetMapping("/itemupd")
	public String upd_item(@RequestParam("name") String name, Model model) {

		if (name != null) {
			int pos = shoppingCart.check_item(name);
			if (pos >= 0) {
				Items item = shoppingCart.getItems().get(pos);
				model.addAttribute("Item", item);
				return "item_form_update";
			}
		}
		return "redirect:items";
	}

	@GetMapping("/itemdel")
	public String del_item(@RequestParam("name") String name) {
		if (name != null) {
			int pos = shoppingCart.check_item(name);
			if (pos >= 0) {
				shoppingCart.getItems().remove(pos);
			}
		}
		return "redirect:items";
	}

	@GetMapping("/updcust")
	public String upd_cust(@RequestParam("customer") String name, Model model) {
		if (name != null) {
			int pos = shoppingCart.check_cust(name);
			if (pos >= 0) {
				Customer customer = shoppingCart.getCustomers().get(pos);
				model.addAttribute("customer", customer);
				return "cust_update";
			}
		}
		return "home";
	}

	@PostMapping("/updatecust")
	public String savecust(@RequestParam("name") String[] name, @ModelAttribute("customer") Customer customer) {
		if (customer.getName() != null && customer.getContact() != null) {
			int pos = shoppingCart.check_cust(name[0]);
			if (pos >= 0) {
				Customer customer1 = shoppingCart.getCustomers().get(pos);
				customer1.setName(name[1]);
				customer1.setContact(customer.getContact());
				return "redirect:home";
			}
		}
		return "redirect:home";
	}

	@GetMapping("/delcust")
	public String del_cust(@RequestParam("customer") String name) {
		if (name != null) {
			int pos = shoppingCart.check_cust(name);
			if (pos >= 0) {
				shoppingCart.getCustomers().remove(pos);
			}
		}
		return "redirect:home";
	}

	@GetMapping("/additemcust")
	public String add_item_cust(@RequestParam("customer") String name, Model model) {
		if (name != null) {
			int pos = shoppingCart.check_cust(name);
			if (pos >= 0) {
				Customer customer = shoppingCart.getCustomers().get(pos);
				model.addAttribute("customer", customer);
				model.addAttribute("Items", shoppingCart.getItems());
			}
		}
		return "cust_items";
	}

	@GetMapping("/custitemupd")
	public String add_cust_item(@RequestParam("name") String item, @RequestParam("customer") String customer,
			Model model) {
		if (item != null && customer != null) {
			int pos = shoppingCart.check_cust(customer);
			int pos1 = shoppingCart.check_item(item);
			if (pos >= 0 && pos1 >= 0) {
				model.addAttribute("item", item);
				model.addAttribute("customer", customer);
			}
		}
		return "add_cust_item";
	}

	@SuppressWarnings("finally")
	@PostMapping("/savecustprod")
	public String add_cust_item(@RequestParam("item") String item, @RequestParam("customer") String customer,
			@RequestParam("quantity") String quantity, Model model) {

		if (item != null && customer != null && quantity != null) {
			try {
				int pos = shoppingCart.check_cust(customer);
				int pos1 = shoppingCart.check_item(item);
				if (pos >= 0 && pos1 >= 0) {
					shoppingCart.add_prod_basket(customer, item, Integer.valueOf(quantity));
					model.addAttribute("name", item);
					model.addAttribute("customer", customer);
				}
			} catch (Exception e) {
				System.out.println("Exception occurs => " + e.getMessage());
			} finally {
				return "redirect:shop?customer=" + customer;
			}
		}
		return "redirect:shop?customer=" + customer;
	}
	
	// REST OF ALL ITEMS
	@RequestMapping(value = "/restitems", method = RequestMethod.GET)
	public @ResponseBody List<Items> itemsRest() {
		return (List<Items>) shoppingCart.getItems();
	}
	
	// REST OF ALL CUSTOMERS
	@RequestMapping(value = "/restcustomers", method = RequestMethod.GET)
	public @ResponseBody List<Customer> customersRest() {
		return (List<Customer>) shoppingCart.getCustomers();
	}
}
