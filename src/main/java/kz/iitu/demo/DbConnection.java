package kz.iitu.demo;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;

public class DbConnection implements AccountService{

    Connection connection = null;
    Statement statement = null;

    public Bank bank;

    public Connection createDBConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/bank",
                    "postgres",
                    "postgres");
            if (connection != null) {
                System.out.println("Коннект успешен !!!");
            }
            else {
                System.out.println("Коннект провал !!!");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return connection;
    }

    @PostConstruct
    public void init() throws SQLException {
        Connection connection = this.createDBConnection();
        ResultSet set = null;
        String query = "SELECT * FROM clients";
        statement = connection.createStatement();
        set = statement.executeQuery(query);
        while (set.next()){
            Client client = new Client(
                    set.getInt(1),
                    set.getString(2),
                    set.getInt(3));
            bank.getClients().add(client);
        }
    }
    @PreDestroy
    public void destroy() {
        try {
            connection.close();
            if (connection != null) {
                System.out.println("Соединение закрыто!!!");
            } else {
                System.out.println("Что-то пошло не так!!!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Bank getBank() {
        return this.bank;
    }

    @Override
    public void getClient(Client client) {

    }
}
