package org.esca.app.auth;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthForm extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    public AuthForm() {
        initComponents();
    }
    private void initComponents(){
        setTitle("Login Form");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        Container content = getContentPane();

        TitlePanel titleBar = new TitlePanel();
        titleBar.btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        content.add(titleBar, BorderLayout.NORTH);
        content.add(new Formulario(), BorderLayout.WEST);
        content.add(new Logotipo(), BorderLayout.EAST);
        setSize(new java.awt.Dimension(766, 580));
        setLocationRelativeTo(null);
    }
}
