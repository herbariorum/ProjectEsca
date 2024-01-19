package org.esca.app.cadastros.usuarios;


import org.esca.app.auth.dao.impl.UsuarioDAOImpl;
import org.esca.app.auth.dominio.Roles;
import org.esca.app.auth.dominio.Usuarios;
import org.esca.app.cadastros.fragmentos.BarraTitulo;
import org.esca.app.cadastros.fragmentos.PanelButton;
import org.esca.app.cadastros.fragmentos.PanelTable;
import org.esca.app.cadastros.usuarios.config.UserCellRenderer;
import org.esca.app.cadastros.usuarios.config.UserTableModel;
import org.esca.app.util.Util;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ListaUsuarios extends JDialog implements ActionListener {

    private Usuarios user = new Usuarios();
    private final UsuarioDAOImpl dao = new UsuarioDAOImpl();
    private static BarraTitulo bt;
    private static PanelTable ptable;
    private static PanelButton pb;

    public ListaUsuarios(JFrame parent, boolean modal) {
        super(parent, modal);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                formWindowActivated(e);
            }
        });

        initComponents();


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String option = actionEvent.getActionCommand();
        switch (option) {
            case "Adicionar":
                new FormUsuariosAUX(getInstance(), true, "NEW").start();
                break;
            case "Atualizar":
                if (this.user.getId() == null) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um registro na tabela primeiro.");
                } else {
                    new FormUsuariosAUX(getInstance(), true, this.user, "UPDATE").start();
                    this.user.setId(null);
                }
                break;
            case "Excluir":
                if (this.user.getId() == null) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um registro na tabela primeiro.");
                } else {
                    new FormUsuariosAUX(getInstance(), true, "DELETE", this.user).start();
                    this.user.setId(null);
                }
                break;

            default:
                throw new AssertionError();
        }
    }

    private void formWindowActivated(WindowEvent evt) {
        setTable("");
    }

    private void initComponents() {
        Container content = getContentPane();
        content.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        /**
         * CARREGA A BARRA DE TITULOS
         */
        bt = new BarraTitulo();
        bt.title.setText("Manutenção de Usuários");
        bt.btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
        /**
         * CARREGA OS BOTÕES
         */
        pb = new PanelButton();
        pb.btnNew.addActionListener(this);
        pb.btnUpdate.addActionListener(this);
        pb.btnDelete.addActionListener(this);
        /**
         * CARREGA A TABELA
         */

        ptable = new PanelTable();
        ptable.tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabelaMouseClicked(e);
            }
        });
        ptable.txtLocalizar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                txtLocalizarKeyPressed(e);
            }
        });

        // Posiciona os componentes
        gbc.weightx = 1;
        gbc.weighty = 0.01; // altura do panel titulo
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        content.add(bt, gbc);
        gbc.weighty = 0.1; // altura do panel de botoes
        gbc.gridy = 1;
        content.add(pb, gbc);
        gbc.weighty = 0.89; // altura do panel da tabela
        gbc.gridy = 2;
        content.add(ptable, gbc);

        setSize(new Dimension(800, 520));
        setLocationRelativeTo(null);
    }

    private void tabelaMouseClicked(MouseEvent evt) {
        int row = ptable.tabela.getSelectedRow();
        if (row != -1) {
            Long userId = Long.valueOf(ptable.tabela.getValueAt(row, 0).toString());
            Long roleId = Long.valueOf(ptable.tabela.getValueAt(row, 1).toString());
            String nome = ptable.tabela.getValueAt(row, 2).toString();
            String email = ptable.tabela.getValueAt(row, 3).toString();
            String cargo = ptable.tabela.getValueAt(row, 4).toString();
            String telefone = ptable.tabela.getValueAt(row, 5).toString();
            String created_at = ptable.tabela.getValueAt(row, 6).toString();

            String role = ptable.tabela.getValueAt(row, 7).toString();

            Roles r = new Roles();
            r.setId(roleId);
            r.setRole(role);
            Usuarios u = new Usuarios();
            u.setId(userId);
            u.setNome(nome);
            u.setEmail(email);
            u.setCargo(cargo);
            u.setPhone(telefone);
            u.setCreated_at(new Util().dateToLocalDate(created_at));
            u.setRole(r);
            this.user = u;
        }

        if (evt.getClickCount() == 2) {
            new FormUsuariosAUX(getInstance(), true, this.user, "UPDATE").start();
            this.user.setId(null);
        }
    }

    private void txtLocalizarKeyPressed(KeyEvent evt) {
        setTable(ptable.txtLocalizar.getText());
    }

    private void setTable(String value) {
        List<Usuarios> usuarios = dao.selectByValue(value);
        ptable.tabela.setModel(new UserTableModel(usuarios));
        ptable.tabela.setDefaultRenderer(Object.class, new UserCellRenderer());
        ptable.tabela.setRowSorter(new TableRowSorter<>(ptable.tabela.getModel()));
    }

    public JDialog getInstance() {
        return this;
    }

    public void start() {
        setVisible(true);
    }
}
