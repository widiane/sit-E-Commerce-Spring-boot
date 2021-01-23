package com.ecommerce.ecommerce.service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import com.ecommerce.ecommerce.domaine.CheckoutVo;
import com.ecommerce.ecommerce.domaine.UserVo;
import com.ecommerce.ecommerce.exception.NotEnoughProductsInStockException;
import com.ecommerce.ecommerce.model.Produit;
import com.ecommerce.ecommerce.model.User;

public interface ShoppingCartService {
	void addProduct(Produit product);

    void removeProduct(Produit product);

    Map<Produit, Integer> getProductsInCart();

    void checkout(User user) throws NotEnoughProductsInStockException; //TO:ADD

    Double getTotal();
}
