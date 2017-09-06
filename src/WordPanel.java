
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

public class WordPanel extends JPanel implements Runnable {

    public static volatile boolean done;
    private WordRecord[] words;
    private int noWords;
    private int maxY;
    private int xPos;
    private boolean gameOver = false;
    private Score s;
    private WordApp wa = new WordApp();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
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

        for (int i = 0; i < noWords; i++) {
            //g.drawString(words[i].getWord(),words[i].getX(),words[i].getY());	
            g.drawString(words[i].getWord(), words[i].getX(), words[i].getY() + 20);  //y-offset for skeleton so that you can see the words	
        }

        //System.out.println("y value is " + words[0].getY());
        // g.drawString(words[0].getWord(), xPos, words[0].getY() + 20);
    }

    WordPanel(WordRecord[] words, int maxY) {
        this.words = words; //will this work?
        noWords = words.length;
        done = false;
        this.maxY = maxY;
        s = new Score();

    }

    WordPanel(WordRecord[] words, int maxY, int xPos) {
        this.words = words; //will this work?
        noWords = words.length;
        done = false;
        this.maxY = maxY;
        this.xPos = xPos;
    }

    public void run() {
        //add in code to animate this;
        while (!gameOver) {

            for (int i = 0; i < noWords; i++) {
                //while (!words[i].dropped()){
                words[i].drop(20);
                //System.out.println(words[i].getY());
                try {
                    Thread.sleep(100);//words[i].getSpeed());
                    this.repaint();
                } catch (InterruptedException ex) {
                    Logger.getLogger(WordPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (words[i].dropped()){
                    words[i].resetWord();
                    s.missedWord();
                    
                    
                }
                //}
            }

        }

        /*
        for (int i = 0; i < noWords; i++) {

            while (!words[i].dropped()) {
                words[i].drop(10);
                
                words[i].setX(xPos);
                //System.out.println("here");
                try {
                    Thread.sleep(words[i].getSpeed());
                } catch (InterruptedException ex) {
                    Logger.getLogger(WordPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("repainting...");
                repaint();

            }
            System.out.println("out");

        }
         */
    }

}
