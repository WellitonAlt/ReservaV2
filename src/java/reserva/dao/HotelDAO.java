package reserva.dao;

import reserva.Hotel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.naming.NamingException;
import javax.sql.DataSource;

@RequestScoped
public class HotelDAO {
    
    @Resource(name = "jdbc/reservaDBLocal")
    DataSource dataSource;
     
    private final static String CRIAR_HOTEL_SQL = "insert into Hotel"
            + " (cnpj, nome, senha, cidade)"
            + " values (?,?,?,?)";

    private final static String BUSCAR_HOTEL_BY_ID_SQL = "select"
            + " id, cnpj, nome, cidade"
            + " from hotel"
            + " where id=?";
    
    private final static String BUSCAR_HOTEL_BY_CIDADE_SQL = "select"
            + " id, cnpj, nome, cidade"
            + " from hotel"
            + " where cidade like ? "
            + " order by nome ";
    
    private final static String LOGIN_HOTEL_SQL = "select"
            + " id, cnpj, nome, senha, cidade"
            + " from hotel "
            + " where cnpj = ? AND"
            + " senha = ? ";
       
    public Hotel gravarHotel(Hotel hotel) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_HOTEL_SQL, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, hotel.getCnpj());
            ps.setString(2, hotel.getNome());
            ps.setString(3, hotel.getSenha());
            ps.setString(4, hotel.getCidade());
            ps.execute();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                hotel.setId(rs.getInt(1));
            }
        }
        return hotel;
    }

    public Hotel buscarHotel(int id) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCAR_HOTEL_BY_ID_SQL)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                Hotel hotel = new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setCnpj(rs.getString("cnpj"));
                hotel.setNome(rs.getString("nome"));
                hotel.setCidade(rs.getString("cidade"));
                return hotel;
            }
        }
    }
    
    public List<Hotel> listarTodasHoteisByCidade(String cidade) throws SQLException, NamingException {
        List<Hotel> ret = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCAR_HOTEL_BY_CIDADE_SQL)) {
            
            if (!cidade.isEmpty()){
                cidade = "%" + cidade + "%";
            }else
                cidade = "%";
            
            ps.setString(1, cidade);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Hotel hotel = new Hotel();
                    hotel.setId(rs.getInt("id"));
                    hotel.setCnpj(rs.getString("cnpj"));
                    hotel.setNome(rs.getString("nome"));
                    hotel.setCidade(rs.getString("cidade"));
                   
                    ret.add(hotel);
                }
            }
        }
        return ret;
    }
    
    
    public Hotel loginHotel(String cnpj, String senha) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(LOGIN_HOTEL_SQL)) {
            ps.setString(1, cnpj);
            ps.setString(2, senha);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                Hotel hotel = new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setCnpj(rs.getString("cnpj"));
                hotel.setNome(rs.getString("nome"));
                hotel.setCidade(rs.getString("cidade"));
                return hotel;
            }catch(Exception e){return null;}
        }
    }

}

