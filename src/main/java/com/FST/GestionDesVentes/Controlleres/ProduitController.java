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

import com.FST.GestionDesVentes.Entities.Produit;
import com.FST.GestionDesVentes.Repositories.ProduitRepository;

import jakarta.validation.Valid;



@RestController
@RequestMapping ({"/produits","/home*"})
@CrossOrigin (origins="*")
public class ProduitController {
	
	    @Autowired
	    private ProduitRepository produitrepository;

	   

	    @GetMapping("/list")
	    public List<Produit> getAllProduits() {
	        return (List<Produit>)produitrepository.findAll();
	    }


	    
	    @PostMapping("/add")
	    public Produit createProdouit(@RequestBody Produit produit){
	        return produitrepository.save(produit);
	    }


	    @PutMapping("/{produitId}")
	    public Produit updateProduit(@PathVariable Long produitId, @Valid @RequestBody Produit produitRequest) {
	        return produitrepository.findById(produitId).map(produit -> {
	           produit.setNom(produitRequest.getNom());
	           produit.setDescription(produitRequest.getDescription());
	           produit.setPrix(produitRequest.getPrix());
	           produit.setStock(produitRequest.getStock());
	           produit.setImage(produitRequest.getImage());
	      
	            return produitrepository.save(produit);
	    
	     }).orElseThrow(() -> new IllegalArgumentException("ProduitId " +
	    		 produitId + " not found"));
	      }


	    @DeleteMapping("/{produitId}")
	    public ResponseEntity<?> deleteProduit(@PathVariable Long produitId) {
	    return produitrepository.findById(produitId).map(produit-> {
	    produitrepository.delete(produit);
	    return ResponseEntity.ok().build();
	    }).orElseThrow(() -> new IllegalArgumentException("ProduitId " +
	    		produitId + " not found"));
	    }



    
        @GetMapping("/{produitId}")
        public Produit getProduit(@PathVariable Long produitId) {

        Optional<Produit> p = produitrepository.findById(produitId);

        return p.get();

        }
        
        
        @GetMapping("/byCategory/{categoryId}")
        public List<Produit> getProduitsByCategoryId(@PathVariable long categoryId) {
        	return produitrepository.findByCategory_Id(categoryId);
        }



}

	

	
