package org.esca.app.auth.dominio;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String password;
    private String phone;
    private String cargo;
    private java.time.LocalDate created_at;
    private java.time.LocalDate updated_at;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Roles role;
}
