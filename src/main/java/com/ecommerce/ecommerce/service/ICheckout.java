package com.ecommerce.ecommerce.service;

import java.util.List;

import com.ecommerce.ecommerce.domaine.CheckoutVo;
import com.ecommerce.ecommerce.domaine.ProduitVo;
import com.ecommerce.ecommerce.domaine.UserVo;

public interface ICheckout {

	void save(CheckoutVo checkout);
	List<CheckoutVo> getCheckouts();
}
