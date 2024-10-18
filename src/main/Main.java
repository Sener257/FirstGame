package main;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame();  //Ersellt ein Fenster
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Um den Fenster schließen zu können
        window.setTitle("2D Adventure"); //Name wird vergeben

        GamePanel gamePanel = new GamePanel(); //neues object GamePanel erstellt
        window.add(gamePanel); //"GamePanel" wird zu "window" hinzugefügt

        window.pack(); //größe eines fensters wird automatisch angepasst

        window.setLocationRelativeTo(null);  //Plaziert Fenster in die Mitte des Bildschirmes
        window.setVisible(true);


    }
}
