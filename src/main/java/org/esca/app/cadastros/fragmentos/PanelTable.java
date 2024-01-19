package org.esca.app.cadastros.fragmentos;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PanelTable extends JPanel {

    public JTextField txtLocalizar;
    public JTable tabela;

    public PanelTable(){
        setLayout(null);
        setBorder(new EmptyBorder(5, 5, 5, 5));

        txtLocalizar = new JTextField();
        txtLocalizar.setBounds(10, 5, 780, 27);
        txtLocalizar.putClientProperty("JTextField.leadingIcon", new ImageIcon("/images/24 search.png"));
        txtLocalizar.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Localizar");

        tabela = new JTable();
        tabela.setBounds(10, 45, 780, 400);
        add(txtLocalizar);
        add(tabela);
    }
}
