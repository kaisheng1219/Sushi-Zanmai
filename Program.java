import java.awt.*;

import javax.swing.*;

import panel.*;

public class Program {
    private static LogoPanel logo;
    private static MainPanel content;

    private static void createAndShowGUI() {
        logo = new LogoPanel();
        content = new MainPanel();

        JFrame frame = new JFrame("Sushi Zanmai");
        frame.setResizable(false);
        frame.setBackground(new Color(255, 247, 222));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1180, 1080);
        frame.add(logo, BorderLayout.NORTH);
        frame.add(content, BorderLayout.CENTER);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        createAndShowGUI();
    }
}