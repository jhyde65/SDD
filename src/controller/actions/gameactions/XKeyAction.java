package controller.actions.gameactions;

import controller.Main;
import javax.swing.*;
import java.awt.event.ActionEvent;
import model.Soldier;
import model.Sword;

public class XKeyAction extends AbstractAction
{
    @Override
    public void actionPerformed(ActionEvent e) {
      try{
          //Throws an exception when sword has not been created. 
       if(Main.gameData.friendFigures.get(2) != null){
       }
      }catch(ArrayIndexOutOfBoundsException exception){
          Main.gameData.friendFigures.add(new Soldier(800, 500));
      }
    }
}
