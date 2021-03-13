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

    public void setMoney(int sum){
        if (sum > client.getSum()) {
            System.out.println("Error, your money is not enough.");
        }else {
            this.client.setSum(this.client.getSum() - sum);
            try {
                int total = client.getSum() - sum;
                String query = "update clients set cash = '" + total + "' where client_id = " + client.getClientId();
                statement = connection.createStatement();
                statement.executeUpdate(query);
            }
            catch (Exception e){
                System.out.println(e);
            }
            System.out.println("Done successfully");
        }
    }

    public void getMoney(int sum){
        try {
            int total = client.getSum() + sum;
            String query = "update clients set cash = '" + total + "' where client_id = " + client.getClientId();
            statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println(e);
        }
        this.client.setSum(this.client.getSum() + sum);
        System.out.println("Done successfully");
    }

    public void printInfo() {
        System.out.println("Information about your account \n" +
                "Card number: " + client.getCardNum()
        + "\nSum in your card: "+ client.getSum());
    }
}

