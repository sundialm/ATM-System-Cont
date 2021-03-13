package kz.iitu.demo;

import org.springframework.stereotype.Component;

@Component
public class Client {
    private int clientId;
    private int sum;
    private String cardNum;

    public Client() {
        
    }

    public Client(int client_id,  String card_num ,int cash ) {
        this.clientId = client_id;
        this.cardNum = card_num;
        this.sum = cash;

    }

    public int getClientId() {
        return clientId;
    }

    public int getSum() {
        return sum;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}

