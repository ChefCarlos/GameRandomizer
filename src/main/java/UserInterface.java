import javax.swing.*;
import java.awt.*;

public class UserInterface extends JFrame {
    public static void main(String[] args) {
        AccessDatabase access = new AccessDatabase();
        UserInterface ui = new UserInterface(access);
    }

    private AccessDatabase access;

    //Creation of user interface
    UserInterface(AccessDatabase access){
        super("Game Randomizer");
        this.access = access;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500,300);
        setLayout(new GridLayout(6,1));
        populateFrame();
    }

    //adds all elements of the UI to the jframe
    private void populateFrame(){
        JLabel title = new JLabel("Add your steamID64 below and it will choose a random game from your library",SwingConstants.CENTER);
        JTextField steamID = new JTextField();
        JButton importGames = new JButton("Import");
        JLabel blank = new JLabel();
        JLabel gameTitle = new JLabel("",SwingConstants.CENTER);
        JButton randomize = new JButton("Randomize");

        //calls  SteamAPI using the steamid user has placed into text box upon pressing import button
        importGames.addActionListener(e ->{
                new SteamAPI(steamID.getText());
                }
        );

        //calls AccessDatabase to add picked game to text box upon pressing button
        randomize.addActionListener(e ->
                gameTitle.setText(access.getRs())
        );


        add(title);
        add(steamID);
        add(importGames);
        add(blank);
        add(gameTitle);
        add(randomize);
        setVisible(true);
    }






}
