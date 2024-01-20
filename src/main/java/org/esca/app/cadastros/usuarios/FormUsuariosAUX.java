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
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;
import org.apache.commons.validator.routines.EmailValidator;
import org.esca.app.auth.dominio.Roles;
import org.esca.app.util.Util;


public class FormUsuariosAUX extends javax.swing.JDialog{

    private Usuarios user = new Usuarios();
    private final UsuarioDAOImpl daoUser = new UsuarioDAOImpl();
    private final RoleDAOImpl daoRole = new RoleDAOImpl();
    private final String titulo;
    private static BarraTitulo bt;
    private JTextField txtEmail;
    private JLabel lblMsgEmail, lblMsgNome, lblMsgCargo, lblMsgPhone, lblMsgPwd;
    private JPasswordField txtPassword;
    private JTextField txtNome, txtCargo, txtPhone;
    private JComboBox<Object> cbxRoles;
    private JButton btnSave;
    Locale local = new Locale("pt", "BR");
    
    Font fontError = new Font("Roboto Black", Font.ITALIC, 10);

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
        bt.btnClose.addActionListener((ActionEvent actionEvent) -> {
            dispose();
        });

        JPanel formulario = new JPanel();
        formulario.setLayout(null);
        formulario.setBorder(new EmptyBorder(20, 20, 20, 20));

        txtNome = new JTextField();
        txtNome.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nome do Usuário");
        txtNome.setBounds(20, 40, 370, 27);
        lblMsgNome = new JLabel();
        lblMsgNome.setForeground(Color.RED);
        lblMsgNome.setBounds(20, 65, 370, 27);
        lblMsgNome.setFont(fontError);

        txtEmail = new JTextField();
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email");
        txtEmail.setBounds(20, 90, 370, 27);
        lblMsgEmail = new JLabel();
        lblMsgEmail.setForeground(Color.RED);
        lblMsgEmail.setBounds(20, 115, 370, 27);
        lblMsgEmail.setFont(fontError);

        txtCargo = new JTextField();
        txtCargo.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Cargo");
        txtCargo.setBounds(20, 140, 370, 27);
        lblMsgCargo = new JLabel();
        lblMsgCargo.setForeground(Color.RED);
        lblMsgCargo.setBounds(20, 165, 370, 27);
        lblMsgCargo.setFont(fontError);

