package com.FST.GestionDesVentes.Entities;

import java.time.LocalDateTime;
import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "t_Facture")
public class Facture {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  
	  private long numFacture ;
	  
	 @NotNull(message = "La date est obligatoire")
     @Column(name = "Date")
      private LocalDateTime dateFacture ;
	
	  
	  @NotBlank(message = "L'adresse est obligatoire")
	  @Column (name="AdresseClient")
	  private String adresseClient;
	  

	  @NotNull(message = "Le total est obligatoire")
	  @Column (name="Total")
	  private Double total;


	  @OneToMany
	  private List<Paiement> payementType;


		public Facture(long numFacture, @NotNull(message = "La date est obligatoire") LocalDateTime dateFacture,
			@NotBlank(message = "L'adresse est obligatoire") String adresseClient,
			@NotNull(message = "Le total est obligatoire") Double total, List<Paiement> payementType) {
		super();
		this.numFacture = numFacture;
		this.dateFacture = dateFacture;
		this.adresseClient = adresseClient;
		this.total = total;
		this.payementType = payementType;
	}

		public Facture() {
			super();
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public long getNumFacture() {
			return numFacture;
		}


		public void setNumFacture(long numFacture) {
			this.numFacture = numFacture;
		}

		public LocalDateTime getDateFacture() {
			return dateFacture;
		}

		public void setDateFacture(LocalDateTime dateFacture) {
			this.dateFacture = dateFacture;
		}

		public String getAdresseClient() {
			return adresseClient;
		}

		public void setAdresseClient(String adresseClient) {
			this.adresseClient = adresseClient;
		}

		public Double getTotal() {
			return total;
		}


		public void setTotal(Double total) {
			this.total = total;
		}


		public List<Paiement> getPayementType() {
			return payementType;
		}

		public void setPayementType(List<Paiement> payementType) {
			this.payementType = payementType;
		}
		
		
		
		
}
