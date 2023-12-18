package informatik.m426.as.memory;

import informatik.m426.as.memory.gui.GUIMemory;

import javax.swing.*;

public class Memory{
    private GUIMemory memory;
    public Memory(GUIMemory memory){
        this.memory = memory;
    }
    public int showValue(JButton targetButton){
        int index = 0;
        for (int i = 0; i < memory.getCards().length; i++) {
            JButton button =  memory.getCards()[i];
            if (button == targetButton) {
                index = i;
            }
        }
        memory.getCards()[index].setIcon(memory.setBackgroundImage(memory.getCardImages().get((Integer) memory.getCardsValue().get(index)).toString()));
        return (int) memory.getCardsValue().get(index);
    }
    public void hideValue(JButton button1, JButton button2){
        button1.setIcon(memory.setBackgroundImage(memory.getDefaultImage()));
        button2.setIcon(memory.setBackgroundImage(memory.getDefaultImage()));
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
