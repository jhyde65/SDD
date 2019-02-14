package controller.actions.gameactions;

import controller.Main;
import javax.swing.*;
import java.awt.event.ActionEvent;
import model.Sword;
import java.lang.ArrayIndexOutOfBoundsException;

public class SpaceKeyAction extends AbstractAction
{
    @Override
    public void actionPerformed(ActionEvent e) {
      try{
          //Throws an exception when sword has not been created. 
       if(Main.gameData.friendFigures.get(1) != null){
       }
      }catch(ArrayIndexOutOfBoundsException exception){
          Main.gameData.friendFigures.add(new Sword(500, 500));
      }
    }
}
