import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main extends JFrame implements ActionListener {

    GamePanel Cube = new GamePanel();

    public Main() {
        super("Cube");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);
        add(Cube);
//        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Cube.move();
        Cube.repaint();
    }

    public static void main(String[] args) {
        Main PacMan = new Main();
    }
}