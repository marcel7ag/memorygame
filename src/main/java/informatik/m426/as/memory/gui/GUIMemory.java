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
    private ArrayList cardsValue,cardImages;
    private JPanel memory;
    private GridBagConstraints memoGrid;
    private JLabel timerLabel;
    private String defaultImage,imagePath;
    public GUIMemory(){
        super("Memory Game");
        setLayout(new BorderLayout());

        cardSize = 16;
        rowSize = cardSize/4;
        colCounter = 0;
        cardsValue = new ArrayList<>();

        imagePath = "C:\\BENEDICT\\IT2b\\M426\\Projekt4_MemoryGame\\memory\\memory\\src\\main\\resources\\imges";
        defaultImage = imagePath+"\\default\\0.gif";

        cardImages = new ArrayList<String>();
        cardImages.add(imagePath+"/0.gif");
        cardImages.add(imagePath+"/1.gif");
        cardImages.add(imagePath+"/2.gif");
        cardImages.add(imagePath+"/3.gif");
        cardImages.add(imagePath+"/4.gif");
        cardImages.add(imagePath+"/5.gif");
        cardImages.add(imagePath+"/6.gif");
        cardImages.add(imagePath+"/7.gif");

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
        timerLabel = new JLabel("Zeit: 00:00");
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

            cards[i].setIcon(setBackgroundImage(defaultImage));

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
    public JLabel getTimerLabel(){
        return timerLabel;
    }
    public JButton[] getCards() {return cards;}
    public ArrayList getCardsValue() {return cardsValue;}
    public ArrayList getCardImages() {return cardImages;}
    public String getDefaultImage() {return defaultImage;}

    public void createActionListeners(ActionListener listener) {
        for (JButton button : cards) {
            button.addActionListener(listener);
        }
    }
    public void resetGame(){
        for (JButton button : getCards()) {
            button.setIcon(setBackgroundImage(getDefaultImage()));
            button.setEnabled(true);
        }
        gui();
    }
    public ImageIcon setBackgroundImage(String imagePath){
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        return scaledIcon;
    }
}
