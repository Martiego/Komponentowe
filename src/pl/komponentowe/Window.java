package pl.komponentowe;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window() {
        this.setBounds(50, 50, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
