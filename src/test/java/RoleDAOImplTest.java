import org.esca.app.auth.dao.impl.RoleDAOImpl;
import org.esca.app.auth.dominio.Roles;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RoleDAOImplTest {

    @Test
    public void tesAddRole(){
        Roles roles = new Roles();
        RoleDAOImpl roleDAO = new RoleDAOImpl();
        roles.setRole("APRENDIS");
        roleDAO.addRole(roles);
        assertNotNull(roles.getId());
    }
}
