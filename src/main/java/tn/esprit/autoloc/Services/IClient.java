package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Agence;
import tn.esprit.autoloc.Entities.Client;

import java.util.List;
import java.util.Set;

public interface IClient {
    Client ajouterClient(Client client);
    void supprimerClient(long idClient);
    List<Client> recupererClients();
    Client recupererClientById(long idClient);

}
