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

import com.FST.GestionDesVentes.Entities.Commande;
import com.FST.GestionDesVentes.Repositories.CommandeRepository;

import jakarta.validation.Valid;







@RestController
@RequestMapping("/commandes")
@CrossOrigin (origins="*")
public class CommandeController {
	


    @Autowired
    private CommandeRepository commanderepository;

   


    @GetMapping("/list")
    public List<Commande> getAllCommandes() {
        return (List<Commande>)commanderepository.findAll();
    }



    /*@PostMapping("/add")
    public Commande createCommande(@RequestBody Commande commande){
        return commanderepository.save(commande);
    }*/
    @PostMapping("/add")
    public ResponseEntity<?> ajouterCommande(@RequestBody Commande commande) {
        if (commande.getPanier().getStatut().equalsIgnoreCase("valide")) {
            Commande nouvelleCommande = commanderepository.save(commande);
            return ResponseEntity.ok().body(nouvelleCommande);
        } else {
            return ResponseEntity.badRequest().body("Impossible d'ajouter une commande pour un panier non validé.");
        }
    }

    
    @DeleteMapping("/{commandeId}")
    public ResponseEntity<?> deleteCommande(@PathVariable Long commandeId) {
        return commanderepository.findById(commandeId).map(commande -> {
            commanderepository.delete(commande);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException("CommandeId" + "commandeId" + " not found"));
    }


    @PutMapping("/{commandeId}")
    public Commande updateCommande(@PathVariable Long commandeId, @Valid @RequestBody Commande CommandeRequest) {
        return commanderepository.findById(commandeId).map(commande -> {
            commande.setDateCommande(CommandeRequest.getDateCommande());
      
            return commanderepository.save(commande);
    
     }).orElseThrow(() -> new IllegalArgumentException("CommandeId " +
    		 commandeId + " not found"));
      }

   
    @GetMapping("/{commandeId}")
    public Commande getCommande(@PathVariable Long commandeId) {
        Optional<Commande> c = commanderepository.findById(commandeId);
        return c.get();

    }
	
	
	
    
  
}