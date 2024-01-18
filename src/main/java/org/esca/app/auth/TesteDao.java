package org.esca.app.auth;

import org.esca.app.auth.dao.impl.RoleDAOImpl;
import org.esca.app.auth.dominio.Roles;

public class TesteDao {

    public static void main(String[] args) {
        Roles roles = new Roles();
        RoleDAOImpl roleDAO = new RoleDAOImpl();
        roles.setRole("APRENDIS");
        roleDAO.addRole(roles);
    }
}
