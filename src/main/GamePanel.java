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

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;


    //Setzt spieler position immer auf
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4; //4 Pixel



    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //größe wird erstellt
        this.setBackground(Color.BLACK); //farbe wird ausgewählt
        this.setDoubleBuffered(true); //verhindert ruckartige Aktualisierungen
        this.addKeyListener(keyH); // fügt object KeyH hinzu
        this.setFocusable(true);
    }

    public void startGameThread() { //neue Methode "starte GameThread"

        gameThread = new Thread(this); //GameThread erstellt
        gameThread.start(); //GameThread gestartet
    }

/*    @Override
    public void run() { //läd das spiel immer neu (mit schleife)

        double drawInterval = 1000000000/FPS; // bilschirm jede 0.0166666 Sekunden aktualisiert
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            //1. UPDATE: aktualisiert Informationen z.B. Spieler position
            update();

            //2. DRAW: aktualisierte Informationen werden auf dem Bildschirm aktualisiert
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
    */

    @Override
    public void run() {   //Game Loop für aktualisirung der Spieldaten

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }

    }

    public void update() {

        if(keyH.upPressed == true) {
            playerY -= playerSpeed;
            //playerY = playerY - playerSpeed
        }
        else if(keyH.downPressed == true) {
            playerY += playerSpeed;
        }
        else if(keyH.leftPressed == true) {
            playerX -= playerSpeed;
        }
        else if(keyH.rightPressed == true) {
            playerX += playerSpeed;
        }

    }
    public void paintComponent(Graphics g) { //zu wiedergeben auf dem bilschirm

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);

        g2.fillRect(playerX, playerY, tileSize, tileSize); //erstellt Rechteck

        g2.dispose();
    }
}
