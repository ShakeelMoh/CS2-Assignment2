
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

public class WordPanel extends JPanel implements Runnable {

    public static volatile boolean done;
    private static WordRecord[] words;
    WordRecord currWord;
    private int noWords;
    private static int maxY;
    private int num;
    private boolean gameOver = false;
    private Score s;
    GUIUpdater gu;

    @Override
    public void paintComponent(Graphics g) {
        //System.out.println("Threads " + xPos);
        //System.out.println("in paint ");
        int width = getWidth();
        int height = getHeight();
        g.clearRect(0, 0, width, height);
        g.setColor(Color.red);
        g.fillRect(0, maxY - 10, width, height);

        g.setColor(Color.black);
        g.setFont(new Font("Helvetica", Font.PLAIN, 26));
        //draw the words
        //animation must be added 

        //g.drawString(currWord.getWord(), currWord.getX(), currWord.getY() + 20);
        for (int i = 0; i < noWords; i++) {
            //g.drawString(words[i].getWord(),words[i].getX(),words[i].getY());	
            //System.out.println("for word " + i + " its at " + (words[i].getY() + 20));
            g.drawString(words[i].getWord(), words[i].getX(), words[i].getY() + 20);  //y-offset for skeleton so that you can see the words	
        }

        //System.out.println("y value is " + words[0].getY());
        // g.drawString(words[0].getWord(), xPos, words[0].getY() + 20);
    }

    WordPanel(WordRecord[] words, int maxY, int num) {
        this.words = words; //will this work?
        noWords = words.length;
        done = false;
        this.maxY = maxY;
        this.num = num;
        s = new Score();

    }

    WordPanel(WordRecord[] words, int maxY) {
        this.words = words; //will this work?
        noWords = words.length;
        done = false;
        this.maxY = maxY;
        s = new Score();

    }

    public void setNum(int x) {
        this.num = x;
    }

    public int getnum() {
        return num;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    
    

    public void run() {
        //add in code to animate this;
        /*
        currWord = words[0];
        System.out.println(point + " point");
        currWord.setX(xPos);
        currWord.resetWord();

        while (!currWord.dropped()) {

            //System.out.println(currWord.getY());
            currWord.drop(20);
            //System.out.println(words[i].getY());
            try {
                Thread.sleep(currWord.getSpeed());//words[i].getSpeed());
                repaint();
            } catch (InterruptedException ex) {
                Logger.getLogger(WordPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (currWord.dropped()) {
                System.out.println("happens");
                currWord.resetWord();
                s.missedWord();
                GUIUpdater.updateScore();
            }
        }
         */
        while (gameOver == false) {
            for (int i = 0; i < words.length; i++) {

                while (!words[i].dropped()) {
                    words[i].drop(10);
                    //System.out.println(Thread.currentThread().getId());
                    //System.out.println("here");
                    try {
                        Thread.sleep(words[i].getSpeed());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(WordPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    repaint();
                    break;
                }

            }

        }

    }
}
