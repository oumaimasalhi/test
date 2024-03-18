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

import com.FST.GestionDesVentes.Entities.Facture;
import com.FST.GestionDesVentes.Repositories.FactureRepository;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/facture")
public class FactureController {

	     @Autowired
	      FactureRepository factureRepository ;

	 
	  
	    @GetMapping("/list")
	    public List<Facture> getAllFactures(){
	        return (List<Facture> )factureRepository.findAll();
	    }
	    

	    @PostMapping("/add")
	    public Facture create(@RequestBody Facture facture){
	        return factureRepository.save(facture);
	    }

	    
	    
	    @DeleteMapping("/{deleteId}")
	    public ResponseEntity<?> deleteFacture(@PathVariable Long deleteId){
	        return factureRepository.findById(deleteId).map(facture -> {
	        	factureRepository.delete(facture);
	            return ResponseEntity.ok().build();
	        }).orElseThrow(() -> new IllegalArgumentException("DeleteId" + "deleteId" + " not found"));
	    }

	    

	    @PutMapping("/{factureId}")
	    public Facture updateFacture(@PathVariable Long factureId, @Valid @RequestBody Facture FactureRequest) {
	        return factureRepository.findById(factureId).map(facture -> {
	          facture.setAdresseClient(FactureRequest.getAdresseClient());
	          facture.setDateFacture(FactureRequest.getDateFacture());
	          facture.setTotal(FactureRequest.getTotal());
	          facture.setNumFacture(FactureRequest.getNumFacture());
	            return factureRepository.save(facture);
	    
	     }).orElseThrow(() -> new IllegalArgumentException("FactureId " +
	    		 factureId + " not found"));
	      
	     }
	   

  
       @GetMapping("/{factureId}")
        public Facture getFacture(@PathVariable Long factureId) {
        Optional<Facture> f = factureRepository.findById(factureId);
        return f.get();

        }

	    
}