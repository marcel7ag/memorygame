package informatik.m426.as.memory.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.Timer;

public class GUIMemory extends JFrame{
    private int cardSize, colCounter, rowSize;
    private JButton[] cards;
    private ArrayList cardsValue;
    private JPanel memory;
    private GridBagConstraints memoGrid;
    private JLabel timerLabel;
    public GUIMemory(){
        super("Memory Game");
        setLayout(new BorderLayout());

        cardSize = 16;
        rowSize = cardSize/4;
        colCounter = 0;
        cardsValue = new ArrayList<>();
        cards = new JButton[cardSize];

        for (int i = 0; i < (cardSize/2); i++){
            cardsValue.add(i);
            cardsValue.add(i);
        }
        Collections.shuffle(cardsValue);

        for (int i = 0; i < cardsValue.size(); i++){
            cards[i] = new JButton();
        }
        memoGrid = new GridBagConstraints();
        memory = new JPanel(new GridBagLayout());
        timerLabel = new JLabel();
    }

    public void gui(){
        memoGrid.weightx = 1;
        memoGrid.weighty = 1;
        memoGrid.insets = new Insets(10,10,10,10);
        memoGrid.fill = GridBagConstraints.HORIZONTAL;

        memoGrid.gridwidth = 1;
        memoGrid.gridx = 0;
        memoGrid.gridy = 0;

        for (int i = 0; i < cardSize; i++){
            if(colCounter == (cardSize/rowSize)){
                memoGrid.gridx +=1;
                colCounter = 0;
            }
            cards[i].setPreferredSize(new Dimension(0, 150));
            cards[i].setFont(new Font("Arial", Font.PLAIN, 24));
            cards[i].setBackground(Color.lightGray);
            memoGrid.gridy = colCounter;
            memory.add(cards[i], memoGrid);
            colCounter++;
        }
        add(memory, BorderLayout.CENTER);
        add(timerLabel, BorderLayout.NORTH);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        setSize(800, 800);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public JLabel getTimerLabel() {
        return timerLabel;
    }
    public JButton[] getCards() {return cards;}
    public ArrayList getCardsValue() {return cardsValue;}
    public void createActionListeners(ActionListener listener) {
        for (JButton button : cards) {
            button.addActionListener(listener);
        }
    }
    public void resetGame(){
        for (JButton button : getCards()) {
            button.setText("");
            button.setEnabled(true);
        }
        gui();
    }
}
