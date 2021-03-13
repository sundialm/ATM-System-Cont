package kz.iitu.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
@Component
public class Bank{

    @Autowired
    private List<Client> clients;
    public Client client;
    Connection connection = null;
    Statement statement = null;

    public Bank() {
    }

    public Bank(List<Client> accounts) {
        this.clients = accounts;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setMoney(int cash){
        if (cash > client.getSum()) {
            System.out.println("Извини, у тебя недостаточно денег.");
            System.out.println("Средства на вашем счете: " + this.client.getSum());
        }
        else {
            this.client.setSum(this.client.getSum() - cash);
            try {
                int total = client.getSum() - cash;
                String query = "update clients set cash = '" + total + "' where client_id = " + client.getClientId();
                statement = connection.createStatement();
                statement.executeUpdate(query);
            }
            catch (Exception e){
                System.out.println(e);
            }
            System.out.println("Операция прошла успешно\n"+
                    "Доступные средства на вашем счете: "+ client.getSum());
        }
    }

    public void getMoney(int cash)
    {
        try {
            int total = client.getSum() + cash;
            String query = "update clients set cash = '" + total + "' where client_id = " + client.getClientId();
            statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println(e);
        }
        this.client.setSum(this.client.getSum() + cash);
        System.out.println("Операция прошла успешно\n"+
                "Доступные средства на вашем счете: "+ client.getSum());
    }

    public void printInfo()
    {
        System.out.println("Текущее состояние счёта");
        System.out.println("Номер счета: "+ client.getCardNum());
        System.out.println("Колчество средств на счёте: "+ client.getSum());
    }

}

