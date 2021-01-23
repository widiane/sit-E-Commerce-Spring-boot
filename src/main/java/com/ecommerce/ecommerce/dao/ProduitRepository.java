package com.ecommerce.ecommerce.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.domaine.ProduitVo;
import com.ecommerce.ecommerce.model.Produit;


public interface ProduitRepository  extends JpaRepository<Produit, Long> {
    Optional<Produit> findById(Long idProduit);
    
    Optional<ProduitVo> findByIdProduit(Long idProduit);

}
