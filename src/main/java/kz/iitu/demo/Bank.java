package kz.iitu.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Bank{
    @Autowired
    private List<Client> clients;

    public Bank() {
    }

    public Bank(List<Client> accounts) {
        this.clients = accounts;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void addAccount(Client client){
        this.clients.add(client);
    }
}

