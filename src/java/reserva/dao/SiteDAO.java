
package reserva.dao;

import reserva.beans.Site;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.sql.DataSource;

@RequestScoped
public class SiteDAO {
    private final static String CRIAR_SITE_SQL = "insert into Site"
            + " (url, senha, nome, telefone)"
            + " values (?,?,?,?)";
    
    private final static String BUSCAR_SITE_SQL = "select"
            + " id, nome"
            + " from Site";
    
    private final static String BUSCAR_SITE_BY_ID_SQL = "select"
            + " id, nome"
            + " from Site"
            + " where id = ?";
    
    private final static String LOGIN_SITE_SQL = "select"
            + " id, url, nome, telefone"
            + " from site "
            + " where url = ? AND"
            + " senha = ? ";
    private final static String BUSCAR_SITE_BY_URL_SQL = "select"
            + " id, url, nome, telefone"
            + " from site "
            + " where url = ? ";
            
    @Resource(name = "jdbc/reservaDBLocal")
    DataSource dataSource;

    public Site gravarSite(Site site) throws SQLException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_SITE_SQL, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, site.getUrl());
            ps.setString(2, site.getSenha());
            ps.setString(3, site.getNome());
            ps.setString(4, site.getTelefone());
            ps.execute();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                site.setId(rs.getInt(1));
            }
        }
        return site;
    }
    
    public List<Site> listarTodosSites() throws SQLException {
        List<Site> ret = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCAR_SITE_SQL)) {
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Site site = new Site();
                    site.setId(rs.getInt("id"));
                    site.setNome(rs.getString("nome"));                  
                    ret.add(site);
                }
            }
        }
        return ret;
    }
        
    public Site loginSite(String url, String senha) throws SQLException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(LOGIN_SITE_SQL)) {
            ps.setString(1, url);
            ps.setString(2, senha);
            
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()){
                    Site site = new Site();
                    site.setId(rs.getInt("id"));
                    site.setUrl(rs.getString("url"));
                    site.setNome(rs.getString("nome"));
                    site.setTelefone(rs.getString("telefone"));
                    return site;
                } else {
                    return null;
                }
            }
        }
    }

    public Site buscarSitePorUrl(String url) throws SQLException{
          try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCAR_SITE_BY_URL_SQL)) {
            ps.setString(1, url);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    Site site = new Site();
                    site.setId(rs.getInt("id"));
                    site.setUrl(rs.getString("url"));
                    site.setNome(rs.getString("nome"));
                    site.setTelefone(rs.getString("telefone"));
                    return site;
                } else {
                    return null;
                }
            }
        }
    }
    
    public String buscarSitePorId(int id) throws SQLException{
          try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCAR_SITE_BY_URL_SQL)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    return rs.getString("nome");
                } else {
                    return null;
                }
            }
        }
    }
    

}
