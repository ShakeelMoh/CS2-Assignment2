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

    String scoreText;
    static JLabel scr;// = new JLabel("Score:" + Score.getScore() + "    ");
    
    public GUIUpdater(JLabel score){
        WordApp.changeJLabel("Score:" + Score.getScore() + "    ");
    }
    
    public GUIUpdater(){

    }

    public static void updateScore(){
        WordApp.changeJLabel("Score:" + Score.getScore() + "    ");
    }
    
}
