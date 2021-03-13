package kz.iitu.demo;

public interface AccountService {
    Bank getBank();
    void createPin(Client client);
}

