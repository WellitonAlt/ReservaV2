package reserva.beans;


import java.io.Serializable;
import java.util.Date;

public class Promocao implements Serializable {
    private int id;
    private int siteID, hotelCNPJ;
    private float preco;
    private Date dataInicial, dataFinal;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getSite() { return siteID; }

    public void setSite(int id_site) { this.siteID = id_site; }

    public int getHotel() { return hotelCNPJ; }

    public void setHotel(int CNPJ_hotel) { this.hotelCNPJ = CNPJ_hotel; }

    public float getPreco() { return preco; }
    
    public void setPreco(float preço) { this.preco = preço; }

    public Date getDataInicial() { return dataInicial; }

    public void setDataInicial(Date dataInicial) { this.dataInicial = dataInicial; }

    public Date getDataFinal() { return dataFinal; }

    public void setDataFinal(Date dataFinal) { this.dataFinal = dataFinal; }

}
