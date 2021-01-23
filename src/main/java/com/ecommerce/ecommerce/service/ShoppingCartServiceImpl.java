package com.ecommerce.ecommerce.service;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.ecommerce.ecommerce.dao.CheckoutRepository;
import com.ecommerce.ecommerce.dao.ProduitRepository;
import com.ecommerce.ecommerce.domaine.CheckoutConverter;
import com.ecommerce.ecommerce.domaine.CheckoutVo;
import com.ecommerce.ecommerce.domaine.ProduitConverter;
import com.ecommerce.ecommerce.domaine.ProduitVo;
import com.ecommerce.ecommerce.domaine.UserConverter;
import com.ecommerce.ecommerce.domaine.UserVo;
import com.ecommerce.ecommerce.exception.NotEnoughProductsInStockException;
import com.ecommerce.ecommerce.model.Produit;
import com.ecommerce.ecommerce.model.User;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

import java.math.BigDecimal;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {



	private final IProduit serviceProduit;
	private final ICheckout serviceCheckout;
	
	
	private final IUser userService;
	
	private Map<Produit, Integer> products = new HashMap<>();

	@Autowired
	public ShoppingCartServiceImpl(
			IProduit serviceProduit,ICheckout serviceCheckout,IUser userService) {
		
		this.serviceProduit = serviceProduit;
		this.serviceCheckout = serviceCheckout;
		this.userService = userService;
		
	}

	/**
	 * If product is in the map just increment quantity by 1. If product is not in
	 * the map with, add it with quantity 1
	 *
	 * @param product
	 */
	@Override
	public void addProduct(Produit product) {
		if (products.containsKey(product)) {
			products.replace(product, products.get(product) + 1);
		} else {
			products.put(product, 1);
		}
	}

	/**
	 * If product is in the map with quantity > 1, just decrement quantity by 1. If
	 * product is in the map with quantity 1, remove it from map
	 *
	 * @param product
	 */
	@Override
	public void removeProduct(Produit product) {
		if (products.containsKey(product)) {
			if (products.get(product) > 1)
				products.replace(product, products.get(product) - 1);
			else if (products.get(product) == 1) {
				products.remove(product);
			}
		}
	}

	/**
	 * @return unmodifiable copy of the map
	 */
	@Override
	public Map<Produit, Integer> getProductsInCart() {
		return Collections.unmodifiableMap(products);
	}

	/**
	 * Checkout will rollback if there is not enough of some product in stock
	 *
	 * @throws NotEnoughProductsInStockException
	 */
	@Override
	public void checkout (User user) throws NotEnoughProductsInStockException {
		ProduitVo product;

		//User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//User customUser = (User)authentication.getPrincipal();
		// User user = userService.findB;
		
		for (Map.Entry<Produit, Integer> entry : products.entrySet()) {
			// Refresh quantity for every product before checking
			// product = productRepository.findOne(entry.getKey().getIdProduit());
			CheckoutVo check = new CheckoutVo();

/*
			if (principal instanceof UserDetails) {

				String userId = ((UserDetails) principal).getUsername();

			} else {

				String username = principal.toString();

			}
*/
			product = serviceProduit.getProduitById(entry.getKey().getIdProduit());
			if (product.getQuantite() < entry.getValue())
				throw new NotEnoughProductsInStockException(product);
			entry.getKey().setQuantite(product.getQuantite() - entry.getValue());

			check.setProduit(ProduitConverter.toBo(product));
			check.setQuantite(product.getQuantite());
			check.setTotal(product.getPrix());
			check.setClient(user);
			
			serviceCheckout.save(check);


		}
	

	}

	@Override
	public Double getTotal() {
	
		/* return products.entrySet().stream()
	                .map(entry -> entry.getKey().getPrix().multiply(BigDecimal.valueOf()))
	                .reduce(BigDecimal::add)
	                .orElse(BigDecimal.ZERO);*/
		return null;
		

	}

}