package com.FST.GestionDesVentes.Entities;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;





@Entity
@Table(name = "t_Paiement")
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    
    @NotNull(message = "La méthode de paiement est obligatoire")
    @Enumerated(EnumType.STRING)
    @Column(name = "paiementType")
    private PaiementType paiementType;

  

	@OneToOne
    private Commande commande ;
    
    @JsonIgnore
    @ManyToOne
    private Facture facture;

    
    public Paiement() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



	public PaiementType getPaiementType() {
		return paiementType;
	}

	public void setPaiementType(PaiementType paiementType) {
		this.paiementType = paiementType;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}


	public Paiement(LocalDateTime dateDePayment,
			@NotNull(message = "La méthode de paiement est obligatoire") PaiementType paiementType, Commande commande,
			Facture facture) {
		super();
		this.paiementType = paiementType;
		this.commande = commande;
		this.facture = facture;
	}

	  
   

  
}

  

