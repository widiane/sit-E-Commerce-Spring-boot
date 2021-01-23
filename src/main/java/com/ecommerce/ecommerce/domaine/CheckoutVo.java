package com.ecommerce.ecommerce.domaine;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ecommerce.ecommerce.model.Client;
import com.ecommerce.ecommerce.model.Produit;
import com.ecommerce.ecommerce.model.User;

public class CheckoutVo {
	
	private Long id;
	
	private Produit produit ;
	

	private User client ;
	private int quantite ;
	private double total ;
	public CheckoutVo(Long id, Produit produit, User client, int quantite, double total) {
		super();
		this.id = id;
		this.produit = produit;
		this.client = client;
		this.quantite = quantite;
		this.total = total;
	}
	public CheckoutVo() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public User getClient() {
		return client;
	}
	public void setClient(User client) {
		this.client = client;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	

}
