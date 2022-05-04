package ui;

import model.Batter;
import model.Pitcher;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        new StatAppUI();
        try {
            new StatApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }

    }
}
