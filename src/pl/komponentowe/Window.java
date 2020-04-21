package pl.komponentowe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {
    public Window() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        this.setBounds((int) (width * 0.2),(int) (height * 0.2) ,(int) (width * 0.6), (int) (height * 0.6));
        this.setResizable(false);

        JButton przycisk = new JButton("No zarycz, no");
        przycisk.setBackground(Color.ORANGE);
        przycisk.setBounds((int) (width * 0.2),(int) (height * 0.2) ,(int) (width * 0.2), (int) (height * 0.2));
        przycisk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                przycisk.setBackground(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
                System.out.println("*ryczy*");

            }
        });
        this.getContentPane().add(przycisk);


    }
}
