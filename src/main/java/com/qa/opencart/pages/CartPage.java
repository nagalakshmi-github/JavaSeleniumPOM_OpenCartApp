package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class CartPage {
	
	private By cartBtn=By.id("cart");
	
	public void getCart() {
		System.out.println("get Cart: "+cartBtn);
	}

}
