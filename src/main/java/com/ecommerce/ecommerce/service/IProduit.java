package com.ecommerce.ecommerce.service;


import java.util.List;
import java.util.Optional;

import com.ecommerce.ecommerce.domaine.ProduitVo;
import com.ecommerce.ecommerce.model.Produit;

public interface IProduit  {
	List<ProduitVo> getProduit();
	void save(ProduitVo produit);
	ProduitVo getProduitById(Long id);
	void delete(Long id);
	List<ProduitVo> findBydesignation(String designation);
	List<ProduitVo> findAll(int pageId, int size);

	Optional<Produit> findById(Long idProduit);
	
	Optional<ProduitVo> findByIdProduit(Long idProduit);
}
