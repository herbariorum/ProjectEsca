package org.esca.app;

import org.esca.app.auth.AuthForm;
import org.esca.app.auth.dominio.Usuarios;
import org.esca.app.cadastros.usuarios.ListaUsuarios;
import org.esca.app.util.StatusBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FormMenu extends JFrame {

    private final Usuarios user;
    private StatusBar statusBar;
    private JMenuItem managerUserAction;

    public FormMenu(Usuarios user) {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.user = user;

        initComponents();

//        this.configuraMenu();
    }

    private void initComponents(){

        addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent e) {
                formWindowClosing(e);
            }
        });

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        /*
        * MENU APLICAÇÃO
         */
        JMenu fileMenu = new JMenu("Aplicação");
        JMenuItem exitAction = new JMenuItem("Sair");
        exitAction.addActionListener((ActionEvent evt) -> {
            mnuExitActionPerformed(evt);
        });
        fileMenu.add(exitAction);

        /*
        * MENU CADASTROS
         */
        JMenu cadastroMenu = new JMenu("Cadastros");
        JMenuItem cadastroEstudantesAction = new JMenuItem("Manuteção de Estudantes");
        cadastroEstudantesAction.addActionListener((ActionEvent etv)->{

        });
        cadastroMenu.add(cadastroEstudantesAction);

        /*
        * MENU CONFIGURAÇÕES
         */
        JMenu configMenu = new JMenu("Configurações");

        JMenuItem managerUserAction = new JMenuItem("Gerenciar Usuários");
        managerUserAction.addActionListener((ActionEvent evt)->{
            mnuManagerUser(evt);
        });
        configMenu.add(managerUserAction);
        JMenuItem trocarUserAction = new JMenuItem("Trocar de Usuário");
        trocarUserAction.addActionListener((ActionEvent evt) -> {
            mnuTrocarUser(evt);
        });
        configMenu.add(trocarUserAction);

        // Adiciona a barra de menus
        menuBar.add(fileMenu);
        menuBar.add(configMenu);

        /**
         *  Cria a barra de Status
         *
         * @param statusBar
         */
        this.statusBar = new StatusBar(getWidth());
        this.statusBar.setMessagem("Logado como "+this.user.getNome());

        add(this.statusBar, BorderLayout.SOUTH);
    }

    private void formWindowClosing(WindowEvent evt){
        this.sairDoApp();
    }

    private void mnuExitActionPerformed(ActionEvent evt){
        this.sairDoApp();
    }
    private void mnuManagerUser(ActionEvent evt){
        new ListaUsuarios(getInstance(), true).start();
    }
    private void mnuTrocarUser(ActionEvent evt){
        AuthForm login = AuthForm.getInstance();
        this.dispose();
        login.setVisible(true);
    }

    private void configurarMenu(){
        if (this.user.getRole().getRole().equals("USERS")){
            managerUserAction.setVisible(false);
        }
    }
    private void sairDoApp(){
        if (JOptionPane.showConfirmDialog(null, "Você deseja realmente sair?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    public JFrame getInstance(){
        return this;
    }

    public void start(){
        setVisible(true);
    }
}
