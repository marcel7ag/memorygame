package informatik.m426.as.memory;
import informatik.m426.as.memory.gui.GUIMemory;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main {
    public static int seconds = 0;
    public static void main(String[] args) {
        GUIMemory memoryGame = new GUIMemory();
        Memory memory = new Memory(memoryGame);

        memoryGame.gui();
        memoryGame.getTimerLabel().setText("Zeit: 00:00");
        int delay = 1000; // delay in milliseconds
        Timer timer = new Timer(delay, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                memoryGame.getTimerLabel().setText(String.format("Zeit: %02d:%02d", seconds / 60, seconds % 60));
            }
        });
        ActionListener cards = new ActionListener() {
            int clickCount = 0;
            JButton firstButton;
            JButton secondButton;
            @Override
            public void actionPerformed(ActionEvent e) {

                if (clickCount == 0){
                    if (!timer.isRunning()){
                        timer.restart();
                    }
                    firstButton = (JButton) e.getSource();
                    memory.showValue(firstButton);
                    clickCount++;
                } else if (clickCount == 1) {

                    secondButton = (JButton) e.getSource();
                    memory.showValue(secondButton);
                    clickCount++;
                } else if (clickCount == 2){
                        if (!firstButton.equals(secondButton) && !secondButton.equals(firstButton)){
                            if (firstButton.getText().equals(secondButton.getText())){
                                firstButton.setEnabled(false);
                                secondButton.setEnabled(false);
                            } else {
                                memory.hideValue(firstButton, secondButton);
                            }
                            clickCount = 0;
                        } else {
                            clickCount = 1;
                        }
                    }
                if (memory.checkWin()){
                    timer.stop();
                    int result = JOptionPane.showOptionDialog(memoryGame, "Du hast gewonnen!", "Glückwunsch", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE, null, null, null);

                    if (result == JOptionPane.OK_OPTION ||result == JOptionPane.CLOSED_OPTION) {
                        clickCount = 0;
                        memoryGame.resetGame();
                        seconds = 0;
                        memoryGame.getTimerLabel().setText(String.format("Zeit: %02d:%02d", seconds / 60, seconds % 60));
                    }

                }
            }
        };
        memoryGame.createActionListeners(cards);
    }
}