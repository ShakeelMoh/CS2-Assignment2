
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

public class WordPanel extends JPanel implements Runnable {

    
    public static volatile boolean done;
    private static WordRecord[] words;
    private static boolean[] usingWords;
    private WordRecord currWord;
    private int noWords;
    private static int maxY;
    private int num;
    private boolean gameOver = false;
    private boolean caught = false;
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

        for (int i = 0; i < noWords; i++) {

            g.drawString(words[i].getWord(), words[i].getX(), words[i].getY() - 5);  //y-offset for skeleton so that you can see the words	
        }

    }

    //additional constructor with thread number passed to it
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
        usingWords = new boolean[words.length];

    }

    public void setNum(int x) {
        this.num = x;
    }

    public int getnum() {
        return num;
    }

    //Only one thread should be able to get the current word
    public synchronized WordRecord getCurrWord() {
        return currWord;
    }

    public synchronized void setCurrWord(WordRecord currWord) {
        this.currWord = currWord;
    }
    
    
    
//Checks if the game is ver
    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    
    public void run() {
        //add in code to animate this;
        GUIUpdater.setDone(false);
        //Loop till game is over
        while (gameOver == false) {
            //System.out.println(Thread.activeCount() + " threads");
            for (int i = 0; i < words.length; i++) {
                
                //Using words determins if another thread is moving a word so
                //another thread doesnt do the same
                while (!words[i].dropped() && usingWords[i] == false) {
                    usingWords[i] = true;
                    words[i].drop(10);
                    //System.out.println(Thread.currentThread().getId());
                    //System.out.println("here");
                    try {
                        
                        Thread.sleep(words[i].getSpeed());
                        
                        repaint();
                        usingWords[i] = false;
                        //break;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(WordPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //Check if word hit red
                    if (words[i].dropped()) {
                        s.missedWord();
                        words[i].resetWord();
                        GUIUpdater.updateScore();
                        repaint();
                        break;
                    }

                    //Check entered word
                    if (GUIUpdater.getEnteredWord().equals(words[i].getWord())) {
                        //System.out.println(words[i].getWord());
                        GUIUpdater.setEnteredWord("");
                        GUIUpdater.incDoneWords();
                        //System.out.println("CHECK: WORKED FOR WORD " + words[i].getWord());
                        caught = true;
                        s.caughtWord(words[i].getWord().length());
                        //System.out.println(words[i].getWord().length());
                        GUIUpdater.updateScore();
                        words[i].resetWord();
                        repaint();
                        caught = false;

                        break;
                        //s.reduceScore(words[i].getWord().length());
                    }

                    repaint();
                    break;
                }

            }
            //Checks if game is over
            if (GUIUpdater.getDoneWords() == GUIUpdater.getTotalWords()){
                gameOver = true;
                GUIUpdater.setDone(true);
              
            }
        }
        
        //Resets game
        for (int i = 0; i < words.length; i++) {
            words[i].setY(-10);
        }
        repaint();
    }
}
