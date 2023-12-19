package com.jotade.Views;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

@SuppressWarnings("resource")
public abstract class MenuImpl {
    protected final Connection CONN;

    public MenuImpl(Connection conn) {
        this.CONN = conn;
    }

    public void start() {
        int option = -1;
        while (option != 0) {
            show();
            option = getOption();
            parseOption(option);
        }
    }

    public int getOption() {
        Scanner reader = new Scanner(System.in);
        try {
            return reader.nextInt();

        } catch (InputMismatchException e) {
            return -1;
        }
    }

    public abstract void parseOption(int option);

    public abstract void show();
}
