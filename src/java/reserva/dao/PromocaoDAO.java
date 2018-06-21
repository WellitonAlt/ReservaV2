package reserva.dao;

import reserva.beans.Hotel;
import reserva.beans.Promocao;
import reserva.beans.Site;
import reserva.beans.PromocaoConsulta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Date;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class PromocaoDAO {
 
    private final static String CRIAR_PROMOCAO_SQL = "insert into Promocao"
            + " (preco, dataInicial, dataFinal, site, hotel)"
            + " values (?,?,?,?,?)";

    private final static String LISTAR_PROMOCAO_SQL_BY_HOTEL = "select"
            + " p.id as promocaoId, p.preco, p.dataInicial, p.dataFinal,"
            + " h.id as hotelId, h.nome as hotelNome, h.cidade as hotelCidade,"
            + " s.id as siteId, s.url as siteURL, s.nome as siteNome, s.telefone as siteTelefone"
            + " from Promocao p"
            + " inner join Hotel h on p.hotel = h.id"
            + " inner join Site s on p.site = s.id"
            + " where h.id = ?";
 
    
    private final static String LISTAR_PROMOCAO_SQL_BY_SITE = "select"
            + " p.id as promocaoId, p.preco, p.dataInicial, p.dataFinal,"
            + " h.id as hotelId, h.nome as hotelNome, h.cidade as hotelCidade,"
            + " s.id as siteId, s.url as siteURL, s.nome as siteNome, s.telefone as siteTelefone"
            + " from Promocao p"
            + " inner join Hotel h on p.hotel = h.id"
            + " inner join Site s on p.site = s.id"
            + " where s.id = ?";
    
    private final static String VERIFICA_DATA_SQL = "select"
            + " dataInicial, dataFinal, hotel"
            + " from Promocao "
            + " where "
            + " hotel = ? and"
            + " dataInicial = ? and"
            + " dataFinal = ?";
    
    @Resource(name = "jdbc/reservaDBLocal")
    DataSource dataSource;

    public Promocao gravarPromocao(Promocao promocao) throws SQLException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_PROMOCAO_SQL, Statement.RETURN_GENERATED_KEYS);) {
            
            ps.setFloat(1, promocao.getPreco());
            ps.setDate(2, new java.sql.Date(promocao.getDataInicial().getTime()));
            ps.setDate(3, new java.sql.Date(promocao.getDataFinal().getTime()));
            ps.setInt(4, promocao.getSite());
            ps.setInt(5, promocao.getHotel());
            ps.execute();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                promocao.setId(rs.getInt(1));
            }
        }
        return promocao;
    }

    public List<PromocaoConsulta> listarTodasPromocoesByHotel(String ID) throws SQLException {
        List<PromocaoConsulta> ret = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(LISTAR_PROMOCAO_SQL_BY_HOTEL)) {
            
            ps.setString(1, ID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PromocaoConsulta proConsulta = new PromocaoConsulta();
                    Promocao promocao = new Promocao();
                    Site site = new Site();
                    Hotel hotel = new Hotel();
                                  
                    promocao.setId(rs.getInt("promocaoId"));
                    promocao.setPreco(rs.getFloat("preco"));
                    promocao.setDataInicial(rs.getDate("dataInicial"));
                    promocao.setDataFinal(rs.getDate("dataInicial"));
                    
                    site.setNome(rs.getString("siteNome"));
                    site.setUrl(rs.getString("siteURL"));
                    site.setTelefone(rs.getString("siteTelefone"));
                    
                    hotel.setNome(rs.getString("hotelNome"));
                    hotel.setCidade(rs.getString("hotelCidade"));
                    
                    proConsulta.setHotel(hotel);
                    proConsulta.setPromocao(promocao);
                    proConsulta.setSite(site);
                    
                    ret.add(proConsulta);
                }
            }
        }
        return ret;
    }
    
    public List<PromocaoConsulta> listarTodasPromocoesBySite(String ID) throws SQLException {
        List<PromocaoConsulta> ret = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(LISTAR_PROMOCAO_SQL_BY_SITE)) {
            
            ps.setString(1, ID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PromocaoConsulta proConsulta = new PromocaoConsulta();
                    Promocao promocao = new Promocao();
                    Site site = new Site();
                    Hotel hotel = new Hotel();
                                  
                    promocao.setId(rs.getInt("promocaoId"));
                    promocao.setPreco(rs.getFloat("preco"));
                    promocao.setDataInicial(rs.getDate("dataInicial"));
                    promocao.setDataFinal(rs.getDate("dataInicial"));
                    
                    site.setNome(rs.getString("siteNome"));
                    site.setUrl(rs.getString("siteURL"));
                    site.setTelefone(rs.getString("siteTelefone"));
                    
                    hotel.setNome(rs.getString("hotelNome"));
                    hotel.setCidade(rs.getString("hotelCidade"));
                    
                    proConsulta.setHotel(hotel);
                    proConsulta.setPromocao(promocao);
                    proConsulta.setSite(site);
                    
                    ret.add(proConsulta);
                }
            }
        }
        return ret;
    }
    
    public boolean verificaData(Date dataInicial, Date dataFinal, int hotel) throws SQLException {
         try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(VERIFICA_DATA_SQL)) {
            
             
            java.sql.Date sqlDataInicial = new java.sql.Date(dataInicial.getTime());
            java.sql.Date sqlDataFinal = new java.sql.Date(dataFinal.getTime());
            
            ps.setInt(1, hotel);
            ps.setDate(2, sqlDataInicial);
            ps.setDate(3, sqlDataFinal); 
            
            try (ResultSet rs = ps.executeQuery()) {
               if(rs.next())
                   return true;
               else
                   return false;
            }
        }
        
    }
}
