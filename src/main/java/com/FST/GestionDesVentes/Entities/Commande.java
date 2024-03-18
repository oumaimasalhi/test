package com.FST.GestionDesVentes.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table (name = "t_Commande")
public class Commande {

	@Id
	@GeneratedValue( strategy= GenerationType.AUTO)
	private long id ;
	
	private LocalDateTime  dateCommande;

	

	@OneToOne
	private Panier panier;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



	public LocalDateTime getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(LocalDateTime dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}
	

	public Commande(LocalDateTime dateCommande,  Panier panier) {
		super();
		this.dateCommande = dateCommande;
		this.panier = panier;
	}

	public Commande() {
		super();
	}
	
	
}
