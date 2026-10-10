package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Entities.Client;
import tn.esprit.autoloc.Repositories.ClientRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class ClientServiceImpl implements IClient{

    private final ClientRepo clientRepo;
    @Override
    public Client ajouterClient(Client client) {
        return clientRepo.save(client);
    }

    @Override
    public void supprimerClient(long idClient) {
         clientRepo.deleteById(idClient);
    }

    @Override
    public List<Client> recupererClients() {
        return clientRepo.findAll();
    }

    @Override
    public Client recupererClientById(long idClient) {
        return clientRepo.findById(idClient).orElseThrow();
    }

    @Override
    public Client updateClient(Client client) {
        return clientRepo.save(client);
    }
}
