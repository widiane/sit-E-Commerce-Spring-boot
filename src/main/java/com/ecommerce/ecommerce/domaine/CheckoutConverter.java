package com.ecommerce.ecommerce.domaine;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.ecommerce.model.Checkout;
import com.ecommerce.ecommerce.model.Produit;

public class CheckoutConverter {
	
	
	public static CheckoutVo toVo(Checkout bo) {
		if (bo == null || bo.getId()==null)
			return null;
			CheckoutVo vo = new CheckoutVo();
			vo.setId(bo.getId());
			vo.setClient(bo.getClient());
			vo.setProduit(bo.getProduit());
			vo.setQuantite(bo.getQuantite());
			vo.setTotal(bo.getTotal());
			
			return vo;
	}
	
	public static Checkout toBo(CheckoutVo vo) {
			Checkout bo = new Checkout();
			bo.setId(vo.getId());
			bo.setClient(vo.getClient());
			bo.setProduit(vo.getProduit());
			bo.setQuantite(vo.getQuantite());
			bo.setTotal(vo.getTotal());
			return bo;
	}
	
	public static List<CheckoutVo> toListVo(List<Checkout> listBo) {
		List<CheckoutVo> listVo = new ArrayList<>();
		for (Checkout checkout : listBo) {
		listVo.add(toVo(checkout));
		}
		return listVo;
		}

}
