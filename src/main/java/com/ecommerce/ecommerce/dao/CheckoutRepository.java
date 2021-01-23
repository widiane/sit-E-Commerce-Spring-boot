package com.ecommerce.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.model.Checkout;


public interface CheckoutRepository  extends JpaRepository<Checkout, Long> {

}
