package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.Entities.Client;
import tn.esprit.autoloc.Repositories.ClientRepo;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientServiceImpl implements IClient {

    private final ClientRepo clientRepo;

    @Override
    @Transactional
    public Client ajouterClient(Client client) {
        validerClient(client);
        if (client.getIdClient() != null) {
            throw new IllegalArgumentException("L'identifiant doit être absent lors de la création");
        }
        return clientRepo.save(client);
    }

    @Override
    @Transactional
    public void supprimerClient(long idClient) {
        if (!clientRepo.existsById(idClient)) {
            throw new ResourceNotFoundException("Client", idClient);
        }
        clientRepo.deleteById(idClient);
    }

    @Override
    public List<Client> recupererClients() {
        return clientRepo.findAll();
    }

    @Override
    public Client recupererClientById(long idClient) {
        return clientRepo.findById(idClient)
                .orElseThrow(() -> new ResourceNotFoundException("Client", idClient));
    }

    @Override
    @Transactional
    public Client modifierClient(long idClient, Client client) {
        Client existant = recupererClientById(idClient);
        validerClient(client);
        existant.setNom(client.getNom());
        existant.setPrenom(client.getPrenom());
        existant.setEmail(client.getEmail());
        existant.setTelephone(client.getTelephone());
        existant.setNumPermis(client.getNumPermis());
        existant.setDateInscription(client.getDateInscription());
        return clientRepo.save(existant);
    }

    private void validerClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client obligatoire");
        }
        if (client.getNom() == null || client.getNom().isBlank()) {
            throw new IllegalArgumentException("Client : nom obligatoire");
        }
        if (client.getPrenom() == null || client.getPrenom().isBlank()) {
            throw new IllegalArgumentException("Client : prenom obligatoire");
        }
        if (client.getEmail() == null || client.getEmail().isBlank()) {
            throw new IllegalArgumentException("Client : email obligatoire");
        }
        if (client.getTelephone() == null || client.getTelephone().isBlank()) {
            throw new IllegalArgumentException("Client : telephone obligatoire");
        }
        if (client.getNumPermis() == null || client.getNumPermis().isBlank()) {
            throw new IllegalArgumentException("Client : numPermis obligatoire");
        }
        if (client.getDateInscription() == null) {
            throw new IllegalArgumentException("Client : dateInscription obligatoire");
        }
    }
}
