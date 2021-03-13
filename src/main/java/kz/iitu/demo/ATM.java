package kz.iitu.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.Scanner;
@Component("Service")
public  class ATM implements AccountService {
    
    public Client client;
    public Bank bank;
    Connection connection = null;
    Statement statement = null;
    Scanner scan = new Scanner(System.in);
    @Autowired
    public ATM(Bank bank) {
        this.bank = bank;
    }

    public ATM() {

    }

    @Override
    public void createPin(Client client){
        this.client = client;
        System.out.println("Генерированный счет клиента: "+ client.getCard_num());
        if (true){
            int length = client.getCard_num().length();
            if (length == 6){
                if (client != null){
                    while (true) {
                        System.out.println("1 Пополнить баланс \n2 Снять \n3 Посмотреть");
                        int num = scan.nextInt();
                        switch (num) {
                            case (1):
                                System.out.println("На сколько вы хотите пополнить?");
                                int getmoney = scan.nextInt();
                                getMoney(getmoney);
                                break;
                            case (2):
                                System.out.println("На сколько вы хотите снять?");
                                int setmoney = scan.nextInt();
                                setMoney(setmoney);
                                break;
                            case (3):
                                printInfo();
                                break;
                        }
                    }
                }
            }else{
                System.out.println("Вы вели не правильный пин должен быть ровно 6 значений");
            }
        }

    }

    @Override
    public Bank getBank() {
        return this.bank;
    }

    public void setMoney(int cash)
    {
        if (cash > client.getCash()) {
            System.out.println("Извини, у тебя недостаточно денег.");
            System.out.println("Средства на вашем счете: " + this.client.getCash());
        }
        else {
            this.client.setCash(this.client.getCash() - cash);
            try {
                int total = client.getCash() - cash;
                String query = "update clients set cash = '" + total + "' where client_id = " + client.getClient_id();
                statement = connection.createStatement();
                statement.executeUpdate(query);
            }
            catch (Exception e){
                System.out.println(e);
            }
            System.out.println("Операция прошла успешно\n"+
                    "Доступные средства на вашем счете: "+ client.getCash());
        }
    }

    public void getMoney(int cash)
    {
        try {
            int total = client.getCash() + cash;
            String query = "update clients set cash = '" + total + "' where client_id = " + client.getClient_id();
            statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println(e);
        }
        this.client.setCash(this.client.getCash() + cash);
        System.out.println("Операция прошла успешно\n"+
                "Доступные средства на вашем счете: "+ client.getCash());
    }



    public void printInfo()
    {
        System.out.println("Текущее состояние счёта");
        System.out.println("Номер счета: "+ client.getCard_num());
        System.out.println("Колчество средств на счёте: "+ client.getCash());
    }

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
}

