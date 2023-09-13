package com.ada.locateCarProject.service;

import java.util.List;
import java.util.Optional;

public class ClientService {
    private List<Client> clients;

    public ClientService(List<Client> clients) {
        this.clients = clients;
    }

    public void addClient(Client client) {
        if (!isDuplicateClient(client)) {
            clients.add(client);
        } else {
            System.out.println("Este cliente já está cadastrado");
        }
    }

    private boolean isDuplicateClient(Client client) {
        if (client instanceof ClientPessoaFisica) {
            String cpfToCheck = ((ClientPessoaFisica) client).getCpf();
            return clients.stream()
                    .filter(c -> c instanceof ClientPessoaFisica).map(c -> (ClientPessoaFisica) c).anyMatch(pf -> pf.getCpf().equals(cpfToCheck));
        } else if (client instanceof ClientPessoaJuridica) {
            String cnpjToCheck = ((ClientPessoaJuridica) client).getCnpj();
            return clients.stream().filter(c -> c instanceof ClientPessoaJuridica).map(c -> (ClientPessoaJuridica) c).anyMatch((pj -> pj.getCnpj().equals(cnpjToCheck)));

        }
        return false;
    }

    public boolean updateClient(String name, String telephone, RentalDiscount rentalDiscount, String identifier) {
        Optional<Client> optionalClient = clients
                .stream()
                .filter(client -> client.getIdentifier().equals(identifier))
                .findFirst();
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.updateClient(name, telephone, rentalDiscount, identifier);
            return true;
        }
        return false;
    }
}
