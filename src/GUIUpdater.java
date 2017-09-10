import javax.swing.JLabel;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class GUIUpdater extends Thread{

    //String scoreText;
    //static JLabel scr;// = new JLabel("Score:" + Score.getScore() + "    ");
    static String enteredWord = "";
    static int totalWords;
    static int doneWords = 0;
    static boolean isDone = false;
    static volatile boolean done;
    
            
    public GUIUpdater(JLabel score){
       //WordApp.changeJLabel("Score:" + Score.getScore() + "    ");
    }
    
    public GUIUpdater(){

    }

    public synchronized static void setEnteredWord(String word){
        enteredWord = word;
    }
    public synchronized static String getEnteredWord(){
        return enteredWord;
    }

    public static int getTotalWords() {
        return totalWords;
    }

    public static void setTotalWords(int totalWords) {
        GUIUpdater.totalWords = totalWords;
    }

    public synchronized static int getDoneWords() {
        return doneWords;
    }
    public synchronized static void resetDoneWords(){
        doneWords = 0;
    }

    public synchronized static void incDoneWords() {
        doneWords ++;
    }

    public synchronized static boolean isDone() {
        return done;
    }

    public synchronized static void setDone(boolean done) {
        GUIUpdater.done = done;
    }
    

    

    
    public synchronized static void updateScore(){
        WordApp.changeJLabel("Caught:" + Score.getCaught() + "    ", "Missed:" + Score.getMissed() + "    ", "Score:" + Score.getScore() + "    ");

    }
    
    public void run(){
        
        boolean flag = false;
        while (flag == false){
            
            if (done){
                JOptionPane.showMessageDialog(null, "Thanks for playing", "Winner!", JOptionPane.INFORMATION_MESSAGE);
                done = false;
                flag = true;
            }
            
        }
        
    }
    
}
