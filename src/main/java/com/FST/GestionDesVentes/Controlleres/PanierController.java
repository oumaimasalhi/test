package com.FST.GestionDesVentes.Controlleres;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FST.GestionDesVentes.Entities.Commande;
import com.FST.GestionDesVentes.Entities.Panier;
import com.FST.GestionDesVentes.Entities.Produit;
import com.FST.GestionDesVentes.Repositories.CommandeRepository;
import com.FST.GestionDesVentes.Repositories.PanierRepository;
import com.FST.GestionDesVentes.Repositories.ProduitRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/panier")
public class PanierController {

	   @Autowired
	   private PanierRepository panierRepository;
	   
	   @Autowired
	   private ProduitRepository produitRepository;

	   @Autowired
	    private CommandeRepository commanderepository;

	   @GetMapping("/list")
	    public List<Panier> getAllPaniers() {
	        return (List<Panier>)panierRepository.findAll();
	    }


	   @PostMapping("/add")
	   public ResponseEntity<Panier> createPanier(@RequestBody Panier panier) {
	       for (Produit produit : panier.getProduits()) {
	           Produit p = produitRepository.findById(produit.getId()).orElse(null);
	           if (p == null) {
	               return ResponseEntity.badRequest().build();
	           }
	           
	           int restQuantite = p.getStock() - panier.getQuantité();
	           if (restQuantite < 0) {
	               return ResponseEntity.badRequest().build();
	           }
	           
	           p.setStock(restQuantite);
	           p.setId(p.getId());
	           try {
	               produitRepository.save(p);
	           } catch (Error e) {
	               return ResponseEntity.badRequest().build();
	           }
	       }
	       
	       // Enregistrer le panier après avoir traité tous les produits
	       try {
	           Panier p = panierRepository.save(panier);
	           return ResponseEntity.ok(p);
	       } catch (Error e) {
	           return ResponseEntity.badRequest().build();
	       }
	   }

	
	    
	    @PutMapping("/{panierId}")
	    public Panier updatePanier(@PathVariable Long panierId, @Valid @RequestBody Panier PanierRequest) {
	        return panierRepository.findById(panierId).map(panier -> {
	           panier.setQuantité(PanierRequest.getQuantité());
	           panier.setStatut(PanierRequest.getStatut());
	      
	            return panierRepository.save(panier);
	    
	     }).orElseThrow(() -> new IllegalArgumentException("PanierId " +
	    		 panierId + " not found"));
	      }


	    /*@DeleteMapping("/{panierId}")
	    public ResponseEntity<?> deletePanier(@PathVariable Long panierId) {
	    return panierRepository.findById(panierId).map(panier-> {
	    panierRepository.delete(panier);
	    return ResponseEntity.ok().build();
	    }).orElseThrow(() -> new IllegalArgumentException("PanierId " +
	    		panierId + " not found"));
	    }*/
	    
	    @DeleteMapping("/{panierId}")
	    public ResponseEntity<?> deletePanier(@PathVariable Long panierId) {
	        return panierRepository.findById(panierId).map(panier -> {
	            // Trouver les commandes associées au panier
	            List<Commande> commandes = commanderepository.findByPanierId(panierId);
	            
	            // Supprimer les commandes associées
	            commanderepository.deleteAll(commandes);
	            
	            // Supprimer le panier
	            panierRepository.delete(panier);
	            
	            return ResponseEntity.ok().build();
	        }).orElseThrow(() -> new IllegalArgumentException("PanierId " + panierId + " not found"));
	    }




 
        @GetMapping("/{panierId}")
       public Panier getPanier(@PathVariable Long panierId) {

       Optional<Panier> p = panierRepository.findById(panierId);

       return p.get();

       }
        
        
        @GetMapping("/increaseQuantity/{panierId}")
        public Panier increaseQuantity(@PathVariable Long panierId) {
            Panier p = panierRepository.findById(panierId).orElse(null);

            p.setQuantité(p.getQuantité() + 1);
            p.setId(panierId);
            return panierRepository.save(p);

        }
     
	    
        @GetMapping("/decreaseQuantity/{panierId}")
        public Panier decreaseQuantity(@PathVariable Long panierId) {
            Panier p = panierRepository.findById(panierId).orElse(null);
            if (p.getQuantité() == 1) {
                return p;
            }
            p.setQuantité(p.getQuantité() - 1);
            p.setId(panierId);
            return panierRepository.save(p);

        }
	   
        
        @GetMapping("addProduitToPanier/{idPanier}/{idProduit}")
        public Panier addProduitToPanier(@PathVariable long idPanier , @PathVariable long idProduit){
            Panier panier = panierRepository.findById(idPanier).orElse(null);
            Produit produit = produitRepository.findById(idProduit).orElse(null);


            panier.addProduit(produit);
            panier.setId(idPanier);
            return panierRepository.save(panier);
        }

        
        @GetMapping("removeProduitFromPanier/{idPanier}/{idProduit}")
        public Panier removePanierFromPanier(@PathVariable long idPanier , @PathVariable long idProduit){
            Panier panier = panierRepository.findById(idPanier).orElse(null);
            Produit produit = produitRepository.findById(idProduit).orElse(null);

            panier.removeProduit(produit);
            panier.setId(idPanier);
            return panierRepository.save(panier);
        }
}
