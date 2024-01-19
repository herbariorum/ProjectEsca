package org.esca.app.cadastros.usuarios;

import com.formdev.flatlaf.FlatClientProperties;
import org.esca.app.auth.dao.impl.RoleDAOImpl;
import org.esca.app.auth.dao.impl.UsuarioDAOImpl;
import org.esca.app.auth.dominio.Usuarios;
import org.esca.app.cadastros.fragmentos.BarraTitulo;
import org.esca.app.util.ComboBoxList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormUsuariosAUX extends javax.swing.JDialog{

    private Usuarios user = new Usuarios();
    private final UsuarioDAOImpl daoUser = new UsuarioDAOImpl();
    private final RoleDAOImpl daoRole = new RoleDAOImpl();
    private String titulo;
    private static BarraTitulo bt;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JTextField txtNome, txtCargo, txtPhone;
    private JComboBox<Object> cbxRoles;
    private JButton btnSave;

    // NEW
    public FormUsuariosAUX(javax.swing.JDialog parent, boolean modal, String titulo){
        super(parent, modal);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        this.titulo = titulo;
        initComponent();

        this.setComboBoxRole();
    }

    // UPDATE
    public FormUsuariosAUX(javax.swing.JDialog parent, boolean modal, Usuarios user, String titulo){
        super(parent, modal);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        this.user = user;
        this.titulo = titulo;
        initComponent();

        this.setComboBoxRole();

        this.preencheFormulario();
    }

    // DELETE
    public FormUsuariosAUX(javax.swing.JDialog parent, boolean modal, String titulo, Usuarios user){
        super(parent, modal);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        this.user = user;
        this.titulo = titulo;
        initComponent();

        this.setComboBoxRole();

        this.preencheFormulario();
    }

    private void initComponent(){
        Container content = getContentPane();
        content.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        /**
         * CARREGA A BARRA DE TITULOS
         */
        bt = new BarraTitulo();
        bt.title.setText("Manutenção de Usuários - "+this.titulo);
        bt.btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        JPanel formulario = new JPanel();
        formulario.setLayout(null);
        formulario.setBorder(new EmptyBorder(20, 20, 20, 20));

        txtNome = new JTextField();
        txtNome.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nome do Usuário");
        txtNome.setBounds(20, 30, 370, 27);

        txtEmail = new JTextField();
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email");
        txtEmail.setBounds(20, 70, 370, 27);

        txtCargo = new JTextField();
        txtCargo.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Cargo");
        txtCargo.setBounds(20, 110, 370, 27);

        txtPhone = new JTextField();
        txtPhone.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Telefone");
        txtPhone.setBounds(20, 160, 370, 27);

        cbxRoles = new JComboBox<>();
        cbxRoles.setBounds(20, 210, 370, 27);

        txtPassword = new JPasswordField();
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");
        txtPassword.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true;"+"showCapsLock:true;");
        txtPassword.setBounds(20, 260, 370, 27);

        btnSave = new JButton(this.titulo);
        btnSave.setBounds(20, 310, 100, 30);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                saveActionPerformed(actionEvent);
            }
        });

        formulario.add(txtNome);
        formulario.add(txtEmail);
        formulario.add(txtCargo);
        formulario.add(txtPhone);
        formulario.add(cbxRoles);
        formulario.add(txtPassword);
        formulario.add(btnSave);

        // Adiciona a barra de titulo e o formulario ao JPanel principal
        gbc.weightx = 1;
        gbc.weighty = 0.01; // altura do panel titulo
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        content.add(bt, gbc);

        gbc.weighty = 0.99; // altura do panel da tabela
        gbc.gridy = 2;
        content.add(formulario, gbc);

        setSize(new Dimension(410, 430));
        setLocationRelativeTo(null);
    }

    private void saveActionPerformed(ActionEvent evt){
        switch (this.titulo) {
            case "NEW":
                this.adicionar();
                break;
            case "UPDATE":
                this.atualizar();
                break;
            case "DELETE":
                if (JOptionPane.showConfirmDialog(null, "Confirma a exclusão?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    daoUser.deleteUser(user);
                    dispose();
                } else {
                    dispose();
                }
            default:
                throw new AssertionError();
        }
    }

    private void preencheFormulario(){
        txtNome.setText(this.user.getNome());
        txtEmail.setText(this.user.getEmail());
        txtCargo.setText(this.user.getCargo());
        txtPhone.setText(this.user.getPhone());
        txtPassword.setText(null);
        for (ComboBoxList a : this.daoRole.getList()){
            a.setSelectedId(daoRole.getList(), String.valueOf(this.user.getRole().getId()), cbxRoles);
        }
    }

    private void adicionar(){}
    private void atualizar(){
    }

    private void setComboBoxRole(){
        daoRole.comboBoxRole();
        cbxRoles.removeAllItems();
        for (ComboBoxList a: daoRole.getList()){
            cbxRoles.addItem(a);
        }
    }
    public void start(){
        setVisible(true);
    }
}
