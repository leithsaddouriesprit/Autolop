package tn.esprit.autoloc.RestController;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Client;
import tn.esprit.autoloc.Services.IClient;

import java.util.List;

@RestController
@RequestMapping("/api/Client")
@RequiredArgsConstructor

public class ClientController {
    private final IClient iClient;


    @PostMapping("/AddClient")
    public Client ajouterClient(@RequestBody Client client) {
        return iClient.ajouterClient(client);
    }

    @GetMapping("/GetAll")
    public List<Client> getAllClients() {
        return iClient.recupererClients();
    }

    @PutMapping("/updateClient")
    public Client updateClient(@RequestBody Client client) {
        return iClient.updateClient(client);
    }

    @GetMapping("/GetById/{id}")
    public Client getClientById(@PathVariable long id) {
     return iClient.recupererClientById(id);
    }

    @DeleteMapping("/deleteClient/{id}")
    public void deleteClient(@PathVariable long id) {
        iClient.supprimerClient(id);
    }



}
