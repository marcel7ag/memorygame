package informatik.m426.as.memory;
import informatik.m426.as.memory.gui.GUIMemory;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Diese Klasse enthält die Hauptlogik des Memory-Spiels.
 */
public class Main {

    /**
     * Die Anzahl der Sekunden, die seit dem Start des Spiels vergangen sind.
     */
    public static int seconds = 0;

    /**
     * Die Hauptmethode des Programms.
     * Sie erstellt eine neue Instanz von GUIMemory und Memory und startet das Spiel.
     * @param args Die Argumente, die an das Programm übergeben werden.
     */
    public static void main(String[] args) {
        GUIMemory memoryGame = new GUIMemory();
        Memory memory = new Memory(memoryGame);

        //Spiel wird eingerichtet mit gui();
        memoryGame.gui();

        int delay = 1000; // delay in milliseconds
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                memoryGame.getTimerLabel().setText(String.format("Zeit: %02d:%02d", seconds / 60, seconds % 60));
            }
        });

        //ActionListener, um die Logik des Spiels zu erstellen.
        ActionListener cards = new ActionListener() {
            int clickCount = 0;
            JButton firstButton,secondButton;
            int value1, value2;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clickCount == 0){
                    if (!timer.isRunning()){
                        timer.restart();
                    }
                    firstButton = (JButton) e.getSource();
                    value1 = memory.showValue(firstButton);
                    clickCount++;
                } else if (clickCount == 1) {
                    secondButton = (JButton) e.getSource();
                    value2 = memory.showValue(secondButton);
                    clickCount++;
                } else if (clickCount == 2){
                        if (!firstButton.equals(secondButton) && !secondButton.equals(firstButton)){
                            if (value1 == value2){
                                firstButton.setEnabled(false);
                                secondButton.setEnabled(false);
                                //System.out.println("Correct");
                            } else {
                                memory.hideValue(firstButton, secondButton);
                                //System.out.println("hidden");
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

                    if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {
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