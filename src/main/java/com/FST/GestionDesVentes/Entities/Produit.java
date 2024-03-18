package com.FST.GestionDesVentes.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity	
@Table (name = "t_Produit")
public class Produit {

	@Id
	@GeneratedValue( strategy= GenerationType.AUTO)
	private long id ;
	
	@NotBlank(message = "Le nom est obligatoire")
	@Column (name="Nom")
	private String nom;
	
	@Column (name="Description")
	private String description;
	
	@NotNull(message = "Le prix est obligatoire")
	@Column (name="Prix")
	private Double prix ;
	
	@NotNull(message = "Le stock est obligatoire")
	@Column (name="Stock")
	private int stock ;

	@NotBlank(message = "Le chemin de l'image est obligatoire")
	@Column (name="Image")
	private String image  ;
	
	@JsonIgnore
	@ManyToOne
	private Panier panier;
	
	@ManyToOne
	private Categorie category;
	
	
	
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Double getPrix() {
		return prix;
	}



	public void setPrix(Double prix) {
		this.prix = prix;
	}



	public int getStock() {
		return stock;
	}



	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public Panier getPanier() {
		return panier;
	}



	public void setPanier(Panier panier) {
		this.panier = panier;
	}



	public Categorie getCategory() {
		return category;
	}



	public void setCategory(Categorie category) {
		this.category = category;
	}



	public Produit() {
		super();
	}



	public Produit(@NotBlank(message = "Le nom est obligatoire") String nom, String description,
			@NotNull(message = "Le prix est obligatoire") Double prix,
			@NotNull(message = "Le stock est obligatoire") int stock,
			@NotBlank(message = "Le chemin de l'image est obligatoire") String image, Panier panier,
			Categorie category) {
		super();
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.stock = stock;
		this.image = image;
		this.panier = panier;
		this.category = category;
	}
	
}
