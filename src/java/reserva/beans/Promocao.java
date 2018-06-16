package reserva.beans;


import java.io.Serializable;
import java.util.Date;

public class Promocao implements Serializable {
    private int id;
    private int site, hotel;
    private float preco;
    private Date dataInicial, dataFinal;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getSite() { return site; }

    public void setSite(int id_site) { this.site = id_site; }

    public int getHotel() { return hotel; }

    public void setHotel(int CNPJ_hotel) { this.hotel = CNPJ_hotel; }

    public float getPreco() { return preco; }
    
    public void setPreco(float preço) { this.preco = preço; }

    public Date getDataInicial() { return dataInicial; }

    public void setDataInicial(Date dataInicial) { this.dataInicial = dataInicial; }

    public Date getDataFinal() { return dataFinal; }

    public void setDataFinal(Date dataFinal) { this.dataFinal = dataFinal; }

}
