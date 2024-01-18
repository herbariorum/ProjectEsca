package org.esca.app.auth.dao;

import jakarta.persistence.EntityManager;
import org.esca.app.auth.dominio.Usuarios;

import java.util.List;

public interface UsuarioDAO {
    public void addUser(Usuarios usuarios, EntityManager em);
    public void updateUser(Usuarios usuarios, EntityManager em);
    public void deleteUser(Usuarios usuarios, EntityManager em);
    public List<Usuarios> selectUser(EntityManager em);
    public Usuarios loginUser(Usuarios usuarios, EntityManager em);
    public Usuarios selectEmailUser(Usuarios usuarios, EntityManager em);
    public  List<Usuarios> selectByValue(String value, EntityManager em);
}
