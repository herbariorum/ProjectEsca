package org.esca.app.cadastros.usuarios;


import org.esca.app.cadastros.fragmentos.BarraTitulo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaUsuarios extends JDialog implements ActionListener{

    private static ListaUsuarios listaUsuarios;
    public ListaUsuarios(JFrame parent, boolean modal) {
        super(parent,  modal);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);


        initComponents();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    private void initComponents(){
        Container content = getContentPane();

        BarraTitulo bt = new BarraTitulo();
        bt.title.setText("Manutenção de Usuários");
        bt.btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        content.add(bt, BorderLayout.NORTH);

        setSize(new Dimension(800, 520));
        setLocationRelativeTo(null);
    }


    public JDialog getInstance(){
        return this;
    }

    public void start(){
        setVisible(true);
    }
}
