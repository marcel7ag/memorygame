package informatik.m426.as.memory;

import informatik.m426.as.memory.gui.GUIMemory;

import javax.swing.*;


/**
 * Die Klasse Memory beinhaltet Methoden zur Anzeige und Überprüfung der Karten
 * und auch die Überprüfung der Siegesanforderung, sprich, ob alle Karten aufgedeckt wurden.
 */
public class Memory{
    private GUIMemory memory;

    /**
     * Konstruktor für die Memory-Klasse.
     *
     * @param memory Die GUI für das Memory-Spiel.
     */
    public Memory(GUIMemory memory){
        this.memory = memory;
    }

    /**
     * Zeigt den Wert der angeklickten Karte an.
     *
     * @param targetButton Die JButton-Instanz der angeklickten Karte.
     * @return Der Wert der angeklickten Karte.
     */
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

    /**
     * Versteckt den Wert von zwei Karten.
     *
     * @param button1 Die JButton-Instanz der ersten Karte.
     * @param button2 Die JButton-Instanz der zweiten Karte.
     */
    public void hideValue(JButton button1, JButton button2){
        button1.setIcon(memory.setBackgroundImage(memory.getDefaultImage()));
        button2.setIcon(memory.setBackgroundImage(memory.getDefaultImage()));
    }

    /**
     * Überprüft, ob das Spiel gewonnen wurde.
     * Wenn alle Karten aufgedeckt sind, dann return true.
     *
     * @return true, wenn das Spiel gewonnen wurde, sonst false.
     */
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
