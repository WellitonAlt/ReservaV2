package reserva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ErroDAO {

    private final static String CRIAR_ERRO_SQL = "insert into ERRO"
            + " (mensagem)"
            + " values (?)";
   
    public ErroDAO(DataSource dataSource, String mensagem) {
        
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_ERRO_SQL, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, mensagem);
            ps.execute();
        }
        catch(SQLException e){};
    }    
}

