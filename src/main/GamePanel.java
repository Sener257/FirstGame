package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 blöcke
    final int scale = 3; //vergrößerung der blöcke

    final int tileSize = originalTileSize * scale; //48x48 blöcke
    final int maxScreenCol = 16; //Spalte auf 16 Blöcke
    final int maxScreenRow = 12; //Reihe auf 12 Blöcke
    final int screenWidth = tileSize * maxScreenCol; //erstellt weite auf 768 Pixel
    final int screenHeight = tileSize * maxScreenRow; //erstellt höhe auf 576 Pixel

    Thread gameThread;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //größe wird erstellt
        this.setBackground(Color.BLACK); //farbe wird ausgewählt
        this.setDoubleBuffered(true); //verhindert ruckartige Aktualisierungen

    }

    public void startGameThread() { //neue Methode "starte GameThread"

        gameThread = new Thread(this); //GameThread erstellt
        gameThread.start(); //GameThread gestartet
    }

    @Override
    public void run() { //core von unserem spiel (mit schleife)



    }
}
