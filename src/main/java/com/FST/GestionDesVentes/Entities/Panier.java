package com.FST.GestionDesVentes.Entities;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;



@Entity
@Table(name = "t_Panier")
public class Panier {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

	@NotNull(message = "La quantité est obligatoire")
	@Min(value = 0, message = "La quantité ne peut pas être négative")
	@Column (name="Quantité")
	private int quantité;   
	
	@Pattern(regexp = "valide|non valide", message = "Le statut doit être 'valide' ou 'non valide'")
	@Column(name = "Statut")
	private String statut;

	
	@OneToMany
	private List<Produit> produits;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQuantité() {
		return quantité;
	}

	public void setQuantité(int quantité) {
		this.quantité = quantité;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public Panier() {
		super();
	}

	public Panier(
			@NotNull(message = "La quantité est obligatoire") @Min(value = 0, message = "La quantité ne peut pas être négative") int quantité,
			@Pattern(regexp = "valide|non valide", message = "Le statut doit être 'valide' ou 'non valide'") String statut,
			List<Produit> produits) {
		super();
		this.quantité = quantité;
		this.statut = statut;
		this.produits = produits;
	}

	public void addProduit(Produit produit){
		this.produits.add(produit);
	}

	public void removeProduit(Produit produit){
		this.produits.remove(produit);
	}
	
	



	
	
	


    
    

	

    
    
    
}