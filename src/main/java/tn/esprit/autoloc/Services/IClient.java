package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Client;

import java.util.List;

public interface IClient {
    Client ajouterClient(Client client);
    void supprimerClient(long idClient);
    List<Client> recupererClients();
    Client recupererClientById(long idClient);
    Client modifierClient(long idClient, Client client);
}
