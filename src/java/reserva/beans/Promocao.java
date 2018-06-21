package reserva.beans;


import java.io.Serializable;
import java.util.Date;

public class Promocao implements Serializable {
    private int id;
    private int siteID, hotelID;
    private float preco;
    private Date dataInicial, dataFinal;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getSite() { return siteID; }

    public void setSite(int id_site) { this.siteID = id_site; }

    public int getHotel() { return hotelID; }

    public void setHotel(int id_hotel) { this.hotelID = id_hotel; }

    public float getPreco() { return preco; }
    
    public void setPreco(float preço) { this.preco = preço; }

    public Date getDataInicial() { return dataInicial; }

    public void setDataInicial(Date dataInicial) { this.dataInicial = dataInicial; }

    public Date getDataFinal() { return dataFinal; }

    public void setDataFinal(Date dataFinal) { this.dataFinal = dataFinal; }

}
