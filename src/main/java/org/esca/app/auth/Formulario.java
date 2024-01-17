package org.esca.app.auth;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Formulario extends JPanel {
    public Formulario() {
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setPreferredSize(new Dimension(383, getHeight()));
        setBackground(Color.RED);
        JLabel title = new JLabel("LEFT PANEL");
        title.setForeground(Color.WHITE);
        add(title);
    }
}