        txtPhone = new JTextField(11); // 6334561488
        txtPhone.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Telefone Ex.: 63999329304");
        txtPhone.addKeyListener(new java.awt.event.KeyAdapter(){
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPhoneKeyTyped(evt);
            }
        });
        txtPhone.setBounds(20, 190, 370, 27);
        lblMsgPhone = new JLabel();
        lblMsgPhone.setForeground(Color.RED);
        lblMsgPhone.setBounds(20, 215, 370, 27);
        lblMsgPhone.setFont(fontError);

        cbxRoles = new JComboBox<>();
        cbxRoles.setBounds(20, 240, 370, 27);

        txtPassword = new JPasswordField();
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");
        txtPassword.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true;"+"showCapsLock:true;");
        txtPassword.setBounds(20, 275, 370, 27);
        lblMsgPwd = new JLabel();
        lblMsgPwd.setForeground(Color.RED);
        lblMsgPwd.setBounds(20, 300, 370, 27);
        lblMsgPwd.setFont(fontError);

        btnSave = new JButton(this.titulo);
        btnSave.setBounds(20, 340, 100, 30);
        btnSave.addActionListener((ActionEvent actionEvent) -> {
            saveActionPerformed(actionEvent);
        });

        formulario.add(txtNome);
        formulario.add(lblMsgNome);
        formulario.add(txtEmail);
        formulario.add(lblMsgEmail);
        formulario.add(txtCargo);
        formulario.add(lblMsgCargo);
        formulario.add(txtPhone);
        formulario.add(lblMsgPhone);
        formulario.add(cbxRoles);
        formulario.add(txtPassword);
        formulario.add(lblMsgPwd);
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

        setSize(new Dimension(410, 450));
        setLocationRelativeTo(null);
    }
    
    private void txtPhoneKeyTyped(java.awt.event.KeyEvent evt){
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;
        if (!numero) evt.consume();
        if (txtPhone.getText().strip().length() == 11) evt.consume();
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

    private void adicionar(){
        // Pega os valores dos campos
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String cargo = txtCargo.getText();
        String phone = txtPhone.getText();
        String pwd = Arrays.toString(txtPassword.getPassword());
        if (nome.isEmpty()) {
            lblMsgNome.setText("Nome é obrigatório");
            txtNome.putClientProperty("JComponent.outline", "warning");
            txtNome.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            txtNome.requestFocus();
            return;
        } else if(nome.length() <= 5) {
            lblMsgNome.setText("Nome deve ter no mínimo 5 characteres.");
            txtNome.putClientProperty("JComponent.outline", "warning");
            txtNome.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            txtNome.requestFocus();
            return;
        }else {
            lblMsgNome.setText(null);
            txtNome.putClientProperty("JComponent.outline", java.awt.Color.GREEN);
        }
        if (!EmailValidator.getInstance().isValid(email)){
            lblMsgEmail.setText("Email inválido");
            txtEmail.putClientProperty("JComponent.outline", "warning");
            txtEmail.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            txtEmail.requestFocus();
            return;
        }else{
            lblMsgEmail.setText(null);
            txtEmail.putClientProperty("JComponent.outline", java.awt.Color.GREEN);
        }
        
        if (cargo.isEmpty()) {
            lblMsgCargo.setText("Cargo é obrigatório");
            txtCargo.putClientProperty("JComponent.outline", "warning");
            txtCargo.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            txtCargo.requestFocus();
            return;
        }else{
            lblMsgCargo.setText(null);
            txtCargo.putClientProperty("JComponent.outline", java.awt.Color.GREEN);
        }
        if (!phone.matches("\\d{11}")){
            lblMsgPhone.setText("Telefone é obrigatório");
            txtPhone.putClientProperty("JComponent.outline", "warning");
            txtPhone.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            txtPhone.requestFocus();
            return;
        }else{
            lblMsgPhone.setText(null);
            txtPhone.putClientProperty("JComponent.outline", java.awt.Color.GREEN);
        }
        if (pwd.isEmpty()){
            lblMsgPwd.setText("Password é obrigatório");
            txtPassword.putClientProperty("JComponent.outline", "warning");
            txtPassword.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            txtPassword.requestFocus();
            return;
        }
        else if (!new Util().isValidPwd(pwd)){
            lblMsgPwd.setText("Password inválido");
            txtPassword.putClientProperty("JComponent.outline", "warning");
            txtPassword.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            txtPassword.requestFocus();
            return;
        }else{
            lblMsgPwd.setText(null);
            txtPassword.putClientProperty("JComponent.outline", java.awt.Color.GREEN);
        }
        
        LocalDate created_at = LocalDate.now();
        
        // Envia os dados para a classe de serviço
        this.user.setNome(nome);
        this.user.setEmail(email);
        this.user.setCargo(cargo);
        this.user.setPhone(phone);
        ComboBoxList roleId = (ComboBoxList) this.cbxRoles.getSelectedItem();
        Roles role = new Roles();
        role.setId(roleId.getId());        
        this.user.setRole(role);
        this.user.setPassword(new Util().gerarPassword(pwd));
        this.user.setCreated_at(created_at);
        
        
        // popula no banco de dados;
        this.save(this.user);        
        
    
    }
    private void atualizar(){
    }

    private void save(Usuarios u){
         if (user.getId() == null){
            daoUser.addUser(u);
          
        }else {
            daoUser.updateUser(u);
        }
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
