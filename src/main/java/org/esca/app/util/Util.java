package org.esca.app.util;

import org.esca.app.auth.dominio.Usuarios;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Util {
    private final String pPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    Pattern regex;

    public boolean isValidPwd(String pwd) {
        this.regex = Pattern.compile(pPassword);
        Matcher m = this.regex.matcher(pwd);
        return m.matches();
    }

    public String gerarPassword(String pwd) {
        return BCrypt.hashpw(pwd, BCrypt.gensalt(12));
    }

    public boolean checkPassword(String pwd, Usuarios f) {
        boolean matched = BCrypt.checkpw(pwd, f.getPassword());
        return matched;
    }
}
