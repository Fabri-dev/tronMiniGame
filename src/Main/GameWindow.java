package Main;

import javax.swing.*;

public class GameWindow extends JFrame {


    public GameWindow(GamePanel gamePanel){
        JFrame jframe = new JFrame();

        jframe.setResizable(false);
        jframe.setTitle("Tron Game");
        jframe.add(gamePanel);

        jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setVisible(true);

    }
}
