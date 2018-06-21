package reserva.beans;

import java.io.Serializable;

public class PromocaoConsulta implements Serializable {
        private Hotel hotel;
        private Promocao promocao;
        private Site site;

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
        
        
}