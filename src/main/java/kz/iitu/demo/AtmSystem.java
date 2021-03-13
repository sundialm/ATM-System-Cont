package kz.iitu.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("Service")
public  class AtmSystem implements AccountService {

    public Client client;
    public Bank bank;

    @Autowired
    public AtmSystem(Bank bank) {
        this.bank = bank;
    }

    @Override
    public Bank getBank() {
        return this.bank;
    }

    @Override
    public void getClient(Client client){
        this.client = client;
        System.out.println("Card number of client: "+ client.getCardNum());
        if (true){
            int length = client.getCardNum().length();
            if (length == 6){
                if (client != null){
                    while (true) {
                        System.out.println("1. To withdraw money.");
                        System.out.println("2. Check the invoice.");
                        System.out.println("3. To add money.");
                        System.out.print("Please choose one option: ");
                        int choice = AtmScanner.getInstance().nextInt();
                        switch (choice) {
                            case 1:
                                System.out.println("To withdraw money enter sum: ");
                                int setmoney = AtmScanner.getInstance().nextInt();
                                bank.setMoney(setmoney);
                                break;
                            case 2:
                                System.out.println("2. Check the invoice.");
                                bank.printInfo();
                                break;
                            case 3:
                                System.out.println("3. To add money enter sum");
                                int getmoney = AtmScanner.getInstance().nextInt();
                                bank.getMoney(getmoney);
                                break;
                        }
                    }
                }
            }else{
                System.out.println("Wrong enter");
            }
        }
    }
}

