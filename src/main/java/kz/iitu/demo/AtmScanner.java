package kz.iitu.demo;

import java.util.Scanner;

public class AtmScanner {
    private static Scanner sc;

    public static Scanner getInstance(){
        if(sc == null)
            sc = new Scanner(System.in);
        return sc;
    }
}
