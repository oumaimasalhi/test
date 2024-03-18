package com.FST.GestionDesVentes.Controlleres;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FST.GestionDesVentes.Entities.Paiement;
import com.FST.GestionDesVentes.Repositories.PaiementRepository;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/paiement")
public class PaiementController {

	    @Autowired
        PaiementRepository paiementrepository ;


	    @GetMapping("/list")
	    public List<Paiement> getAllPaiements(){
	        return (List<Paiement> ) paiementrepository.findAll();
	    }

	    
	    @PostMapping("/add")
	    public Paiement create(@RequestBody Paiement paiement){
	        return paiementrepository.save(paiement);
	    }

	    
	    
	    @DeleteMapping("/{paiementId}")
	    public ResponseEntity<?> deletePaiement(@PathVariable Long paiementId) {
	        return paiementrepository.findById(paiementId).map(paiement -> {
	            paiementrepository.delete(paiement);
	            return ResponseEntity.ok().build();
	        }).orElseThrow(() -> new IllegalArgumentException("PaiementId" + "paiementId" + " not found"));
	    }

	   
	    
	    @PutMapping("/{paiementId}")
	    public Paiement updatePaiement(@PathVariable Long paiementId, @Valid @RequestBody Paiement PaiementRequest) {
	        return paiementrepository.findById(paiementId).map(paiement -> {

	            paiement.setPaiementType(PaiementRequest.getPaiementType());

	      
	            return paiementrepository.save(paiement);
	    
	     }).orElseThrow(() -> new IllegalArgumentException("PaiementId " +
	    		 paiementId + " not found"));
	      }
	    


  
      @GetMapping("/{paiementId}")
      public Paiement getPaiement(@PathVariable Long paiementId) {
        Optional<Paiement> p = paiementrepository.findById(paiementId);
        return p.get();

      }

    
}