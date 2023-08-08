package panel;

import javax.swing.*;
import java.awt.*;

public class LogoPanel extends JPanel {
    private JLabel lblLogo;
    
    public LogoPanel() {
        init();
    }

    private void init() {
        setBackground(new Color(255, 247, 222));
        
        lblLogo = new JLabel();
        lblLogo.setIcon(new ImageIcon("image/logo.png"));
        
        add(lblLogo);
    }
}