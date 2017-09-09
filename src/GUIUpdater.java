import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class GUIUpdater {

    //String scoreText;
    //static JLabel scr;// = new JLabel("Score:" + Score.getScore() + "    ");
    static String enteredWord = "";
    
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

    
    public synchronized static void updateScore(){
        WordApp.changeJLabel("Caught:" + Score.getCaught() + "    ", "Missed:" + Score.getMissed() + "    ", "Score:" + Score.getScore() + "    ");

    }
    
}
