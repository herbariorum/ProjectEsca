package org.esca.app.auth;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TitlePanel extends JPanel {
    public JButton btnClose;
    public TitlePanel(){
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(Color.BLUE);
        btnClose = new JButton();
        btnClose.putClientProperty("JButton.buttonType", "roundRect");
        btnClose.setIcon(new ImageIcon(getClass().getResource("/images/Fechar.png")));

        JLabel title = new JLabel("TOP PANEL");
        title.setForeground(Color.WHITE);
        add(title);
        add(btnClose);
    }



}
