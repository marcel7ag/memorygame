package informatik.m426.as.memory.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Die Klasse erweitert JFrame und enthält mehrere Methoden und Variablen, die das Verhalten des Spiels steuern.
 * Diese Klasse ist sozusagen der Grund des Memoryspiels.
 */
public class GUIMemory extends JFrame{
    private int cardSize, colCounter, rowSize;
    private JButton[] cards;
    private ArrayList cardsValue,cardImages;
    private JPanel memory;
    private GridBagConstraints memoGrid;
    private JLabel timerLabel;
    private String defaultImage,imagePath;

    /**
     * Konstruktor für die GUIMemory-Klasse.
     * Es initialisiert das Spiel, indem es die Kartengröße, die Zeilengröße und den Spaltenzähler setzt,
     * und es initialisiert auch die Variablen cardsValue, cardImages, cards, memory, memoGrid und timerLabel.
     * Es legt auch den Standardbildpfad fest und fügt Bilder zur ArrayList cardImages hinzu.
     */
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

    /**
     * Richtet die GUI des Spiels ein.
     * Es legt das Layout des Spielbretts fest, fügt die Karten zum Brett hinzu und richtet das Timer-Label ein.
     * Es legt auch die Größe des Fensters fest und macht es nicht veränderbar.
     */
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

    /**
     * Gibt das Timer-Label des Spiels zurück.
     * @return Das Timer-Label.
     */
    public JLabel getTimerLabel(){
        return timerLabel;
    }

    /**
     * Gibt das Array von Karten zurück, die im Spiel verwendet werden.
     * @return Das Array von Karten.
     */
    public JButton[] getCards() {return cards;}

    /**
     * Gibt die ArrayList zurück, die die Werte der Karten speichert.
     * @return Die ArrayList der Kartenwerte.
     */
    public ArrayList getCardsValue() {return cardsValue;}

    /**
     * Gibt die ArrayList zurück, die die Bilder der Karten speichert.
     * @return Die ArrayList der Kartenbilder.
     */
    public ArrayList getCardImages() {return cardImages;}

    /**
     * Gibt den Standardbildpfad zurück.
     * @return Der Standardbildpfad.
     */
    public String getDefaultImage() {return defaultImage;}

    /**
     * Fügt jedem Karten ein ActionListener hinzu.
     * Der ActionListener wird als Parameter an die Methode übergeben.
     * @param listener Der ActionListener, der hinzugefügt werden soll.
     */
    public void createActionListeners(ActionListener listener) {
        for (JButton button : cards) {
            button.addActionListener(listener);
        }
    }

    /**
     * Setzt das Spiel zurück, indem es die Bilder und die Aktivierung der Karten zurücksetzt.
     * Es ruft dann die Methode gui() auf, um die GUI zu aktualisieren.
     */
    public void resetGame(){
        for (JButton button : getCards()) {
            button.setIcon(setBackgroundImage(getDefaultImage()));
            button.setEnabled(true);
        }
        gui();
    }

    /**
     * Setzt das Hintergrundbild einer Karte.
     * Es nimmt den Bildpfad als Parameter und gibt das skalierte Bildsymbol zurück.
     * @param imagePath Der Pfad des Bildes.
     * @return Das skalierte Bildsymbol.
     */
    public ImageIcon setBackgroundImage(String imagePath){
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        return scaledIcon;
    }
}
