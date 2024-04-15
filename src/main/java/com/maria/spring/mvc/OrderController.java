package com.maria.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class OrderController { //controller in Spring MVC.Handles the submission in the form and computation
	
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String submitOrder(@ModelAttribute("order") Order order, Model model) {
        // Perform computations on the order
        double phonePrice = getOrderPrice(order.getPhoneBrand(), order.getPhoneModel());
        double total = phonePrice * order.getQuantity();

        // Update the order with computed values
        order.setPhonePrice(phonePrice);

        // Add order data and computed values to the model for the view
        model.addAttribute("order", order);
        model.addAttribute("total", total);

        // Return the view name for "show-order.html"
        return "show-order";
    }
    
    @GetMapping("/order")
    public String getOrder() {
        // Logic for displaying the Smart Phone Online Request form
        return "Placeorder"; // "Placeorder" is the name of my order request form
    }
    
    // Sample method to get the price based on the selected brand and model
    private double getOrderPrice(String brand, String model) {
        
        double price = 0.0;
        if ("iPhone".equalsIgnoreCase(brand)) {
            if ("iPhone XR".equalsIgnoreCase(model)) {
                price = 979.00;
             // Add more cases for other iPhone models
            } else if ("iPhone 14 Pro".equalsIgnoreCase(model)) {
                price = 1099.00; // Sample price
            } else if ("iphone 15".equalsIgnoreCase(model)) {
            	price = 1200.00; //sample price
            }
            
            
         // Add more cases for other Samsung models
        }else if ("Samsung".equalsIgnoreCase(brand)) {
            if ("Samsung Galaxy 20 Plus".equalsIgnoreCase(model)) {
                price = 899.00; // Sample price
            } else if ("Samsung Galaxy Note20".equalsIgnoreCase(model)) {
                price = 799.00; // Sample price
            }
            
            } else if ("Samsung Galaxy Z Flip".equalsIgnoreCase(model)) {
            	price = 1499.00; //sample price
            
        }
        

        return price;
    }
}



