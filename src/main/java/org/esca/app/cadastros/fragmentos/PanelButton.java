package org.esca.app.cadastros.fragmentos;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PanelButton extends JPanel {

    public JButton btnNew, btnUpdate, btnDelete;

    public PanelButton() {
        setLayout(null);
        setBorder(new EmptyBorder(10, 20, 5, 5));

        btnNew = new JButton("Adicionar");
        btnNew.setName("Adicionar");
        btnNew.setIcon(new ImageIcon(getClass().getResource("/images/32 new.png")));
        btnNew.setBounds(10, 10, 130, 35);
        btnUpdate = new JButton("Atualizar");
        btnUpdate.setName("Atualizar");
        btnUpdate.setIcon(new ImageIcon(getClass().getResource("/images/32 edit.png")));
        btnUpdate.setBounds(160, 10, 130, 35);
        btnDelete = new JButton("Excluir");
        btnDelete.setName("Deletar");
        btnDelete.setIcon(new ImageIcon(getClass().getResource("/images/32 delete.png")));
        btnDelete.setBounds(305, 10 ,130, 35);

        add(btnNew);
        add(btnUpdate);
        add(btnDelete);
    }

}
