package com.FST.GestionDesVentes.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FST.GestionDesVentes.Entities.Paiement;



@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long>{

}
