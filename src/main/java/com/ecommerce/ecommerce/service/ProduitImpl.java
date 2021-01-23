package com.ecommerce.ecommerce.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.ecommerce.dao.ProductImageRepository;
import com.ecommerce.ecommerce.dao.ProduitRepository;
import com.ecommerce.ecommerce.domaine.ProduitConverter;
import com.ecommerce.ecommerce.domaine.ProduitVo;
import com.ecommerce.ecommerce.model.Produit;





@Service("produitService")
@Transactional
public class ProduitImpl  implements IProduit{

	@Autowired
	ProduitRepository produitRepository; 
	
 ;
	@Override
	public List<ProduitVo> getProduit() {
		 List<Produit> list = produitRepository.findAll()	;	

		return ProduitConverter.toListVo(list);

	}

	@Override
	public void save(ProduitVo produit) {

		 
		produitRepository.save(ProduitConverter.toBo(produit));
	    }

	

	@Override
	public ProduitVo getProduitById(Long id) {
		boolean trouve = produitRepository.existsById(id);
		if(!trouve)
			return null;
		return ProduitConverter.toVo(produitRepository.getOne(id));
	}

	@Override
	public void delete(Long id) {

		produitRepository.deleteById(id);
	}

	@Override
	public List<ProduitVo> findBydesignation(String designation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProduitVo> findAll(int pageId, int size) {
		Page<Produit> result = produitRepository.findAll(PageRequest.of(pageId, size,Direction.ASC,"name"));
		return ProduitConverter.toListVo(result.getContent());

	
	}

	@Override
	public Optional<Produit> findById(Long idProduit) {
        return produitRepository.findById(idProduit);
	}

	@Override
	public Optional<ProduitVo> findByIdProduit(Long idProduit) {
		/*boolean trouve = produitRepository.existsById(idProduit);
		if(!trouve)
			return null;
		return Optional.ofNullable(ProduitConverter.toVo(produitRepository.getOne(idProduit)));*/
        return produitRepository.findByIdProduit( idProduit);
		
		

	}
	
	  
	  
	  



	
	
}