package informatik.m426.as.memory;

import informatik.m426.as.memory.gui.GUIMemory;

import javax.swing.*;
import java.util.Objects;

public class Memory{
    private GUIMemory memory;
    public Memory(GUIMemory memory){
        this.memory = memory;
    }
    public void showValue(JButton targetButton){
        int index = 0;
        for (int i = 0; i < memory.getCards().length; i++) {
            JButton button =  memory.getCards()[i];
            if (button == targetButton) {
                index = i;
            }
        }
        memory.getCards()[index].setText(memory.getCardsValue().get(index).toString());
    }
    public void hideValue(JButton button1, JButton button2){
        button1.setText("");
        button2.setText("");
    }
    public boolean checkWin(){
        for (int i = 0; i < memory.getCards().length; i++) {
            JButton button =  memory.getCards()[i];
            if (button.isEnabled()){
                return false;
            }
        }
        return true;
    }
}