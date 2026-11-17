package tn.esprit.autoloc.RestController;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Client;
import tn.esprit.autoloc.Services.IClient;

import java.util.List;

@RestController
@Tag(name="Gestion Client")
@RequestMapping("/api/Client")
@RequiredArgsConstructor
public class ClientController {
    private final IClient iClient;

    @Operation(description="ajouter un client dans la base")
    @PostMapping("/AddClient")
    public Client ajouterClient(@RequestBody Client client) {
        return iClient.ajouterClient(client);
    }

    @Operation(description="récupérer tous les clients")
    @GetMapping("/GetAll")
    public List<Client> getAllClients() {
        return iClient.recupererClients();
    }

    @Operation(description="modifier un client existant (idClient dans le corps JSON)")
    @PutMapping("/updateClient")
    public Client updateClient(@RequestBody Client client) {
        return iClient.updateClient(client);
    }

    @Operation(description="récupérer un client par son identifiant")
    @GetMapping("/GetById/{id}")
    public Client getClientById(@PathVariable long id) {
        return iClient.recupererClientById(id);
    }

    @Operation(description="supprimer un client par son identifiant")
    @DeleteMapping("/deleteClient/{id}")
    public void deleteClient(@PathVariable long id) {
        iClient.supprimerClient(id);
    }
}
