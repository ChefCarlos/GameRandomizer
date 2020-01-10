import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class UserInterface extends JFrame {
    public static void main(String[] args) {
        //AccessDatabase access = new AccessDatabase();
        UserInterface ui = new UserInterface();

    }

    UserInterface(){
        super("Game Randomizer");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500,300);
        setLayout(new GridLayout(6,1));
        populateFrame();
    }

    private void populateFrame(){
        JLabel title = new JLabel("Add your steam profile Link below and it will choose a random game",SwingConstants.CENTER);
        JTextField steamID = new JTextField();
        JButton importGames = new JButton("Import");
        JLabel blank = new JLabel();
        JLabel gameTitle = new JLabel("",SwingConstants.CENTER);
        JButton randomize = new JButton("Randomize");
        add(title);
        add(steamID);
        add(importGames);
        add(blank);
        add(gameTitle);
        add(randomize);
        setVisible(true);
    }
}
