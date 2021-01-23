package com.ecommerce.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.ecommerce.dao.CheckoutRepository;
import com.ecommerce.ecommerce.domaine.CheckoutConverter;
import com.ecommerce.ecommerce.domaine.CheckoutVo;
import com.ecommerce.ecommerce.domaine.ProduitConverter;
import com.ecommerce.ecommerce.model.Checkout;
import com.ecommerce.ecommerce.model.Produit;


@Service("checkoutService")
@Transactional
public class CheckoutImpl implements ICheckout {

	@Autowired
	CheckoutRepository repo;
	
	@Override
	public void save(CheckoutVo checkout) {
		
	repo.save(CheckoutConverter.toBo(checkout));

	}

	@Override
	public List<CheckoutVo> getCheckouts() {
		
		 List<Checkout> list = repo.findAll()	;	

			return CheckoutConverter.toListVo(list);
	}

}
